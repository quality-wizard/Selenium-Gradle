#!/bin/bash

# =======================
# Cargar archivo .env
# =======================
ENV_FILE=".env"
if [ -f "$ENV_FILE" ]; then
    export $(grep -v '^#' "$ENV_FILE" | xargs)
else
    echo "Archivo .env no encontrado. Abortando."
    exit 1
fi

# =======================
# Configuración
# =======================

AUTH_URL="https://xray.cloud.getxray.app/api/v2/authenticate"
KEY_FILE="build/cucumber/testExecutionKey.txt"

if [ -z "$XRAY_CLIENT_ID" ] || [ -z "$XRAY_CLIENT_SECRET" ] || [ -z "$JIRA_URL" ] || [ -z "$JIRA_EMAIL" ] || [ -z "$JIRA_API_TOKEN" ]; then
    echo "Variables XRAY_CLIENT_ID, XRAY_CLIENT_SECRET, JIRA_URL, JIRA_EMAIL y JIRA_API_TOKEN deben estar definidas."
    exit 1
fi

if [ ! -f "$KEY_FILE" ]; then
    echo "No se encontró archivo $KEY_FILE con Test Execution Key. Ejecuta primero upload-xray.sh"
    exit 1
fi

TEST_EXECUTION_KEY=$(cat "$KEY_FILE")
echo "Test Execution Key leída: $TEST_EXECUTION_KEY"

# Obtener token Xray
TOKEN=$(curl -s -H "Content-Type: application/json" \
    -X POST \
    -d "{\"client_id\": \"$XRAY_CLIENT_ID\", \"client_secret\": \"$XRAY_CLIENT_SECRET\"}" \
    "$AUTH_URL" | tr -d '"')

echo "Generando PDF de Allure Report con Puppeteer..."

if ! command -v node >/dev/null 2>&1; then
    echo "Node.js no instalado. Instala Node.js para generar PDF."
    exit 1
fi

if [ ! -d "node_modules/puppeteer" ]; then
    echo "Instalando Puppeteer..."
    npm install puppeteer --silent --no-progress > /dev/null 2>&1
fi

cat <<'EOP' > generate-pdf.js
const puppeteer = require('puppeteer');

(async () => {
    const browser = await puppeteer.launch({
        headless: true,
        args: ['--no-sandbox', '--disable-setuid-sandbox']
    });
    const page = await browser.newPage();
    await page.goto('http://localhost:8888', { waitUntil: 'networkidle0' });
    await new Promise(resolve => setTimeout(resolve, 3000));
    await page.pdf({
        path: 'build/allure-report/allure-report.pdf',
        format: 'A4',
        printBackground: true,
        margin: { top: '20px', bottom: '20px', left: '20px', right: '20px' }
    });
    await browser.close();
    console.log('PDF generado exitosamente.');
})();
EOP


npx http-server build/allure-report -p 8888 > /dev/null 2>&1 &
SERVER_PID=$!
sleep 3  # Espera para que el servidor se inicie completamente

NODE_OPTIONS="--no-deprecation" node generate-pdf.js > /dev/null 2>&1

kill $SERVER_PID

PDF_PATH="build/allure-report/allure-report.pdf"
if [ ! -f "$PDF_PATH" ]; then
    echo "No se encontró PDF generado. Abortando."
    exit 1
fi

echo "Subiendo PDF a Xray..."

UPLOAD_RESPONSE=$(curl -s -H "Authorization: Bearer $TOKEN" \
    -F "attachment=@$PDF_PATH" \
    https://xray.cloud.getxray.app/api/v2/attachments)

echo "PDF subido correctamente en Xray."

ATTACHMENT_ID=$(echo "$UPLOAD_RESPONSE" | sed -n 's/.*\"id\":\"\([^\"]*\)\".*/\1/p')

if [ -z "$ATTACHMENT_ID" ]; then
    echo "No se pudo obtener attachmentId. Abortando."
    exit 1
fi

echo "Adjuntando PDF al Test Execution $TEST_EXECUTION_KEY en Jira..."

# Construir header Basic Auth para Jira con email y API token
BASIC_AUTH=$(echo -n "$JIRA_EMAIL:$JIRA_API_TOKEN" | base64)

JIRA_ATTACH_RESPONSE=$(curl -s -H "X-Atlassian-Token: no-check" \
    -H "Authorization: Basic $BASIC_AUTH" \
    -F "file=@$PDF_PATH" \
    "$JIRA_URL/rest/api/2/issue/$TEST_EXECUTION_KEY/attachments")

echo "PDF adjuntado correctamente a ticket execution en Jira."
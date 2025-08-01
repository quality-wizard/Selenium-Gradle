#!/bin/bash

# # =======================
# # Cargar archivo .env
# # =======================
# ENV_FILE=".env"
# if [ -f "$ENV_FILE" ]; then
#     export $(grep -v '^#' "$ENV_FILE" | xargs)
# else
#     echo "Archivo .env no encontrado. Abortando."
#     exit 1
# fi

# =======================
# Configuración
# =======================

# Variables de entorno XRAY_CLIENT_ID y XRAY_CLIENT_SECRET deben estar definidas

AUTH_URL="https://xray.cloud.getxray.app/api/v2/authenticate"
IMPORT_URL="https://xray.cloud.getxray.app/api/v2/import/execution/cucumber/multipart"
KEY_FILE="build/cucumber/testExecutionKey.txt"

# Validar variables necesarias
if [ -z "$XRAY_CLIENT_ID" ] || [ -z "$XRAY_CLIENT_SECRET" ]; then
    echo "Variables XRAY_CLIENT_ID y XRAY_CLIENT_SECRET no definidas."
    exit 1
fi

# Obtener token
TOKEN=$(curl -s -H "Content-Type: application/json" \
    -X POST \
    -d "{\"client_id\": \"$XRAY_CLIENT_ID\", \"client_secret\": \"$XRAY_CLIENT_SECRET\"}" \
    "$AUTH_URL" | tr -d '"')

# Crear info.json
INFO_FILE="build/cucumber/info.json"
mkdir -p build/cucumber
cat <<EOF > "$INFO_FILE"
{
    "fields": {
        "project": { "key": "CWP" },
        "summary": "Smoke Test - FreeRangeNavigation",
        "description": "Ejecución automática en entorno QA desde Jenkins. Pruebas sobre la navegación principal sin login.",
        "issuetype": { "name": "Test Execution" }
    },
    "startDate": "$(date -u +"%Y-%m-%dT%H:%M:%S%z")",
    "finishDate": "$(date -u +"%Y-%m-%dT%H:%M:%S%z")"
}
EOF

echo "Subiendo resultados a Xray..."

RESPONSE=$(curl -s \
    -H "Authorization: Bearer $TOKEN" \
    -F "results=@build/cucumber/cucumber.json" \
    -F "info=@$INFO_FILE;type=application/json" \
    "$IMPORT_URL")

echo "Respuesta de Xray:"
echo "$RESPONSE"

# Extraer testExecutionKey y guardarlo
TEST_EXECUTION_KEY=$(echo "$RESPONSE" | sed -n 's/.*"key":"\([^"]*\)".*/\1/p')
mkdir -p build/cucumber
echo "$TEST_EXECUTION_KEY" > "$KEY_FILE"
echo "Test Execution Key guardada en $KEY_FILE"

# Limpiar
rm "$INFO_FILE"
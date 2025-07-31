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

# Reemplaza esto con tu endpoint de autenticación y subida de resultados
AUTH_URL="https://xray.cloud.getxray.app/api/v2/authenticate"
IMPORT_URL="https://xray.cloud.getxray.app/api/v2/import/execution/cucumber"

# Token de API desde variable de entorno (¡no lo hardcodees!)
TOKEN=$(curl -s -H "Content-Type: application/json" \
    -X POST \
    -d "{\"client_id\": \"$XRAY_CLIENT_ID\", \"client_secret\": \"$XRAY_CLIENT_SECRET\"}" \
    "$AUTH_URL" | tr -d '"')

# =======================
# Subida de resultados
# =======================

echo "Subiendo resultados a Xray..."
RESPONSE=$(curl -s \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $TOKEN" \
    --data @"build/allure-results/cucumber.json" \
    "$IMPORT_URL")

echo "Respuesta de Xray:"
echo "$RESPONSE"
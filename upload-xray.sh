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
IMPORT_URL="https://xray.cloud.getxray.app/api/v2/import/execution/cucumber/multipart"

# Obtener token
TOKEN=$(curl -s -H "Content-Type: application/json" \
    -X POST \
    -d "{\"client_id\": \"$XRAY_CLIENT_ID\", \"client_secret\": \"$XRAY_CLIENT_SECRET\"}" \
    "$AUTH_URL" | tr -d '"')

# =======================
# Subida de resultados sin testExecutionKey para que se cree automáticamente
# =======================
echo "Subiendo resultados a Xray..."
RESPONSE=$(curl -s \
    -H "Authorization: Bearer $TOKEN" \
    -F "file=@build/cucumber/cucumber.json" \
    -F "info={\"project\":\"CWP\",\"summary\":\"Ejecución automatizada desde Jenkins\"};type=application/json" \
    "$IMPORT_URL")

echo "Respuesta de Xray:"
echo "$RESPONSE"
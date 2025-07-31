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
 # Crear archivo temporal info.json
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
RESPONSE=$(curl -s \
    -H "Authorization: Bearer $TOKEN" \
    -F "results=@build/cucumber/cucumber.json" \
    -F "info=@$INFO_FILE;type=application/json" \
    "$IMPORT_URL")

echo "Respuesta de Xray:"
echo "$RESPONSE"
# Limpiar archivo temporal
rm "$INFO_FILE"
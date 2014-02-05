#!/bin/bash
SERVER="http://localhost:8080/pi-products"
URL="$SERVER/search/category"
echo "URL: $URL"
curl -H "Accept:application/json" "$URL" | python -mjson.tool


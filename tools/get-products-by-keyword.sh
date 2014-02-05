#!/bin/bash
SERVER="http://localhost:8080/pi-products"
URIENCODE=$(perl -MURI::Escape -e 'print uri_escape($ARGV[0]);' "$1")
URL="$SERVER/search/keyword/$URIENCODE"
echo "URL: $URL"
curl -H "Accept:application/json" "$URL" | python -mjson.tool


#!/bin/bash
SERVER="http://localhost:8080/pi-products"
URIENCODE=$(perl -MURI::Escape -e 'print uri_escape($ARGV[0]);' "$1")
URIKEYENCODE=$(perl -MURI::Escape -e 'print uri_escape($ARGV[0]);' "$2")
URL="$SERVER/search/category/$URIENCODE/keyword/$URIKEYENCODE"
echo "URL: $URL"
curl -H "Accept:application/json" "$URL" | python -mjson.tool


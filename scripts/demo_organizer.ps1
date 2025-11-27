$ErrorActionPreference = 'Stop'

$path = Join-Path $env:TEMP 'lab11-demo.jpg'
Invoke-WebRequest -Uri 'https://picsum.photos/seed/lab11/800/600' -OutFile $path

$resp = curl.exe -s -S -F "file=@$path" http://localhost:8081/uploadFile
$obj = $resp | ConvertFrom-Json
if (-not $obj) { Write-Error "Upload failed: $resp" }
$url = $obj.name
Write-Host "Uploaded URL: $url"

$payload = @{ name = '演示组织'; address = '演示地址'; image = $url } | ConvertTo-Json
$created = Invoke-RestMethod -Uri 'http://localhost:8081/organizer' -Method Post -ContentType 'application/json' -Body $payload
Write-Host ("Created organizer id: {0}" -f $created.id)
$created | ConvertTo-Json -Depth 6


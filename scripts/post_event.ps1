$ErrorActionPreference = 'Stop'

$body = @{
  category   = 'Test'
  title      = 'From CLI'
  description= 'desc'
  location   = 'loc'
  date       = '2025-11-27'
  time       = '10:00'
  petAllowed = $true
  organizer  = @{ id = 1 }
  images     = @('https://images.dog.ceo/breeds/husky/n02110185_1469.jpg','https://picsum.photos/seed/lab11/800/600')
} | ConvertTo-Json -Depth 6

$resp = Invoke-RestMethod -Uri 'http://localhost:8081/events' -Method Post -ContentType 'application/json' -Body $body
Write-Host ($resp | ConvertTo-Json -Depth 6)


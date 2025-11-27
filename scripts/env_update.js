const fs = require('fs')
const path = require('path')

const envPath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/.env.development')
const entries = [
  ['VITE_BACKEND_URL', 'http://localhost:8081'],
  ['VITE_UPLOAD_URL', 'http://[::1]:8080/uploadImage'],
  ['VITE_MAIN_BACKEND_URL', 'http://localhost:8080']
]

function run() {
  let s = ''
  if (fs.existsSync(envPath)) {
    s = fs.readFileSync(envPath, 'utf8')
  }
  let lines = s.split(/\r?\n/).filter(Boolean)
  for (const [key, value] of entries) {
    const idx = lines.findIndex(l => l.startsWith(key + '='))
    if (idx >= 0) {
      lines[idx] = `${key}=${value}`
    } else {
      lines.push(`${key}=${value}`)
    }
  }
  const out = lines.join('\n') + '\n'
  fs.writeFileSync(envPath, out)
  console.log('Updated keys in .env.development:', entries.map(([k]) => k).join(', '))
}

run()

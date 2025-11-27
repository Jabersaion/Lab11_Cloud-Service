const fs = require('fs')
const path = require('path')

const envPath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/.env.development')

function run() {
  if (!fs.existsSync(envPath)) {
    console.error('env file not found at', envPath)
    process.exit(1)
  }
  let s = fs.readFileSync(envPath, 'utf8')
  s = s.replace(/VITE_MAIN_BACKEND_URL=.*/g, 'VITE_MAIN_BACKEND_URL=http://localhost:8081')
  fs.writeFileSync(envPath, s)
  console.log('Updated VITE_MAIN_BACKEND_URL to http://localhost:8081')
}

run()


const fs = require('fs')
const path = require('path')

const typesPath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/src/types.ts')

function run() {
  if (!fs.existsSync(typesPath)) {
    console.error('types.ts not found at', typesPath)
    process.exit(1)
  }
  let s = fs.readFileSync(typesPath, 'utf8')
  s = s.replace('petsAllowed: boolean', 'petAllowed: boolean')
  fs.writeFileSync(typesPath, s)
  console.log('Updated Event type: petsAllowed -> petAllowed')
}

run()


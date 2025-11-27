const fs = require('fs')
const path = require('path')

const appVuePath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/src/App.vue')

function run() {
  if (!fs.existsSync(appVuePath)) {
    console.error('App.vue not found at', appVuePath)
    process.exit(1)
  }
  let s = fs.readFileSync(appVuePath, 'utf8')
  if (s.includes("name: 'organizer-list'")) {
    console.log('Organizer link already present')
    return
  }
  const insert = `|
          <RouterLink
            class="font-bold text-gray-700"
            exact-active-class="text-green-500"
            :to="{ name: 'organizer-list' }"
          >Organizer</RouterLink>
          `
  if (s.includes('</nav>')) {
    s = s.replace('</nav>', `${insert}\n        </nav>`)
    fs.writeFileSync(appVuePath, s)
    console.log('Inserted Organizer link into App.vue')
  } else {
    console.error('No </nav> tag found in App.vue')
    process.exit(2)
  }
}

run()


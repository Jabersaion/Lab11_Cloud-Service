const fs = require('fs')
const path = require('path')

const detailPath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/src/views/event/DetailView.vue')

const block = `\n  <div v-if=\"event?.images?.length\">\n    <div class=\"grid grid-cols-2 gap-4\">\n      <img v-for=\"(img, idx) in event.images\" :key=\"idx\" :src=\"img\" class=\"rounded-md object-cover w-full h-48\" />\n    </div>\n  </div>\n`

function run() {
  if (!fs.existsSync(detailPath)) {
    console.error('DetailView.vue not found at', detailPath)
    process.exit(1)
  }
  let s = fs.readFileSync(detailPath, 'utf8')
  if (s.includes('event?.images') || s.includes('v-for="(img, idx) in event.images"')) {
    console.log('Images block already present')
    return
  }
  if (s.includes('</template>')) {
    s = s.replace('</template>', `${block}\n</template>`)
    fs.writeFileSync(detailPath, s)
    console.log('Inserted images block into Event DetailView.vue')
  } else {
    console.error('No </template> tag found in DetailView.vue')
    process.exit(2)
  }
}

run()


const fs = require('fs')
const path = require('path')

const detailPath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/src/views/event/DetailView.vue')

function run() {
  if (!fs.existsSync(detailPath)) {
    console.error('DetailView.vue not found at', detailPath)
    process.exit(1)
  }
  let s = fs.readFileSync(detailPath, 'utf8')
  s = s.replace(/<template>[\s\S]*?<p>.*?<\/p>/, (m) => {
    return `<template>\n  <p>{{ event.time }} on {{ event.date }} @ {{ event.location }}</p>\n  <p>{{ event.description }}</p>`
  })
  fs.writeFileSync(detailPath, s)
  console.log('DetailView.vue updated header lines')
}

run()

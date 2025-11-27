const fs = require('fs')
const path = require('path')

const formPath = path.resolve('D:/study/Component-based Software Development/code/lab-frontend-first-part/src/views/EventFormView.vue')

const content = `
<script setup lang="ts">
import type { Event, Organizer } from "@/types"
import { ref, onMounted } from "vue"
import EventService from "@/services/EventService"
import OrganizerService from "@/services/OrganizerService"
import { useRouter } from "vue-router"
import { useMessageStore } from "@/stores/message"
import BaseInput from "@/components/BaseInput.vue"
import BaseSelect from "@/components/BaseSelect.vue"
import ImageUpload from "@/components/ImageUpload.vue"

const event = ref<Event>({ id: 0, category: "", title: "", description: "", location: "", date: "", time: "", petAllowed: false, organizer: { id: 0, name: "" }, images: [] })
const organizers = ref<Organizer[]>([])
const router = useRouter()
const store = useMessageStore()

function saveEvent() {
  EventService.saveEvent(event.value)
    .then((response) => {
      store.updateMessage("Event " + response.data.title + " created")
      setTimeout(() => store.resetMessage(), 3000)
      router.push({ name: "event-detail-view", params: { id: response.data.id } })
    })
    .catch(() => {
      router.push({ name: "network-error-view" })
    })
}

onMounted(() => {
  OrganizerService.getOrganizers()
    .then((response) => { organizers.value = response.data })
    .catch(() => { organizers.value = [] })
})
</script>

<template>
  <form @submit.prevent="saveEvent">
    <BaseInput v-model="event.title" type="text" label="Title" />
    <BaseInput v-model="event.category" type="text" label="Category" />
    <BaseInput v-model="event.description" type="text" label="Description" />
    <BaseInput v-model="event.location" type="text" label="Location" />
    <BaseInput v-model="event.date" type="text" label="Date" />
    <BaseInput v-model="event.time" type="text" label="Time" />
    <label class="block mb-1">Pets Allowed</label>
    <input v-model="event.petAllowed" type="checkbox" class="mb-4" />
    <BaseSelect v-model="event.organizer.id" :options="organizers" label="Organizer" />
    <label class="block mt-4 mb-1">Images</label>
    <ImageUpload v-model="event.images" />
    <button class="button" type="submit">Submit</button>
  </form>
</template>

<style scoped>
.button {
  padding: 10px 16px;
  background-color: #41b883;
  border: none;
  color: white;
  cursor: pointer;
}
.button:hover { filter: brightness(1.05); }
</style>
`

function run() {
  if (!fs.existsSync(formPath)) {
    console.error('EventFormView.vue not found at', formPath)
    process.exit(1)
  }
  fs.writeFileSync(formPath, content)
  console.log('EventFormView.vue updated with full fields and ImageUpload')
}

run()

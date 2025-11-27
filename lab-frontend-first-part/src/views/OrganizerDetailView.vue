<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getOrganizer } from '@/services/OrganizerService'
import type { Organizer } from '@/types'

const route = useRoute()
const organizer = ref<Organizer | null>(null)

onMounted(async () => {
  const id = Number(route.params.id)
  organizer.value = await getOrganizer(id)
})
</script>
<template>
  <div class="max-w-2xl mx-auto px-6 py-8">
    <h1 class="text-2xl font-semibold">{{ organizer?.name }}</h1>
    <div class="text-gray-600 mt-2">{{ organizer?.address }}</div>
    <div class="mt-6" v-if="organizer?.image">
      <img :src="organizer?.image" alt="Organizer" class="max-w-sm rounded shadow" />
    </div>
  </div>
</template>


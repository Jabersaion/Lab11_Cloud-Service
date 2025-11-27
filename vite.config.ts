import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import VueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [vue(), VueDevTools()],
  server: {
    host: true,
    proxy: {
      '/events': { target: 'http://localhost:8080', changeOrigin: true },
      '/organizers': { target: 'http://localhost:8080', changeOrigin: true },
      '/organizer': { target: 'http://localhost:8080', changeOrigin: true },
      '/uploadImage': { target: 'http://localhost:8080', changeOrigin: true }
    }
  },
  resolve: {
    alias: { '@': fileURLToPath(new URL('./src', import.meta.url)) }
  }
})
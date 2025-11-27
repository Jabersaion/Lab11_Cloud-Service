import axios from 'axios'
import type { Organizer } from '@/types'

const API = import.meta.env.VITE_MAIN_BACKEND_URL || import.meta.env.VITE_BACKEND_URL

export async function getOrganizer(id: number): Promise<Organizer> {
  const res = await axios.get(`${API}/organizer/${id}`)
  return res.data
}


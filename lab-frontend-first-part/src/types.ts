export interface Organizer {
  id: number
  name: string
  address: string
  image?: string
  ownEvents?: Event[]
}

export interface Event {
  id: number
  category: string
  title: string
  description: string
  location: string
  date: string
  time: string
  petAllowed: boolean
  organizer: Organizer
  images: string[]
}


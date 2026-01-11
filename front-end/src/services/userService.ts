import api from './api'
import { ApiResponse } from '@/types/api'

export interface UserStats {
  booksRated: number
  averageRating: number
  totalRatingCount: number
  readingLevel: string
}

export const userService = {
  getUserStats() {
    return api.get<ApiResponse<UserStats>>('/user/stats')
  },

  getUserProfile() {
    return api.get<ApiResponse<any>>('/user/profile')
  },

  updateProfile(data: any) {
    return api.put('/user/profile', data)
  },
}


import { useQuery } from '@tanstack/react-query'
import { userService } from '@/services/userService'

export const useUserStats = () => {
  return useQuery({
    queryKey: ['userStats'],
    queryFn: async () => {
      try {
        const response = await userService.getUserStats()
        return response.data.body
      } catch (error) {
        console.error('Error fetching user stats:', error)
        // Return default stats if endpoint doesn't exist
        return {
          booksRated: 0,
          averageRating: 0,
          totalRatingCount: 0,
          readingLevel: 'Not set',
        }
      }
    },
    staleTime: 1000 * 60 * 5, // 5 minutes
  })
}


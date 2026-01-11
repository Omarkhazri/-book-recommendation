import { useQuery } from '@tanstack/react-query'
import { bookService } from '@/services/bookService'

export interface BookRating {
  id: number
  user: {
    id: number
    firstName: string
    lastName: string
  }
  book: {
    id: number
    name: string
  }
  rate: number
  comment?: string
  createdDate?: string
}

export const useBookRatings = (bookId: number) => {
  return useQuery({
    queryKey: ['bookRatings', bookId],
    queryFn: async () => {
      try {
        const response = await bookService.getBookRatings(bookId)
        return (response.data.body as BookRating[]) || []
      } catch (error) {
        console.error('Error fetching book ratings:', error)
        return []
      }
    },
    staleTime: 1000 * 60 * 5, // 5 minutes
  })
}


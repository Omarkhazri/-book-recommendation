import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { bookService } from '@/services/bookService'
import { Book, BookCategory, PaginationRequest } from '@/types/api'

export const useBookCategories = () => {
  return useQuery({
    queryKey: ['categories'],
    queryFn: async () => {
      const response = await bookService.getCategories()
      return response.data.body
    },
  })
}

export const useBooks = (filters: any, pagination: PaginationRequest) => {
  return useQuery({
    queryKey: ['books', filters, pagination],
    queryFn: async () => {
      const response = await bookService.getBooks(filters, pagination)
      const data = response.data.body

      // Transform backend response to expected frontend structure
      // Backend returns: result, totalNumberOfPages, totalNumberOfElements, pageNumber
      // Frontend expects: content, totalPages, totalElements, currentPage
      return {
        content: data?.result || data?.content || [],
        totalPages: data?.totalNumberOfPages || data?.totalPages || 0,
        totalElements: data?.totalNumberOfElements || data?.totalElements || 0,
        currentPage: data?.pageNumber ? data.pageNumber - 1 : 0, // Convert from 1-based to 0-based
      }
    },
    staleTime: 1000 * 60 * 5, // 5 minutes
  })
}

export const useBook = (bookId: number) => {
  return useQuery({
    queryKey: ['book', bookId],
    queryFn: async () => {
      const response = await bookService.getBookById(bookId)
      return response.data.body
    },
  })
}

export const useRecommendations = () => {
  return useQuery({
    queryKey: ['recommendations'],
    queryFn: async () => {
      const response = await bookService.getRecommendations()
      return response.data.body
    },
    staleTime: 1000 * 60 * 10, // 10 minutes
  })
}

export const useRateBook = () => {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: ({ bookId, rate, comment }: { bookId: number; rate: number; comment?: string }) =>
      bookService.rateBook(bookId, rate, comment),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['recommendations'] })
      queryClient.invalidateQueries({ queryKey: ['books'] })
    },
  })
}

export const useBooksByAuthor = (authorId: number) => {
  return useQuery({
    queryKey: ['booksByAuthor', authorId],
    queryFn: async () => {
      const response = await bookService.getBooksByAuthor(authorId)
      return response.data.body
    },
  })
}


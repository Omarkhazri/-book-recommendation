import api from './api'
import {
  Book,
  BookCategory,
  ApiResponse,
  BookFilterPaginationRequest,
  PaginationRequest,
  PaginatedResponse,
} from '@/types/api'

export const bookService = {
  getCategories() {
    return api.get<ApiResponse<BookCategory[]>>('/book/find-all-categories')
  },

  getBooks(filters: Partial<BookFilterPaginationRequest>, pagination: PaginationRequest) {
    // Transform categoryId to categoryIds (Array of IDs)
    const transformedFilters: any = {}

    if (filters && filters.categoryId) {
      // Send as array, not Set (Set doesn't serialize to JSON properly)
      transformedFilters.categoryIds = [filters.categoryId]
    }

    console.log('Sending to backend:', {
      criteria: transformedFilters,
      paginationRequest: pagination,
      deletedRecords: false
    })

    return api.post<ApiResponse<PaginatedResponse<Book>>>('/book/find-all-paginated-filtered', {
      criteria: transformedFilters,  // Changed from filterRequest to criteria
      paginationRequest: pagination,
      deletedRecords: false,  // Explicitly set deletedRecords
    })
  },

  getBookById(id: number) {
    return api.get<ApiResponse<Book>>(`/book/find-by-id/${id}`)
  },

  getRecommendations() {
    return api.get<ApiResponse<Book[]>>('/book/find-all-recommended')
  },

  rateBook(bookId: number, rate: number, comment?: string) {
    return api.post<ApiResponse<any>>('/book/rate', {
      book: { id: bookId },  // Transform bookId to book object with id
      rate,
      comment,
    })
  },

  getBookRatings(bookId: number) {
    return api.get<ApiResponse<any[]>>(`/book/${bookId}/ratings`)
  },

  getBooksByAuthor(authorId: number) {
    return api.get<ApiResponse<Book[]>>(`/book/find-all-by-author-id/${authorId}`)
  },
}


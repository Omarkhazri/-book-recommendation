// API Response Types
export interface ApiResponse<T> {
  success: boolean
  timestamp: string
  message: string
  body: T
}

export interface AuthResponse {
  accessToken: string
  refreshToken: string
  tokenType: string
}

// User Types
export interface User {
  id: number
  firstName: string
  lastName: string
  email: string
  phoneNumber?: string
  birthdate?: string
  country?: string
  age?: number
  gender?: 'MALE' | 'FEMALE' | 'OTHERS'
  maritalStatus?: 'SINGLE' | 'MARRIED' | 'IN_RELATIONSHIP'
  imageUrl?: string
  createdDate?: string
  modifiedDate?: string
}

export interface UserRegisterRequest {
  firstName: string
  lastName: string
  email: string
  password: string
  phoneNumber?: string
  birthdate?: string
  country?: string
  age?: number
  gender?: string
  maritalStatus?: string
  imageUrl?: string
}

export interface UserLoginRequest {
  email: string
  password: string
}

// Book Types
export interface Author {
  id: number
  name: string
  description: string
  birthdate: string
  deathdate?: string
  country: string
  age: number
  gender: string
  imageUrl: string
}

export interface BookCategory {
  id: number
  name: string
  description?: string
}

export interface Book {
  id: number
  name: string
  author: Author
  category: BookCategory
  rate: number
  usersRateCount: number
  price: number
  pagesNumber: number
  description?: string
  imageUrl?: string
  publishedDate?: string
  createdDate?: string
}

export interface UserBookRate {
  id: number
  userId: number
  bookId: number
  rate: number
  comment?: string
}

// Pagination Types
export interface PaginationRequest {
  pageNumber: number
  pageSize: number
  sortBy?: string
  sortDirection?: 'ASC' | 'DESC'
}

export interface PaginatedResponse<T> {
  content: T[]
  totalElements: number
  totalPages: number
  currentPage: number
}

export interface BookFilterPaginationRequest {
  categoryId?: number
  authorId?: number
  minPrice?: number
  maxPrice?: number
}

export interface FilterPaginationRequest<T> {
  filterRequest: T
  paginationRequest: PaginationRequest
}

// Reading Info Types
export interface UserReadingInfo {
  id: number
  user: User
  readingLevel: 'BEGINNER' | 'INTERMEDIATE' | 'ADVANCED' | 'EXPERT'
  userBookCategories: UserBookCategory[]
}

export interface UserBookCategory {
  id: number
  bookCategory: BookCategory
  preferenceLevel: number
}


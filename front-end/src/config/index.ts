export const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8010/book-service/api'

export const config = {
  api: {
    baseURL: API_BASE_URL,
    timeout: 10000,
  },
  auth: {
    accessTokenExpiry: 3600000, // 1 hour
    refreshTokenExpiry: 86400000, // 24 hours
  },
  pagination: {
    defaultPageSize: 12,
  },
  features: {
    recommendationsEnabled: true,
    ratingsEnabled: true,
  },
}

export default config


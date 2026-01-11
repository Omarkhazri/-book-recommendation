import api from './api'
import { User, UserLoginRequest, UserRegisterRequest, AuthResponse, ApiResponse } from '@/types/api'

export const authService = {
  register(userData: UserRegisterRequest) {
    return api.post<ApiResponse<User>>('/user', userData)
  },

  login(credentials: UserLoginRequest) {
    return api.post<ApiResponse<AuthResponse>>('/auth/log-in', credentials)
  },

  getCurrentUser() {
    return api.get<ApiResponse<User>>('/auth/current')
  },

  refreshToken(refreshToken: string) {
    return api.post<ApiResponse<AuthResponse>>('/auth/refresh-token', {
      refreshToken,
    })
  },

  logout() {
    localStorage.removeItem('accessToken')
    localStorage.removeItem('refreshToken')
  },
}


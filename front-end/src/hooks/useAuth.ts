import { useNavigate } from 'react-router-dom'
import { useAuthStore } from '@/store/authStore'
import { authService } from '@/services/authService'

export const useAuth = () => {
  const navigate = useNavigate()
  const { isLoggedIn, user, logout, setAuth, setUser } = useAuthStore()

  const login = async (email: string, password: string) => {
    try {
      const response = await authService.login({ email, password })
      const { accessToken, refreshToken } = response.data.body
      setAuth(accessToken, refreshToken)
      localStorage.setItem('accessToken', accessToken)
      localStorage.setItem('refreshToken', refreshToken)
      navigate('/books')
      return true
    } catch (error) {
      console.error('Login failed:', error)
      return false
    }
  }

  const register = async (userData: any) => {
    try {
      const response = await authService.register(userData)
      const user = response.data.body
      setUser(user)
      navigate('/login')
      return true
    } catch (error) {
      console.error('Registration failed:', error)
      return false
    }
  }

  const handleLogout = () => {
    logout()
    authService.logout()
    localStorage.removeItem('accessToken')
    localStorage.removeItem('refreshToken')
    navigate('/login')
  }

  const getCurrentUser = async () => {
    try {
      const response = await authService.getCurrentUser()
      setUser(response.data.body)
      return response.data.body
    } catch (error) {
      console.error('Failed to get current user:', error)
      return null
    }
  }

  return {
    isLoggedIn,
    user,
    login,
    register,
    logout: handleLogout,
    getCurrentUser,
  }
}


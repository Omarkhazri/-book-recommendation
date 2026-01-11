import { useEffect } from 'react'
import api from '@/services/api'

/**
 * Hook pour tester la connexion API
 * Utile pour diagnostiquer les problèmes de communication frontend-backend
 */
export const useApiTest = () => {
  useEffect(() => {
    const testApi = async () => {
      try {
        console.log('🔍 Testing API connection...')
        console.log('API Base URL:', import.meta.env.VITE_API_URL)

        // Test 1: Categories
        console.log('\n📚 Test 1: Fetching categories...')
        const categoriesResponse = await api.get('/book/find-all-categories')
        console.log('✅ Categories Response:', categoriesResponse.data)

        // Test 2: Books with pagination
        console.log('\n📖 Test 2: Fetching books with pagination...')
        const booksResponse = await api.post('/book/find-all-paginated-filtered', {
          filterRequest: {},
          paginationRequest: {
            pageNumber: 0,
            pageSize: 12,
            sortBy: 'name',
            sortDirection: 'ASC',
          },
        })
        console.log('✅ Books Response:', booksResponse.data)

        // Test 3: Check structure
        if (booksResponse.data.body?.content) {
          console.log(`\n✅ Found ${booksResponse.data.body.content.length} books`)
          console.log('First book:', booksResponse.data.body.content[0])
        } else {
          console.error('❌ Response structure incorrect:', booksResponse.data)
        }
      } catch (error) {
        console.error('❌ API Error:', error)
      }
    }

    testApi()
  }, [])

  return null
}


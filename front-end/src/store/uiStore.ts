import { create } from 'zustand'

interface UIState {
  searchQuery: string
  selectedCategoryId: number | null
  selectedMinPrice: number | null
  selectedMaxPrice: number | null
  isLoading: boolean
  error: string | null

  setSearchQuery: (query: string) => void
  setSelectedCategoryId: (id: number | null) => void
  setPriceRange: (min: number | null, max: number | null) => void
  setIsLoading: (loading: boolean) => void
  setError: (error: string | null) => void
  clearFilters: () => void
}

export const useUIStore = create<UIState>((set) => ({
  searchQuery: '',
  selectedCategoryId: null,
  selectedMinPrice: null,
  selectedMaxPrice: null,
  isLoading: false,
  error: null,

  setSearchQuery: (query) => set({ searchQuery: query }),
  setSelectedCategoryId: (id) => set({ selectedCategoryId: id }),
  setPriceRange: (min, max) => set({ selectedMinPrice: min, selectedMaxPrice: max }),
  setIsLoading: (loading) => set({ isLoading: loading }),
  setError: (error) => set({ error }),
  clearFilters: () =>
    set({
      searchQuery: '',
      selectedCategoryId: null,
      selectedMinPrice: null,
      selectedMaxPrice: null,
      error: null,
    }),
}))


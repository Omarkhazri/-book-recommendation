import React from 'react'
import { useRecommendations } from '@/hooks/useBooks'
import { BookCard } from '@/components/books/BookCard'

export const RecommendationsPage: React.FC = () => {
  const { data: recommendations, isLoading, error } = useRecommendations()

  if (isLoading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="inline-block animate-spin rounded-full h-16 w-16 border-b-2 border-blue-600"></div>
          <p className="text-gray-600 mt-4">Loading recommendations...</p>
        </div>
      </div>
    )
  }

  if (error) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-lg max-w-md">
          <p className="font-bold">Error loading recommendations</p>
          <p>Please try again later</p>
        </div>
      </div>
    )
  }

  const books = recommendations || []

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="mb-8">
          <h1 className="text-4xl font-bold text-gray-800 mb-2">🤖 Recommended for You</h1>
          <p className="text-gray-600">
            Based on your ratings and preferences, here are books we think you'll love
          </p>
        </div>

        {/* Recommendations Grid */}
        {books.length === 0 ? (
          <div className="text-center py-12">
            <p className="text-gray-500 text-lg mb-4">
              No recommendations yet. Start rating books to get personalized recommendations!
            </p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
            {books.map((book) => (
              <BookCard key={book.id} book={book} />
            ))}
          </div>
        )}
      </div>
    </div>
  )
}


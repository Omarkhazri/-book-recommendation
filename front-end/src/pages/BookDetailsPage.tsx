import React, { useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { useQuery } from '@tanstack/react-query'
import { bookService } from '@/services/bookService'
import { useRateBook } from '@/hooks/useBooks'
import { useBookRatings } from '@/hooks/useBookRatings'

export const BookDetailsPage: React.FC = () => {
  const { bookId } = useParams<{ bookId: string }>()
  const navigate = useNavigate()
  const [showRating, setShowRating] = useState(false)
  const [rating, setRating] = useState(0)
  const [comment, setComment] = useState('')
  const { mutate: rateBook, isPending } = useRateBook()

  const { data: book, isLoading, error } = useQuery({
    queryKey: ['book', bookId],
    queryFn: async () => {
      const response = await bookService.getBookById(Number(bookId))
      return response.data.body
    },
  })

  const { data: ratings = [] } = useBookRatings(Number(bookId) || 0)

  const handleRate = (rate: number) => {
    setRating(rate)
    rateBook(
      { bookId: Number(bookId), rate, comment: comment || undefined },
      {
        onSuccess: () => {
          setShowRating(false)
          setRating(0)
          setComment('')
        },
      }
    )
  }

  if (isLoading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
          <p className="text-gray-600 mt-4">Loading book details...</p>
        </div>
      </div>
    )
  }

  if (error || !book) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <p className="text-red-600 text-lg">Book not found</p>
          <button
            onClick={() => navigate('/books')}
            className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
          >
            Back to Books
          </button>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Back Button */}
        <button
          onClick={() => navigate('/books')}
          className="mb-6 px-4 py-2 text-blue-600 hover:text-blue-700 font-medium flex items-center gap-2"
        >
          ← Back to Books
        </button>

        {/* Main Content */}
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Left Column - Book Image & Rating */}
          <div className="lg:col-span-1">
            {/* Book Cover */}
            <div className="bg-white rounded-lg shadow-lg overflow-hidden sticky top-8">
              <div className="relative h-80 bg-gray-200">
                {book.imageUrl ? (
                  <img
                    src={book.imageUrl}
                    alt={book.name}
                    className="w-full h-full object-cover"
                  />
                ) : (
                  <div className="w-full h-full flex items-center justify-center bg-gradient-to-br from-blue-100 to-blue-200">
                    <span className="text-6xl">📕</span>
                  </div>
                )}
              </div>

              {/* Book Stats */}
              <div className="p-6 space-y-6">
                {/* Rating */}
                <div>
                  <h3 className="text-sm font-semibold text-gray-700 mb-3">Rating</h3>
                  <div className="flex items-center gap-3">
                    <div className="flex items-center gap-1">
                      <span className="text-3xl text-yellow-400">⭐</span>
                      <span className="text-2xl font-bold text-gray-800">{book.rate.toFixed(1)}</span>
                    </div>
                    <div className="text-sm text-gray-600">
                      <p>({book.usersRateCount} reviews)</p>
                    </div>
                  </div>
                </div>

                {/* Price */}
                <div>
                  <h3 className="text-sm font-semibold text-gray-700 mb-2">Price</h3>
                  <p className="text-3xl font-bold text-blue-600">${book.price.toFixed(2)}</p>
                </div>

                {/* Rating Button */}
                {showRating ? (
                  <div className="space-y-3">
                    <p className="text-sm font-semibold text-gray-700">Rate this book:</p>
                    <div className="flex gap-2">
                      {[1, 2, 3, 4, 5].map((star) => (
                        <button
                          key={star}
                          onClick={() => handleRate(star)}
                          disabled={isPending}
                          className={`text-3xl transition ${
                            star <= rating ? 'text-yellow-400' : 'text-gray-300 hover:text-yellow-300'
                          } disabled:opacity-50`}
                        >
                          ★
                        </button>
                      ))}
                    </div>
                    <textarea
                      value={comment}
                      onChange={(e) => setComment(e.target.value)}
                      placeholder="Add a comment about this book (optional)..."
                      className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 resize-none"
                      rows={3}
                    />
                    {rating > 0 && (
                      <button
                        onClick={() => handleRate(rating)}
                        disabled={isPending}
                        className="w-full px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium disabled:opacity-50"
                      >
                        {isPending ? 'Submitting...' : 'Submit Rating'}
                      </button>
                    )}
                    <button
                      onClick={() => {
                        setShowRating(false)
                        setRating(0)
                        setComment('')
                      }}
                      className="w-full text-xs text-gray-500 hover:text-gray-700 py-1"
                    >
                      Cancel
                    </button>
                  </div>
                ) : (
                  <button
                    onClick={() => setShowRating(true)}
                    className="w-full px-4 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium"
                  >
                    Rate This Book
                  </button>
                )}
              </div>
            </div>
          </div>

          {/* Right Column - Book Details */}
          <div className="lg:col-span-2 space-y-6">
            {/* Title & Category */}
            <div className="bg-white rounded-lg shadow-lg p-8">
              <div className="mb-4">
                <span className="inline-block px-3 py-1 bg-blue-100 text-blue-800 rounded-full text-sm font-semibold">
                  {book.category.name}
                </span>
              </div>
              <h1 className="text-4xl font-bold text-gray-900 mb-2">{book.name}</h1>
              <p className="text-lg text-gray-600">by {book.author.name}</p>
            </div>

            {/* Description */}
            {book.description && (
              <div className="bg-white rounded-lg shadow-lg p-8">
                <h2 className="text-2xl font-bold text-gray-900 mb-4">Description</h2>
                <p className="text-gray-700 leading-relaxed whitespace-pre-wrap">{book.description}</p>
              </div>
            )}

            {/* Book Info Grid */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              {/* Pages */}
              <div className="bg-white rounded-lg shadow-lg p-6">
                <h3 className="text-sm font-semibold text-gray-700 mb-2">Pages</h3>
                <p className="text-2xl font-bold text-gray-900">{book.pagesNumber}</p>
              </div>

              {/* Published Date */}
              {book.publishedDate && (
                <div className="bg-white rounded-lg shadow-lg p-6">
                  <h3 className="text-sm font-semibold text-gray-700 mb-2">Published</h3>
                  <p className="text-2xl font-bold text-gray-900">
                    {new Date(book.publishedDate).toLocaleDateString('en-US', {
                      year: 'numeric',
                      month: 'long',
                      day: 'numeric',
                    })}
                  </p>
                </div>
              )}
            </div>

            {/* Author Details */}
            <div className="bg-white rounded-lg shadow-lg p-8">
              <h2 className="text-2xl font-bold text-gray-900 mb-6">About the Author</h2>
              <div className="flex gap-6">
                {/* Author Image */}
                <div className="flex-shrink-0">
                  <div className="h-32 w-32 bg-gray-200 rounded-lg overflow-hidden">
                    {book.author.imageUrl ? (
                      <img
                        src={book.author.imageUrl}
                        alt={book.author.name}
                        className="w-full h-full object-cover"
                      />
                    ) : (
                      <div className="w-full h-full flex items-center justify-center bg-gradient-to-br from-purple-100 to-purple-200">
                        <span className="text-3xl">👤</span>
                      </div>
                    )}
                  </div>
                </div>

                {/* Author Info */}
                <div className="flex-grow">
                  <h3 className="text-xl font-bold text-gray-900 mb-2">{book.author.name}</h3>

                  <div className="grid grid-cols-2 gap-4 mb-4 text-sm">
                    <div>
                      <p className="text-gray-600">Age</p>
                      <p className="font-semibold text-gray-900">{book.author.age}</p>
                    </div>
                    <div>
                      <p className="text-gray-600">Country</p>
                      <p className="font-semibold text-gray-900">{book.author.country}</p>
                    </div>
                    <div>
                      <p className="text-gray-600">Gender</p>
                      <p className="font-semibold text-gray-900">{book.author.gender}</p>
                    </div>
                    {book.author.birthdate && (
                      <div>
                        <p className="text-gray-600">Born</p>
                        <p className="font-semibold text-gray-900">
                          {new Date(book.author.birthdate).toLocaleDateString('en-US', {
                            year: 'numeric',
                            month: 'short',
                            day: 'numeric',
                          })}
                        </p>
                      </div>
                    )}
                  </div>

                  {book.author.description && (
                    <div>
                      <p className="text-gray-700 leading-relaxed">{book.author.description}</p>
                    </div>
                  )}
                </div>
              </div>
            </div>

            {/* Category Details */}
            <div className="bg-white rounded-lg shadow-lg p-8">
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Category</h2>
              <h3 className="text-xl font-semibold text-gray-900 mb-2">{book.category.name}</h3>
              {book.category.description && (
                <p className="text-gray-700 leading-relaxed">{book.category.description}</p>
              )}
            </div>

            {/* Comments & Ratings Section */}
            <div className="bg-white rounded-lg shadow-lg p-8">
              <h2 className="text-2xl font-bold text-gray-900 mb-6">
                Reader Reviews ({ratings.length})
              </h2>

              {ratings.length === 0 ? (
                <p className="text-gray-600 text-center py-8">
                  No reviews yet. Be the first to rate this book!
                </p>
              ) : (
                <div className="space-y-6">
                  {ratings.map((rating: any) => (
                    <div key={rating.id} className="border-l-4 border-blue-500 pl-6 py-4">
                      {/* Rating Header */}
                      <div className="flex items-center justify-between mb-2">
                        <div>
                          <p className="font-semibold text-gray-900">
                            {rating.user.firstName} {rating.user.lastName}
                          </p>
                          <div className="flex items-center gap-2 mt-1">
                            {[1, 2, 3, 4, 5].map((star) => (
                              <span
                                key={star}
                                className={`text-lg ${
                                  star <= rating.rate
                                    ? 'text-yellow-400'
                                    : 'text-gray-300'
                                }`}
                              >
                                ★
                              </span>
                            ))}
                            <span className="ml-2 text-sm font-semibold text-gray-700">
                              {rating.rate}.0
                            </span>
                          </div>
                        </div>
                        {rating.createdDate && (
                          <span className="text-xs text-gray-500">
                            {new Date(rating.createdDate).toLocaleDateString('en-US', {
                              year: 'numeric',
                              month: 'short',
                              day: 'numeric',
                            })}
                          </span>
                        )}
                      </div>

                      {/* Comment */}
                      {rating.comment && (
                        <p className="text-gray-700 mt-3 leading-relaxed">
                          {rating.comment}
                        </p>
                      )}
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}


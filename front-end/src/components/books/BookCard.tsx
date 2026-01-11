import React from 'react'
import { useNavigate } from 'react-router-dom'
import { Book } from '@/types/api'
import { useState } from 'react'
import { useRateBook } from '@/hooks/useBooks'

interface BookCardProps {
  book: Book
  onRate?: () => void
}

export const BookCard: React.FC<BookCardProps> = ({ book, onRate }) => {
  const navigate = useNavigate()
  const [rating, setRating] = useState(0)
  const [showRating, setShowRating] = useState(false)
  const [comment, setComment] = useState('')
  const { mutate: rateBook, isPending } = useRateBook()

  const handleRate = (rate: number) => {
    setRating(rate)
    rateBook(
      { bookId: book.id, rate, comment: comment || undefined },
      {
        onSuccess: () => {
          setShowRating(false)
          setRating(0)
          setComment('')
          onRate?.()
        },
      }
    )
  }

  return (
    <div className="bg-white rounded-lg shadow-lg overflow-hidden hover:shadow-xl transition transform hover:scale-105 group cursor-pointer flex flex-col h-full">
      {/* Book Image - Clickable */}
      <div
        onClick={() => navigate(`/books/${book.id}`)}
        className="relative h-64 bg-gray-200 overflow-hidden group-hover:brightness-90 transition"
      >
        {book.imageUrl ? (
          <img
            src={book.imageUrl}
            alt={book.name}
            className="w-full h-full object-cover"
          />
        ) : (
          <div className="w-full h-full flex items-center justify-center bg-gradient-to-br from-blue-100 to-blue-200">
            <span className="text-4xl">📕</span>
          </div>
        )}
      </div>

      {/* Book Info */}
      <div className="p-4 flex-grow flex flex-col">
        {/* Title - Clickable */}
        <h3
          onClick={() => navigate(`/books/${book.id}`)}
          className="text-lg font-bold text-gray-800 line-clamp-2 hover:text-blue-600 transition cursor-pointer"
        >
          {book.name}
        </h3>

        {/* Author - Clickable */}
        <p
          onClick={() => navigate(`/books/${book.id}`)}
          className="text-sm text-gray-600 mt-1 hover:text-blue-600 transition cursor-pointer"
        >
          {book.author.name}
        </p>

        {/* Category */}
        <p className="text-xs text-gray-500 mt-1 inline-block px-2 py-1 bg-blue-100 text-blue-800 rounded">
          {book.category.name}
        </p>

        {/* Description Preview */}
        {book.description && (
          <p className="text-xs text-gray-600 mt-2 line-clamp-2">
            {book.description}
          </p>
        )}

        {/* Pages */}
        <p className="text-xs text-gray-500 mt-2">📄 {book.pagesNumber} pages</p>

        {/* Rating */}
        <div className="flex items-center justify-between mt-4 mb-4">
          <div className="flex items-center gap-1">
            <span className="text-xl text-yellow-400">⭐</span>
            <span className="text-sm font-semibold">{book.rate.toFixed(1)}</span>
            <span className="text-xs text-gray-500">({book.usersRateCount})</span>
          </div>
          <span className="text-lg font-bold text-blue-600">${book.price}</span>
        </div>

        {/* Spacer */}
        <div className="flex-grow"></div>

        {/* View Details Button */}
        <button
          onClick={() => navigate(`/books/${book.id}`)}
          className="w-full mb-2 px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition text-sm font-medium"
        >
          View Details
        </button>

        {/* Rating Buttons */}
        {showRating ? (
          <div className="space-y-2">
            <p className="text-xs font-semibold text-gray-700">Rate this book:</p>
            <div className="flex flex-wrap gap-2 justify-center">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  key={star}
                  onClick={() => handleRate(star)}
                  disabled={isPending}
                  className={`text-2xl transition ${
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
              placeholder="Add a comment (optional)..."
              className="w-full px-2 py-2 text-xs border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 resize-none"
              rows={2}
            />
            {rating > 0 && (
              <button
                onClick={() => handleRate(rating)}
                disabled={isPending}
                className="w-full px-3 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition text-xs font-medium disabled:opacity-50"
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
              className="text-xs text-gray-500 mt-1 w-full hover:text-gray-700"
            >
              Cancel
            </button>
          </div>
        ) : (
          <button
            onClick={() => setShowRating(true)}
            className="w-full px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition text-sm font-medium"
          >
            Rate Book
          </button>
        )}
      </div>
    </div>
  )
}


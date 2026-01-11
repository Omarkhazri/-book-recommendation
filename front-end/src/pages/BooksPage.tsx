import React, { useState, useEffect } from 'react'
import { BookCard } from '@/components/books/BookCard'
import { useBooks, useBookCategories } from '@/hooks/useBooks'
import { useDebugApi } from '@/hooks/useDebugApi'
import { Book, PaginationRequest} from '@/types/api'

export const BooksPage: React.FC = () => {
    const [pageNumber, setPageNumber] = useState(0)
    const [categoryId, setCategoryId] = useState<number | null>(null)
    const [apiError, setApiError] = useState<string | null>(null)
    const pageSize = 12

    const pagination: PaginationRequest = {
        pageNumber: Math.max(0, pageNumber),
        pageSize,
        sortBy: 'name',
        sortDirection: 'ASC',
    }

    const filters = categoryId ? {categoryId} : {}

    const {data: categoriesData, isLoading: categoriesLoading, error: categoriesError} = useBookCategories()
    const {data: booksData, isLoading: booksLoading, error: booksError, refetch} = useBooks(filters, pagination)

    // Debug logging
    useDebugApi('Categories', categoriesData, categoriesError, categoriesLoading)
    useDebugApi('Books', booksData, booksError, booksLoading)

    useEffect(() => {
        if (booksError) {
            console.error('Books Error Details:', booksError)
            setApiError(`Failed to load books: ${booksError instanceof Error ? booksError.message : 'Unknown error'}`)
        } else {
            setApiError(null)
        }
    }, [booksError])

    const categories = categoriesData || []
    const books = booksData?.content || []
    const totalPages = booksData?.totalPages || 0

    // ...existing code...
    const handlePreviousPage = () => {
        setPageNumber((prev) => Math.max(0, prev - 1))
    }

    const handleNextPage = () => {
        setPageNumber((prev) => {
            const nextPage = prev + 1
            return nextPage >= totalPages ? prev : nextPage
        })
    }

    const handlePageChange = (page: number) => {
        if (page >= 0 && page < totalPages) {
            setPageNumber(page)
        }
    }

    return (
        <div className="min-h-screen bg-gray-50 py-8">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                {/* Header */}
                <div className="mb-8">
                    <h1 className="text-4xl font-bold text-gray-800 mb-2">Books</h1>
                    <p className="text-gray-600">Explore our collection of books and find your next great read</p>
                </div>

                {/* Error Display */}
                {apiError && (
                    <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-lg mb-8">
                        <p className="font-bold">Error loading books</p>
                        <p>{apiError}</p>
                        <button
                            onClick={() => refetch()}
                            className="mt-2 px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700"
                        >
                            Retry
                        </button>
                    </div>
                )}

                {/* Filters */}
                <div className="bg-white rounded-lg shadow p-6 mb-8">
                    <h2 className="text-lg font-semibold text-gray-800 mb-4">Filter by Category</h2>
                    <div className="flex flex-wrap gap-2">
                        <button
                            onClick={() => {
                                setCategoryId(null)
                                setPageNumber(0)
                            }}
                            className={`px-4 py-2 rounded-lg transition ${
                                categoryId === null
                                    ? 'bg-blue-600 text-white'
                                    : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                            }`}
                        >
                            All
                        </button>

                        {categoriesLoading ? (
                            <span className="text-gray-500">Loading categories...</span>
                        ) : (
                            categories.map((category) => (
                                <button
                                    key={category.id}
                                    onClick={() => {
                                        setCategoryId(categoryId === category.id ? null : category.id)
                                        setPageNumber(0)
                                    }}
                                    className={`px-4 py-2 rounded-lg transition ${
                                        categoryId === category.id
                                            ? 'bg-blue-600 text-white'
                                            : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                                    }`}
                                >
                                    {category.name}
                                </button>
                            ))
                        )}
                    </div>
                </div>

                {/* Books Grid */}
                {booksLoading ? (
                    <div className="flex justify-center items-center h-64">
                        <div className="text-center">
                            <div
                                className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
                            <p className="text-gray-600 mt-4">Loading books...</p>
                        </div>
                    </div>
                ) : books.length === 0 ? (
                    <div className="text-center py-12">
                        <p className="text-gray-500 text-lg">No books found</p>
                        <p className="text-gray-400 text-sm mt-2">
                            {categoryId ? 'Try selecting a different category' : 'Check back later for more books'}
                        </p>
                    </div>
                ) : (
                    <>
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6 mb-8">
                            {books.map((book: Book) => (
                <BookCard key={book.id} book={book} onRate={() => refetch()} />
              ))}
            </div>
            {/* Pagination */}
            {totalPages > 1 && (
              <div className="flex justify-center items-center gap-2 mt-8">
                <button
                  onClick={handlePreviousPage}
                  disabled={pageNumber === 0}
                  className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  Previous
                </button>

                <div className="flex gap-1">
                  {Array.from({ length: Math.min(5, totalPages) }).map((_, i) => {
                    let pageNum = i

                    if (totalPages > 5) {
                      if (pageNumber <= 2) {
                        pageNum = i
                      } else if (pageNumber >= totalPages - 3) {
                        pageNum = totalPages - 5 + i
                      } else {
                        pageNum = pageNumber - 2 + i
                      }
                    }

                    if (pageNum < 0 || pageNum >= totalPages) {
                      return null
                    }

                    return (
                      <button
                        key={pageNum}
                        onClick={() => handlePageChange(pageNum)}
                        className={`px-3 py-2 rounded-lg transition ${
                          pageNum === pageNumber
                            ? 'bg-blue-600 text-white'
                            : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                        }`}
                      >
                        {pageNum + 1}
                      </button>
                    )
                  })}
                </div>

                <button
                  onClick={handleNextPage}
                  disabled={pageNumber >= totalPages - 1}
                  className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  Next
                </button>
              </div>
            )}
          </>
        )}
      </div>
    </div>
  )
}


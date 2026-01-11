import React from 'react'
import { useNavigate } from 'react-router-dom'

export const HomePage: React.FC = () => {
  const navigate = useNavigate()

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-blue-50">
      {/* Hero Section */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20">
        <div className="text-center mb-16">
          <span className="text-7xl mb-6 block">📚</span>
          <h1 className="text-5xl md:text-6xl font-bold text-gray-900 mb-6">
            Discover Your Next <span className="text-blue-600">Great Read</span>
          </h1>
          <p className="text-xl md:text-2xl text-gray-600 max-w-2xl mx-auto mb-8">
            Get personalized book recommendations powered by AI. Rate books you love and discover new favorites based on your preferences.
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <button
              onClick={() => navigate('/login')}
              className="px-8 py-4 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition text-lg"
            >
              Sign In
            </button>
            <button
              onClick={() => navigate('/register')}
              className="px-8 py-4 bg-white text-blue-600 font-semibold rounded-lg border-2 border-blue-600 hover:bg-blue-50 transition text-lg"
            >
              Create Account
            </button>
          </div>
        </div>

        {/* Features Section */}
        <div className="grid md:grid-cols-3 gap-8 mt-20">
          <div className="bg-white rounded-lg shadow-lg p-8 text-center">
            <span className="text-4xl mb-4 block">🤖</span>
            <h3 className="text-2xl font-bold text-gray-900 mb-3">AI Recommendations</h3>
            <p className="text-gray-600">
              Get personalized book recommendations based on your reading preferences using advanced algorithms.
            </p>
          </div>

          <div className="bg-white rounded-lg shadow-lg p-8 text-center">
            <span className="text-4xl mb-4 block">⭐</span>
            <h3 className="text-2xl font-bold text-gray-900 mb-3">Rate & Review</h3>
            <p className="text-gray-600">
              Share your thoughts on books by rating them. Your ratings help improve recommendations for everyone.
            </p>
          </div>

          <div className="bg-white rounded-lg shadow-lg p-8 text-center">
            <span className="text-4xl mb-4 block">🎯</span>
            <h3 className="text-2xl font-bold text-gray-900 mb-3">Explore Categories</h3>
            <p className="text-gray-600">
              Browse thousands of books across multiple categories and find exactly what you're looking for.
            </p>
          </div>
        </div>

        {/* How It Works Section */}
        <div className="mt-20 bg-white rounded-lg shadow-lg p-12">
          <h2 className="text-4xl font-bold text-gray-900 text-center mb-12">How It Works</h2>
          <div className="grid md:grid-cols-4 gap-8">
            <div className="text-center">
              <div className="bg-blue-600 text-white w-12 h-12 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">
                1
              </div>
              <h3 className="text-lg font-semibold text-gray-900 mb-2">Sign Up</h3>
              <p className="text-gray-600">Create your free account and get started</p>
            </div>

            <div className="text-center">
              <div className="bg-blue-600 text-white w-12 h-12 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">
                2
              </div>
              <h3 className="text-lg font-semibold text-gray-900 mb-2">Browse Books</h3>
              <p className="text-gray-600">Explore our collection of thousands of books</p>
            </div>

            <div className="text-center">
              <div className="bg-blue-600 text-white w-12 h-12 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">
                3
              </div>
              <h3 className="text-lg font-semibold text-gray-900 mb-2">Rate Books</h3>
              <p className="text-gray-600">Rate books you've read (1-5 stars)</p>
            </div>

            <div className="text-center">
              <div className="bg-blue-600 text-white w-12 h-12 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">
                4
              </div>
              <h3 className="text-lg font-semibold text-gray-900 mb-2">Get Recommendations</h3>
              <p className="text-gray-600">Receive AI-powered personalized recommendations</p>
            </div>
          </div>
        </div>

        {/* CTA Section */}
        <div className="mt-20 text-center">
          <h2 className="text-4xl font-bold text-gray-900 mb-6">Ready to Discover Your Next Favorite Book?</h2>
          <button
            onClick={() => navigate('/register')}
            className="px-8 py-4 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition text-lg inline-block"
          >
            Get Started Now
          </button>
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-gray-900 text-white py-12 mt-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <p className="mb-2">&copy; 2025 Book Recommendation System. All rights reserved.</p>
          <p className="text-gray-400">Discover, Rate, and Enjoy Great Books</p>
        </div>
      </footer>
    </div>
  )
}


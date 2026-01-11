import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useQuery } from '@tanstack/react-query'
import { useAuthStore } from '@/store/authStore'
import { useAuth } from '@/hooks/useAuth'
import { useUserStats } from '@/hooks/useUserStats'

export const ProfilePage: React.FC = () => {
  const navigate = useNavigate()
  const { logout } = useAuthStore()
  const { getCurrentUser } = useAuth()
  const [isEditing, setIsEditing] = useState(false)

  const { data: user, isLoading, error } = useQuery({
    queryKey: ['currentUser'],
    queryFn: async () => {
      const response = await getCurrentUser()
      return response
    },
  })

  const { data: stats = {} } = useUserStats()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  if (isLoading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
          <p className="text-gray-600 mt-4">Loading profile...</p>
        </div>
      </div>
    )
  }

  if (error || !user) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <p className="text-red-600 text-lg">Failed to load profile</p>
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
    <div className="min-h-screen bg-gray-50 py-12">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="mb-8">
          <button
            onClick={() => navigate('/books')}
            className="text-blue-600 hover:text-blue-700 font-medium flex items-center gap-2"
          >
            ← Back to Books
          </button>
        </div>

        {/* Profile Card */}
        <div className="bg-white rounded-lg shadow-lg overflow-hidden">
          {/* Cover Background */}
          <div className="h-32 bg-gradient-to-r from-blue-600 to-blue-800"></div>

          {/* Profile Content */}
          <div className="px-8 py-8">
            {/* Profile Header */}
            <div className="flex flex-col md:flex-row md:items-end gap-6 -mt-20 mb-8">
              {/* Avatar */}
              <div className="flex-shrink-0">
                <div className="h-40 w-40 bg-gray-300 rounded-full border-4 border-white shadow-lg overflow-hidden flex items-center justify-center">
                  <span className="text-6xl">👤</span>
                </div>
              </div>

              {/* User Info */}
              <div className="flex-grow">
                <h1 className="text-3xl font-bold text-gray-900">
                  {user.firstName} {user.lastName}
                </h1>
                <p className="text-gray-600 text-lg mt-1">{user.email}</p>
                <div className="flex gap-4 mt-4">
                  <button
                    onClick={() => setIsEditing(!isEditing)}
                    className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
                  >
                    {isEditing ? 'Cancel' : 'Edit Profile'}
                  </button>
                  <button
                    onClick={handleLogout}
                    className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition"
                  >
                    Logout
                  </button>
                </div>
              </div>
            </div>

            {/* User Details Section */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-8">
              {/* Personal Information */}
              <div className="bg-gray-50 rounded-lg p-6">
                <h2 className="text-xl font-bold text-gray-900 mb-4">Personal Information</h2>

                <div className="space-y-4">
                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      First Name
                    </label>
                    <p className="text-gray-900">{user.firstName || 'Not provided'}</p>
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      Last Name
                    </label>
                    <p className="text-gray-900">{user.lastName || 'Not provided'}</p>
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      Email
                    </label>
                    <p className="text-gray-900 break-all">{user.email}</p>
                  </div>

                  {user.phoneNumber && (
                    <div>
                      <label className="block text-sm font-semibold text-gray-700 mb-1">
                        Phone
                      </label>
                      <p className="text-gray-900">{user.phoneNumber}</p>
                    </div>
                  )}

                  {user.country && (
                    <div>
                      <label className="block text-sm font-semibold text-gray-700 mb-1">
                        Country
                      </label>
                      <p className="text-gray-900">{user.country}</p>
                    </div>
                  )}
                </div>
              </div>

              {/* Account Information */}
              <div className="bg-gray-50 rounded-lg p-6">
                <h2 className="text-xl font-bold text-gray-900 mb-4">Account Information</h2>

                <div className="space-y-4">
                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      User ID
                    </label>
                    <p className="text-gray-900 font-mono">#{user.id}</p>
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      Account Status
                    </label>
                    <span className="inline-block px-3 py-1 bg-green-100 text-green-800 rounded-full text-sm font-medium">
                      ✓ Active
                    </span>
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      Member Since
                    </label>
                    <p className="text-gray-900">
                      {user.createdDate
                        ? new Date(user.createdDate).toLocaleDateString('en-US', {
                            year: 'numeric',
                            month: 'long',
                            day: 'numeric',
                          })
                        : 'Recently joined'}
                    </p>
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-1">
                      Gender
                    </label>
                    <p className="text-gray-900 capitalize">
                      {user.gender ? user.gender.charAt(0) + user.gender.slice(1).toLowerCase() : 'Not specified'}
                    </p>
                  </div>

                  {user.age && (
                    <div>
                      <label className="block text-sm font-semibold text-gray-700 mb-1">
                        Age
                      </label>
                      <p className="text-gray-900">{user.age} years old</p>
                    </div>
                  )}
                </div>
              </div>
            </div>

            {/* Reading Statistics - Primary Stats */}
            <div className="bg-gradient-to-r from-blue-50 to-blue-100 rounded-lg p-6 mt-6">
              <h2 className="text-xl font-bold text-gray-900 mb-4">📊 Reading Statistics</h2>

              <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                <div className="bg-white rounded-lg p-4 text-center shadow-md">
                  <p className="text-gray-600 text-sm mb-2 font-medium">Books Rated</p>
                  <p className="text-4xl font-bold text-blue-600">
                    {stats.booksRated || 0}
                  </p>
                  <p className="text-xs text-gray-500 mt-2">total ratings given</p>
                </div>

                <div className="bg-white rounded-lg p-4 text-center shadow-md">
                  <p className="text-gray-600 text-sm mb-2 font-medium">Average Rating</p>
                  <div className="flex items-center justify-center gap-2 mt-1">
                    <span className="text-3xl text-yellow-400">⭐</span>
                    <p className="text-3xl font-bold text-blue-600">
                      {stats.averageRating ? stats.averageRating.toFixed(1) : '0.0'}
                    </p>
                  </div>
                  <p className="text-xs text-gray-500 mt-2">out of 5.0</p>
                </div>

                <div className="bg-white rounded-lg p-4 text-center shadow-md">
                  <p className="text-gray-600 text-sm mb-2 font-medium">Reading Level</p>
                  <p className="text-lg font-bold text-blue-600 mt-4">
                    {stats.readingLevel || 'Not set'}
                  </p>
                  <p className="text-xs text-gray-500 mt-2">estimated from ratings</p>
                </div>
              </div>
            </div>

            {/* Reading Activity */}
            <div className="bg-purple-50 rounded-lg p-6 mt-6 border-l-4 border-purple-500">
              <h2 className="text-xl font-bold text-gray-900 mb-4">📚 Reading Activity</h2>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="bg-white rounded-lg p-4">
                  <p className="text-gray-600 text-sm mb-2">Total Ratings</p>
                  <p className="text-3xl font-bold text-purple-600">
                    {stats.totalRatingCount || 0}
                  </p>
                  <p className="text-xs text-gray-500 mt-2">reviews and ratings</p>
                </div>

                <div className="bg-white rounded-lg p-4">
                  <p className="text-gray-600 text-sm mb-2">Contribution</p>
                  <p className="text-3xl font-bold text-purple-600">
                    {stats.booksRated ? `${Math.round((stats.booksRated / 100) * 100)}%` : '0%'}
                  </p>
                  <p className="text-xs text-gray-500 mt-2">of reading community</p>
                </div>
              </div>
            </div>

            {/* Quick Stats */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-6">
              {/* Preferences */}
              <div className="bg-green-50 rounded-lg p-6 border border-green-200">
                <h2 className="text-lg font-bold text-gray-900 mb-4">✨ Your Preferences</h2>
                <div className="space-y-3">
                  <p className="text-sm text-gray-700">
                    <span className="font-semibold">Account Type:</span> Reader
                  </p>
                  <p className="text-sm text-gray-700">
                    <span className="font-semibold">Status:</span> <span className="text-green-600 font-medium">Active Member</span>
                  </p>
                  <p className="text-sm text-gray-700">
                    <span className="font-semibold">Reviews Given:</span> {stats.booksRated || 0}
                  </p>
                </div>
              </div>

              {/* Stats Summary */}
              <div className="bg-orange-50 rounded-lg p-6 border border-orange-200">
                <h2 className="text-lg font-bold text-gray-900 mb-4">🏆 Your Impact</h2>
                <div className="space-y-3">
                  <p className="text-sm text-gray-700">
                    <span className="font-semibold">Rating Accuracy:</span> {stats.averageRating ? `${(stats.averageRating / 5 * 100).toFixed(0)}%` : 'N/A'}
                  </p>
                  <p className="text-sm text-gray-700">
                    <span className="font-semibold">Last Activity:</span> Recently
                  </p>
                  <p className="text-sm text-gray-700">
                    <span className="font-semibold">Member Tier:</span> {stats.booksRated && stats.booksRated > 10 ? '⭐ Gold' : 'Silver'}
                  </p>
                </div>
              </div>
            </div>

            {/* Account Actions */}
            <div className="bg-red-50 rounded-lg p-6 mt-6 border border-red-200">
              <h2 className="text-xl font-bold text-red-900 mb-4">⚠️ Danger Zone</h2>
              <button
                className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition"
              >
                Delete Account
              </button>
              <p className="text-sm text-red-700 mt-2">
                Warning: This action cannot be undone. All your data will be permanently deleted.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}


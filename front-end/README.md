# Book Recommendation System - Frontend

React frontend application for the Book Recommendation System built with Vite, TypeScript, Tailwind CSS, and React Query.

## Features

- 🔐 JWT Authentication (Login/Register)
- 📚 Browse and search books
- 🏷️ Filter by category
- ⭐ Rate books (1-5 stars)
- 🤖 AI-powered recommendations
- 👤 User profile management
- 📱 Responsive design
- ⚡ Fast performance with Vite

## Tech Stack

- **Framework**: React 18
- **Language**: TypeScript
- **Build Tool**: Vite
- **Styling**: Tailwind CSS
- **HTTP Client**: Axios
- **Data Fetching**: React Query (TanStack Query)
- **State Management**: Zustand
- **Routing**: React Router v6

## Getting Started

### Prerequisites

- Node.js 18+
- npm or yarn

### Installation

```bash
# Install dependencies
npm install

# Copy environment file
cp .env.example .env

# Start development server
npm run dev
```

The application will be available at `http://localhost:5173`

### Building for Production

```bash
# Build
npm run build

# Preview production build
npm run preview
```

## Project Structure

```
src/
├── components/           # React components
│   ├── auth/            # Authentication components
│   ├── books/           # Book-related components
│   └── common/          # Common components (Navbar, Footer, etc.)
├── pages/               # Page components
├── services/            # API services
│   ├── api.ts          # Axios configuration
│   ├── authService.ts  # Authentication API
│   └── bookService.ts  # Books API
├── hooks/              # Custom React hooks
│   ├── useAuth.ts
│   └── useBooks.ts
├── store/              # Zustand stores
│   ├── authStore.ts
│   └── uiStore.ts
├── types/              # TypeScript types and interfaces
├── styles/             # CSS files
└── App.tsx             # Root component
```

## API Configuration

The frontend connects to the backend API at `http://localhost:8010/book-service/api`

Make sure the backend is running before starting the frontend:

```bash
# Backend should be running on port 8010
mvn spring-boot:run
```

## Environment Variables

Create a `.env` file based on `.env.example`:

```
VITE_API_URL=http://localhost:8010/book-service/api
```

## Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run preview` - Preview production build
- `npm run type-check` - Check TypeScript types
- `npm run lint` - Run ESLint

## Features Implemented

### Authentication
- ✅ Login page with JWT authentication
- ✅ Token refresh mechanism
- ✅ Protected routes
- ✅ Automatic token management in Axios interceptors

### Books
- ✅ Browse all books
- ✅ Paginated list (12 books per page)
- ✅ Filter by category
- ✅ Book card with rating display
- ✅ Rate books functionality

### UI/UX
- ✅ Responsive design (mobile, tablet, desktop)
- ✅ Loading states
- ✅ Error handling
- ✅ Beautiful Tailwind CSS styling

## Features to Implement

- [ ] Register/Sign up page
- [ ] Recommendations page with AI-powered recommendations
- [ ] User profile page
- [ ] Detailed book view
- [ ] Search functionality
- [ ] Book reviews/comments
- [ ] Reading list/bookmarks
- [ ] Social features (follow users, share reviews)

## API Endpoints Used

- `POST /api/user` - Register new user
- `POST /api/auth/log-in` - Login
- `GET /api/auth/current` - Get current user
- `POST /api/auth/refresh-token` - Refresh JWT token
- `GET /api/book/find-all-categories` - Get all book categories
- `POST /api/book/find-all-paginated-filtered` - Get books with pagination and filters
- `GET /api/book/find-by-id/{bookId}` - Get book details
- `GET /api/book/find-all-recommended` - Get AI recommendations
- `POST /api/book/rate` - Rate a book

## Authentication Flow

1. User logs in with email and password
2. Backend returns `accessToken` (1h expiry) and `refreshToken` (24h expiry)
3. Frontend stores tokens and adds to Axios interceptor
4. All API requests include `Authorization: Bearer <token>`
5. If token expires, interceptor automatically refreshes using `refreshToken`
6. If refresh fails, user is redirected to login

## Deployment

### Vercel (Recommended)

```bash
# Install Vercel CLI
npm install -g vercel

# Deploy
vercel
```

### Netlify

```bash
# Build
npm run build

# Deploy the dist folder to Netlify
```

### Docker

```bash
# Build Docker image
docker build -t book-app .

# Run container
docker run -p 5173:5173 book-app
```

## Troubleshooting

### CORS Issues
If you see CORS errors, make sure:
1. Backend is running on `http://localhost:8010`
2. Backend has `@CrossOrigin` annotation on controllers
3. Vite proxy is configured in `vite.config.ts`

### Token Not Persisting
Zustand store with `persist` middleware should save tokens to localStorage automatically. Check:
1. Browser localStorage is enabled
2. No incognito/private browsing mode

### Build Errors
```bash
# Clear node_modules and reinstall
rm -rf node_modules package-lock.json
npm install
npm run build
```

## Contributing

1. Create a new branch for your feature
2. Make your changes
3. Run `npm run type-check` to check types
4. Commit with clear messages
5. Push and create a pull request

## License

MIT

## Support

For issues or questions, refer to the main project documentation in the parent directory.

---

**Last Updated**: December 12, 2025
**Version**: 0.0.1


# 📚 Book Recommendation System

A full-stack web application that provides intelligent book recommendations using **user-based collaborative filtering**. The system allows users to discover books based on reading preferences and ratings from similar users, creating a personalized reading experience.

## 🎯 System Overview

The Book Recommendation System is built with a **Clean Architecture** approach, separating concerns into distinct layers:
- **Entity Layer**: Database models (User, Book, Author, Rating, Category, etc.)
- **Repository Layer**: Spring Data JPA repositories for data access
- **DAO Layer**: Custom data access objects with complex query logic
- **Service Layer**: Business logic and use case implementation
- **Controller Layer**: REST API endpoints
- **Security Layer**: JWT-based authentication and authorization
- **Transformer Layer**: DTOs and entity transformation using MapStruct
- **Recommender Engine**: Collaborative filtering algorithm

---

## 💻 Technology Stack

### Backend
- **Java 17**: Programming language
- **Spring Boot 2.7.5**: Application framework
- **Spring Security**: JWT-based authentication and authorization
- **Hibernate 5.6.12**: ORM framework
- **MySQL 8.0**: Relational database
- **Liquibase 3.6.3**: Database migration and versioning
- **MapStruct 1.5.3**: Entity-to-DTO mapping
- **Lombok**: Reduce boilerplate code
- **SpringDoc OpenAPI 1.6.12**: Swagger/OpenAPI 3.0 documentation
- **Maven 3**: Dependency management and build tool
- **JWT (java-jwt 4.2.1)**: Token-based authentication

### Frontend
- **React 18.2.0**: UI framework
- **TypeScript**: Type-safe JavaScript
- **Vite**: Fast build tool and dev server
- **React Router v6**: Client-side routing
- **TanStack React Query v5**: Server state management
- **Zustand v4.4.1**: Client state management
- **React Hook Form v7.48.0**: Form management
- **Axios**: HTTP client
- **Tailwind CSS**: Utility-first CSS framework
- **PostCSS**: CSS processing

### Development Tools
- **Docker**: Containerization
- **Git**: Version control

---

## 🎮 Core Features

### 1. **User Management**
- User registration and authentication
- JWT token-based authorization (access token: 1 hour, refresh token: 24 hours)
- User profile management
- Reading preferences configuration (preferred genre, reading level)
- Reading statistics tracking (books rated, books read, wishlist items)

### 2. **Book & Author Management**
- Browse books with pagination and filtering
- View author details
- Book categorization system (10 categories: Science Fiction, Mystery, Romance, Fantasy, Thriller, Biography, History, Self-Help, Business, Children)
- Book search and filtering by category, author, and name

### 3. **Rating & Review System**
- Users can rate books on a 1-5 star scale
- Add detailed comments/reviews to ratings
- View all ratings and reviews for a book
- Calculate and display average rating for each book
- User reading history tracking

### 4. **Intelligent Recommendation Engine**
- **Collaborative Filtering Algorithm**: Recommends books based on similar users' preferences
- **Algorithm Features**:
  - Analyzes user rating patterns
  - Identifies 10 nearest neighbors (similar users)
  - Recommends top 20 books rated ≥4 stars by similar users
  - Personalized recommendations per user

### 5. **User Preferences**
- Select preferred reading genres
- Set reading level (BEGINNER, INTERMEDIATE, ADVANCED)
- View recommended books based on reading preferences
- Wishlist functionality

### 6. **API Documentation**
- Auto-generated Swagger/OpenAPI 3.0 documentation
- Interactive API testing interface

---

## 📊 Database Schema

### Key Entities

| Table | Purpose |
|-------|---------|
| `user` | User accounts and authentication |
| `book` | Book catalog information |
| `author` | Author details |
| `book_category` | Book categories/genres |
| `user_book_rate` | Book ratings and reviews |
| `user_book_category` | User's preferred genres |
| `user_reading_info` | User reading statistics |
| `refresh_token` | JWT refresh token storage |

### Key Relationships
- **User 1:N Book Ratings** - A user can rate multiple books
- **Book 1:N Ratings** - A book can have multiple ratings
- **Book N:M Categories** - A book can belong to multiple categories
- **User 1:N Preferred Categories** - Users can prefer multiple genres

---

## 🚀 API Endpoints

### Base URL: `http://localhost:8010/book-service`

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login and get JWT token
- `POST /api/auth/refresh-token` - Refresh access token

### Books
- `GET /api/book/find-by-id/{bookId}` - Get book details by ID
- `POST /api/book/find-all-paginated-filtered` - Get books with pagination and filtering
- `GET /api/book/find-all-recommended` - Get recommended books for logged-in user
- `GET /api/book/find-all-by-author/{authorId}` - Get books by author
- `GET /api/book/{id}/ratings` - Get all ratings for a book

### Book Ratings
- `POST /api/user-book-rate/create` - Create book rating/review
- `PUT /api/user-book-rate/update/{ratingId}` - Update existing rating
- `DELETE /api/user-book-rate/delete/{ratingId}` - Delete rating

### Authors
- `GET /api/author/find-all` - Get all authors with pagination
- `GET /api/author/find-by-id/{authorId}` - Get author details

### Book Categories
- `GET /api/book-category/find-all` - Get all book categories

### Users
- `GET /api/user/find-by-id/{userId}` - Get user profile
- `GET /api/user/find-current-user` - Get current logged-in user info
- `PUT /api/user/update/{userId}` - Update user profile

### User Preferences
- `POST /api/user-book-category/create` - Set user's preferred genre
- `DELETE /api/user-book-category/delete/{categoryId}` - Remove preferred genre

### User Statistics
- `GET /api/user-reading-info/{userId}` - Get user reading statistics

---

## 🛠️ Installation & Setup

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Node.js 16+ and npm
- Maven 3.6+

### Database Setup

1. Create MySQL database:
```sql
CREATE DATABASE book_reco CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_reco?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

3. Run Liquibase migrations (automatic on startup):
```
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:/db/book-recommendation-system.xml
```

### Backend Setup

```bash
# Clone repository
git clone <repository-url>
cd book-recommendation-system-master

# Build with Maven
mvn clean install

# Run Spring Boot application
mvn spring-boot:run
# or
java -jar target/book-recommendation-system-0.0.1-SNAPSHOT.jar

# Application starts on http://localhost:8010/book-service
# Swagger UI: http://localhost:8010/book-service/swagger-ui.html
```

### Frontend Setup

```bash
# Navigate to frontend directory
cd front-end

# Install dependencies
npm install

# Start development server
npm run dev
# Application opens on http://localhost:5173

# Build for production
npm run build

# Preview production build
npm run preview
```

---

## 🔒 Security

### Authentication Flow
1. User registers with email and password
2. User logs in to receive JWT access token (valid for 1 hour)
3. JWT token included in `Authorization: Bearer <token>` header
4. Refresh token provided to get new access token when expired

### JWT Configuration
```properties
library.security.jwtSecret=book-recommendation-auth-secret
library.security.jwt.accessToken.expirationMs=3600000    # 1 hour
library.security.jwt.refreshToken.expirationMs=86400000  # 24 hours
```

### Authorization
- Public endpoints: Registration, login, public book browsing
- Protected endpoints: Ratings, user profile, recommendations (requires JWT)
- Role-based access control implemented via Spring Security

---

## 🤖 Recommendation Algorithm

### Collaborative Filtering Implementation

**Location**: `src/main/java/com/sesame/pds/recommender/CollaborativeFilteringRecommender.java`

**Algorithm Steps**:
1. **Load User Ratings**: Fetch all ratings from database
2. **Find Similar Users**: Calculate similarity between target user and others using rating patterns
3. **Filter Neighborhoods**: Select top 10 most similar users
4. **Extract Recommendations**: Get books rated ≥4 stars by similar users
5. **Rank & Return**: Return top 20 recommended books

**Key Parameters**:
- `NUM_NEIGHBOURHOODS = 10`: Number of similar users to consider
- `NUM_RECOMMENDATIONS = 20`: Maximum recommendations to return
- `MIN_VALUE_RECOMMENDATION = 4`: Minimum rating threshold for recommendations

---

## 📝 Sample Request/Response

### Register User
**Request**: `POST /api/auth/register`
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "SecurePass123!",
  "phoneNumber": "+1234567890"
}
```

**Response**:
```json
{
  "success": true,
  "timestamp": "2025-12-14T10:30:00",
  "message": "User created successfully.",
  "body": {
    "id": 1,
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}
```

### Rate a Book
**Request**: `POST /api/user-book-rate/create`
```json
{
  "bookId": 5,
  "rate": 4,
  "comment": "Great book! Very engaging story."
}
```

**Response**:
```json
{
  "success": true,
  "timestamp": "2025-12-14T10:35:00",
  "message": "Book rating created successfully.",
  "body": {
    "id": 123,
    "bookId": 5,
    "userId": 1,
    "rate": 4,
    "comment": "Great book! Very engaging story."
  }
}
```

---

## ⚠️ Known Issues & Limitations

### 1. **CORS Policy Issue**
- **Problem**: Frontend cannot access certain endpoints due to CORS restrictions
- **Error**: `No 'Access-Control-Allow-Origin' header`
- **Location**: Book ratings endpoint `/api/book/{id}/ratings`
- **Status**: ⏳ Pending - Requires CORS configuration in Spring Security
- **Impact**: Cannot fetch ratings for book details page

### 2. **Rating Calculation Bug**
- **Problem**: Average rating calculation shows incorrect values (e.g., 44.9 instead of 4.4)
- **Root Cause**: Possible multiplication instead of division in averaging logic
- **Affected Component**: Rating aggregation service
- **Status**: ⏳ Pending - Requires algorithm review
- **Impact**: Displayed ratings are incorrect

### 3. **Book Search Filtering Issue**
- **Problem**: Category ID filtering not working correctly when fetching books
- **Current Behavior**: All books returned regardless of category filter
- **Status**: ⏳ Pending - Backend filter logic needs debugging
- **Impact**: Cannot filter books by category

### 4. **Profile Statistics Not Updating**
- **Problem**: User statistics (books rated, books read, average rating) showing 0
- **Affected Fields**: 
  - Books Rated
  - Books Read
  - Average Rating
  - Wishlist Items
- **Status**: ⏳ Pending - Data aggregation query needs verification
- **Impact**: User profile shows incomplete information

### 5. **Pagination Index Mismatch**
- **Problem**: Frontend sends pageNumber starting from 0, backend returns pageNumber starting from 1
- **Impact**: Pagination inconsistency between frontend and backend

### 6. **Enum Conversion Error**
- **Problem**: Gender field stored as string "MALE" but expected as numeric enum ordinal
- **Current Schema Issue**: Database column type mismatch
- **Status**: ⏳ Pending - Schema migration needed

---

## 📈 Performance Considerations

- **Database Indexes**: Implemented on frequently queried fields (email, user_id, book_id)
- **Pagination**: All list endpoints support pagination (default: 12 items per page)
- **Lazy Loading**: Configured for JPA relationships to reduce unnecessary queries
- **Connection Pooling**: HikariCP configured for optimal database performance

---

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### Test Coverage
- Unit tests for service layer
- Integration tests for API endpoints
- Repository tests for DAO operations

---

## 📦 Project Structure

```
book-recommendation-system-master/
├── src/main/java/com/sesame/pds/
│   ├── config/                 # Spring configurations
│   ├── controller/             # REST API endpoints
│   ├── service/                # Business logic
│   ├── dao/                    # Data access objects
│   ├── entity/                 # JPA entities
│   ├── repository/             # Spring Data repositories
│   ├── dto/                    # Data transfer objects
│   ├── transformer/            # Entity-DTO mappers
│   ├── exception/              # Custom exceptions
│   ├── enums/                  # Enumeration types
│   ├── security/               # JWT and security config
│   ├── recommender/            # Recommendation algorithms
│   └── utils/                  # Utility classes
├── src/main/resources/
│   ├── application.properties  # Configuration file
│   └── db/                     # Liquibase migrations
├── front-end/
│   ├── src/
│   │   ├── components/         # React components
│   │   ├── pages/              # Page components
│   │   ├── services/           # API services
│   │   ├── hooks/              # Custom React hooks
│   │   ├── store/              # Zustand stores
│   │   └── types/              # TypeScript types
│   └── package.json            # Frontend dependencies
└── pom.xml                     # Maven configuration
```

---

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Clean Architecture principles
- ✅ Spring Boot microservices patterns
- ✅ JWT authentication & authorization
- ✅ Hibernate ORM and database design
- ✅ RESTful API design
- ✅ Collaborative filtering algorithms
- ✅ React hooks and state management
- ✅ TypeScript type safety
- ✅ Database migration with Liquibase
- ✅ Full-stack development practices

---

## 📝 License

This project is part of an educational initiative.

---

## 👨‍💻 Contributors

- **KHAZRI OMAR** - Original Author & Architect
- **Project Team** - Development & Testing

---

## 📞 Support

For issues, bug reports, or feature requests, please refer to the **Known Issues** section above or create a detailed issue report.

---

**Last Updated**: January 11, 2025  
**Version**: 0.0.1-SNAPSHOT  
**Java Version**: 17  
**Spring Boot Version**: 2.7.5  
**Database**: MySQL 8.0

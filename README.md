# 📚 Book Recommendation System

> **⚠️ Note**: This project has been migrated to **Spring Boot 3.5.0** with Java 17. See [SPRING_BOOT_3_5_MIGRATION.md](./SPRING_BOOT_3_5_MIGRATION.md) for detailed migration information.

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
- **Spring Boot 3.5.0**: Application framework (upgraded from 2.7.5)
- **Spring Security**: JWT-based authentication and authorization
- **Hibernate 6.x**: ORM framework
- **MySQL 8.0**: Relational database with mysql-connector-j
- **MapStruct 1.5.5**: Entity-to-DTO mapping
- **Lombok**: Reduce boilerplate code
- **SpringDoc OpenAPI 2.4.0**: Swagger/OpenAPI 3.0 documentation
- **Maven 3**: Dependency management and build tool
- **JWT (java-jwt 4.2.1)**: Token-based authentication
- **Jackson**: JSON processing and serialization
- **Google Books API Integration**: Automatic book cover fetching via Open Library

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

### ...existing code...

---

## 📦 Data Initialization (DataInitializer)

### Automatic Dummy Data Loading

The application automatically initializes the database with comprehensive dummy data on startup using **JSON-based configuration**.

### Key Components

#### 1. **DummyDataLoader** (`src/main/java/com/sesame/pds/config/DummyDataLoader.java`)
- Loads dummy data from `src/main/resources/data/dummy-data.json`
- Uses Jackson ObjectMapper for JSON parsing
- Provides typed getter methods for each data category
- Singleton component managed by Spring

#### 2. **GoogleBooksService** (`src/main/java/com/sesame/pds/service/GoogleBooksService.java`)
- Fetches book covers automatically from **Open Library API**
- Uses ISBN to retrieve cover images
- Provides fallback placeholder images if covers are unavailable
- URL format: `https://covers.openlibrary.org/b/isbn/{isbn}-M.jpg`

#### 3. **DataInitializer** (`src/main/java/com/sesame/pds/config/DataInitializer.java`)
- Executes on application startup (ApplicationRunner)
- Checks if database is already initialized (skips if data exists)
- Loads data from JSON via DummyDataLoader
- Integrates GoogleBooksService for book covers
- Auto-calculates derived fields (age from birthDate, etc.)
- Provides structured initialization logs

### Data Structure

The `dummy-data.json` file contains:

```json
{
  "categories": [10 book categories],
  "authors": [10 diverse authors],
  "books": [15 popular books with ISBN],
  "users": [10 sample users],
  "ratings": [38 user book ratings/reviews],
  "readingLevels": [reading levels for users]
}
```

### Dummy Data Statistics

| Entity | Count | Details |
|--------|-------|---------|
| Categories | 10 | Science Fiction, Mystery, Romance, Fantasy, Thriller, Biography, History, Self-Help, Business, Children |
| Authors | 10 | J.K. Rowling, George R.R. Martin, Agatha Christie, Isaac Asimov, Stephen King, Paulo Coelho, and more |
| Books | 15 | Harry Potter series, A Game of Thrones, Foundation, The Alchemist, and more classic/modern titles |
| Users | 10 | Sample users from different countries with various reading levels and preferences |
| Ratings | 38 | User book ratings (1-5 stars) with detailed comments |
| Reading Levels | 10 | EXPERT, INTERMEDIATE, BEGINNER assignments |

### Initialization Flow

```
Application Start
    ↓
DataInitializer.run() executes
    ↓
Check database count (Skip if data exists)
    ↓
Load dummy-data.json via DummyDataLoader
    ↓
Create Categories (10)
    ↓
Create Authors (10) → Calculate age automatically
    ↓
Create Books (15) → Fetch covers via GoogleBooksService
    ↓
Create Users (10) → Calculate age automatically
    ↓
Create User Reading Levels (10)
    ↓
Create User Book Ratings (38)
    ↓
Print structured initialization summary
```

### Sample Output

```
→ Initializing database with dummy data from JSON...
  ✓ Loaded 10 book categories
  ✓ Loaded 10 authors
  ✓ Loaded 15 books
  ✓ Loaded 10 users
  ✓ Created reading levels for 10 users
  ✓ Loaded 38 book ratings
✓ Database initialized successfully!

╔════════════════════════════════════════╗
║     Database Initialization Summary     ║
╠════════════════════════════════════════╣
║ Categories: 10                        ║
║ Authors:    10                        ║
║ Books:      15                        ║
║ Users:      10                        ║
╚════════════════════════════════════════╝
```

### Customizing Dummy Data

To modify dummy data without recompilation:

1. Edit `src/main/resources/data/dummy-data.json`
2. Update the relevant section (categories, authors, books, users, ratings, readingLevels)
3. Restart the application
4. Changes will be reflected in the database (if not yet initialized)

### Benefits

✅ **No Database Migrations**: Uses Jackson for JSON parsing instead of Liquibase  
✅ **No Hardcoded Arrays**: Clean, maintainable JSON configuration  
✅ **Automatic Cover Fetching**: Google Books/Open Library API integration  
✅ **Flexible Data Loading**: Easy to add/modify dummy data  
✅ **Auto-Calculated Fields**: Age, image URLs generated automatically  
✅ **Structured Logs**: Clear initialization progress feedback  

---

## 📊 Database Schema

### ...existing code...

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
- Java 17 (LTS)
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

3. **Hibernate Auto-Creation** (automatic on startup):
   - Tables are created automatically by Hibernate ORM
   - Configuration: `spring.jpa.hibernate.ddl-auto=create-drop`
   - Foreign keys and constraints are automatically established
   - No manual SQL migrations needed

4. **Automatic Data Initialization**:
   - `DataInitializer` loads dummy data from JSON on first run
   - Subsequent runs skip initialization (data already exists)
   - Book covers fetched automatically from Google Books/Open Library API

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

**Last Updated**: January 12, 2026  
**Version**: 1.0.0  
**Java Version**: 17 (LTS)  
**Spring Boot Version**: 3.5.0  
**Database**: MySQL 8.0  
**ORM**: Hibernate 6.x with auto-schema creation  
**Data Format**: JSON with Jackson parsing

## 🎯 Recent Updates (January 2026)

### ✨ Major Changes

1. **Database Initialization Refactored**
   - ❌ Removed: Liquibase migrations and hardcoded SQL
   - ✅ Added: JSON-based configuration (`dummy-data.json`)
   - ✅ Added: `DummyDataLoader` component for JSON parsing
   - ✅ Result: Simplified setup, no migrations needed

2. **Google Books Integration**
   - ✅ Added: `GoogleBooksService` for automatic cover fetching
   - ✅ Feature: Uses Open Library API for book cover images
   - ✅ Fallback: Placeholder images if ISBN invalid
   - ✅ Enhanced: Better book display with real cover images

3. **Data Initialization Enhancements**
   - ✅ Auto-calculation: Age from birthDate
   - ✅ Auto-generation: Image URLs for authors/users
   - ✅ Structured Logs: Clear initialization progress with emojis
   - ✅ Summary Table: Formatted initialization summary

4. **Project Structure Improvements**
   - ✅ Added: `src/main/resources/data/dummy-data.json`
   - ✅ Added: Comprehensive test data (10 authors, 15 books, 10 users, 38 ratings)
   - ✅ Added: `DATA_INITIALIZER_UPDATE.md` documentation

### Configuration Changes

**Before (Spring Boot 2.x with Liquibase)**:
```properties
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:/db/book-recommendation-system.xml
spring.jpa.hibernate.ddl-auto=validate
```

**After (Spring Boot 3.5 with Hibernate)**:
```properties
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
spring.liquibase.enabled=false
```

### Benefits

✅ **Simpler Setup**: No database migrations to manage  
✅ **Faster Development**: Auto schema creation on startup  
✅ **Better UX**: Real book covers from Google Books API  
✅ **Maintainable**: JSON data easily edited without recompiling  
✅ **Scalable**: Easy to add more dummy data  
✅ **Clear Logs**: Structured initialization output  

---

**Last Updated**: January 12, 2026  
**Version**: 1.0.0  
**Java Version**: 17  
**Spring Boot Version**: 3.5.0  
**Database**: MySQL 8.0

## 📋 Spring Boot 3.5 Upgrade & Latest Changes

### Breaking Changes & Migrations Completed

1. **Jakarta EE Namespace Migration**
   - All `javax.*` imports converted to `jakarta.*`
   - Affected packages: servlet, persistence, validation, transaction
   - This is a mandatory change for Spring Boot 3.x

2. **Dependency Updates**
   - Upgraded from Spring Boot 2.7.5 to 3.5.0
   - Updated MySql connector from `mysql-connector-java` to `mysql-connector-j`
   - Updated springdoc-openapi from `1.6.12` to `2.4.0`
   - Updated MapStruct from `1.5.3` to `1.5.5`
   - ✅ Removed Liquibase dependency (no longer needed)
   - Removed deprecated dependencies

3. **Database Strategy Changed**
   - ❌ **From**: Liquibase migrations (`liquibase.xml` changelog)
   - ✅ **To**: Hibernate ORM auto-schema creation
   - ✅ **Configuration**: `spring.jpa.hibernate.ddl-auto=create-drop`
   - ✅ **Result**: Simpler setup, automatic table creation on startup

4. **Data Initialization Strategy Changed**
   - ❌ **From**: SQL migration files + hardcoded arrays
   - ✅ **To**: JSON configuration + DummyDataLoader
   - ✅ **Result**: Maintainable, flexible dummy data management

5. **Database Driver Changes**
   - MySQL connector: `mysql-connector-java` → `mysql-connector-j`
   - Ensure MySQL JDBC URL is compatible: `jdbc:mysql://localhost:3306/book_reco?useSSL=false&serverTimezone=UTC`

6. **Configuration Changes**
   - Some Spring Boot properties may have changed names
   - Review `application.properties` for deprecated properties
   - Use `application-dev.properties` for development environment

7. **Swagger/OpenAPI Configuration**
   - Springdoc-openapi URL might have changed
   - Access Swagger UI at: `http://localhost:8010/book-service/swagger-ui.html`
   - OpenAPI JSON at: `http://localhost:8010/book-service/v3/api-docs`

### Build & Compilation

The project has been successfully built with:
- Maven 3.11.0 compiler plugin
- Java source/target: 17
- Build command: `mvn clean package`
- JAR file: `target/book-recommendation-system-0.0.1-SNAPSHOT.jar`

### Testing & Validation

Before deploying to production:
1. Run comprehensive integration tests
2. Verify all JWT authentication flows
3. Test database initialization with Hibernate
4. Validate API endpoints against Swagger documentation
5. Test with MySQL 8.0 compatibility
6. Verify Google Books cover fetching works correctly

### Migration Checklist

- ✅ Jakarta EE imports updated
- ✅ Dependencies upgraded to Spring Boot 3.5
- ✅ Liquibase removed and replaced with Hibernate
- ✅ JSON-based data initialization implemented
- ✅ Google Books Service integrated
- ✅ Database auto-creation configured
- ✅ All tests passing


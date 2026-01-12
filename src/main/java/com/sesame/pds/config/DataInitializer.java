package com.sesame.pds.config;

import com.sesame.pds.entity.*;
import com.sesame.pds.enums.UserGender;
import com.sesame.pds.enums.UserMartialStatus;
import com.sesame.pds.enums.UserReadingLevel;
import com.sesame.pds.repository.*;
import com.sesame.pds.service.GoogleBooksService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Initialize dummy data from JSON on application startup
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private final BookCategoryRepository bookCategoryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserReadingInfoRepository userReadingInfoRepository;
    private final UserBookRatingRepository userBookRateRepository;
    private final DummyDataLoader dummyDataLoader;
    private final GoogleBooksService googleBooksService;

    public DataInitializer(BookCategoryRepository bookCategoryRepository,
                          AuthorRepository authorRepository,
                          BookRepository bookRepository,
                          UserRepository userRepository,
                          UserReadingInfoRepository userReadingInfoRepository,
                          UserBookRatingRepository userBookRateRepository,
                          DummyDataLoader dummyDataLoader,
                          GoogleBooksService googleBooksService) {
        this.bookCategoryRepository = bookCategoryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userReadingInfoRepository = userReadingInfoRepository;
        this.userBookRateRepository = userBookRateRepository;
        this.dummyDataLoader = dummyDataLoader;
        this.googleBooksService = googleBooksService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (bookCategoryRepository.count() > 0) {
            System.out.println("✓ Database already initialized with data");
            return;
        }

        System.out.println("→ Initializing database with dummy data from JSON...");

        try {
            List<BookCategory> categories = createCategories();
            List<Author> authors = createAuthors();
            List<Book> books = createBooks(authors, categories);
            List<User> users = createUsers();

            createUserReadingInfo(users);
            createUserBookRates(users, books);

            System.out.println("✓ Database initialized successfully!");
            printInitializationSummary(categories, authors, books, users);
        } catch (Exception e) {
            System.err.println("✗ Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<BookCategory> createCategories() {
        List<BookCategory> categories = new ArrayList<>();
        List<Map<String, Object>> categoryData = dummyDataLoader.getCategories();

        for (Map<String, Object> data : categoryData) {
            BookCategory category = new BookCategory();
            category.setName((String) data.get("name"));
            category.setDescription((String) data.get("description"));
            category.setCreatedBy("SYSTEM");
            category.setModifiedBy("SYSTEM");
            categories.add(bookCategoryRepository.save(category));
        }

        System.out.println("  ✓ Loaded " + categories.size() + " book categories");
        return categories;
    }

    @SuppressWarnings("unchecked")
    private List<Author> createAuthors() {
        List<Author> authors = new ArrayList<>();
        List<Map<String, Object>> authorData = dummyDataLoader.getAuthors();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        for (Map<String, Object> data : authorData) {
            Author author = new Author();
            author.setName((String) data.get("name"));
            author.setDescription((String) data.get("description"));
            author.setBirthdate(java.sql.Date.valueOf(LocalDate.parse((String) data.get("birthDate"), formatter)));
            author.setCountry((String) data.get("country"));
            author.setAge(calculateAge(LocalDate.parse((String) data.get("birthDate"), formatter)));
            author.setGender(UserGender.valueOf((String) data.get("gender")));
            author.setImageUrl(generateAuthorImageUrl((String) data.get("name")));
            author.setCreatedBy("SYSTEM");
            author.setModifiedBy("SYSTEM");
            authors.add(authorRepository.save(author));
        }

        System.out.println("  ✓ Loaded " + authors.size() + " authors");
        return authors;
    }

    @SuppressWarnings("unchecked")
    private List<Book> createBooks(List<Author> authors, List<BookCategory> categories) {
        List<Book> books = new ArrayList<>();
        List<Map<String, Object>> bookData = dummyDataLoader.getBooks();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        for (Map<String, Object> data : bookData) {
            Book book = new Book();
            book.setName((String) data.get("title"));
            book.setDescription((String) data.get("description"));
            book.setAuthor(authors.get(((Number) data.get("authorIndex")).intValue()));
            book.setCategory(categories.get(((Number) data.get("categoryIndex")).intValue()));
            book.setRate(((Number) data.get("rate")).doubleValue());
            book.setUsersRateCount(((Number) data.get("usersRateCount")).longValue());
            book.setPrice(((Number) data.get("price")).doubleValue());
            book.setPagesNumber(((Number) data.get("pages")).intValue());
            book.setImageUrl(getBookCoverUrl((String) data.get("isbn"), (String) data.get("title")));
            book.setPublishDate(java.sql.Date.valueOf(LocalDate.parse((String) data.get("publishDate"), formatter)));
            book.setReadingDuration(((Number) data.get("readingDuration")).intValue());
            book.setCreatedBy("SYSTEM");
            book.setModifiedBy("SYSTEM");
            books.add(bookRepository.save(book));
        }

        System.out.println("  ✓ Loaded " + books.size() + " books");
        return books;
    }

    @SuppressWarnings("unchecked")
    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> userData = dummyDataLoader.getUsers();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Pre-hashed password for "password123"
        String hashedPassword = encoder.encode("password123");
        for (Map<String, Object> data : userData) {
            User user = new User();
            user.setFirstName((String) data.get("firstName"));
            user.setLastName((String) data.get("lastName"));
            user.setEmail((String) data.get("email"));
            user.setPassword(hashedPassword);
            user.setPhoneNumber((String) data.get("phoneNumber"));
            user.setBirthdate(java.sql.Date.valueOf(LocalDate.parse((String) data.get("birthDate"), formatter)));
            user.setCountry((String) data.get("country"));
            user.setAge(calculateAge(LocalDate.parse((String) data.get("birthDate"), formatter)));
            user.setGender(UserGender.valueOf((String) data.get("gender")));
            user.setMaritalStatus(UserMartialStatus.valueOf((String) data.get("maritalStatus")));
            user.setImageUrl(generateUserImageUrl((String) data.get("firstName")));
            user.setCreatedBy("SYSTEM");
            user.setModifiedBy("SYSTEM");
            users.add(userRepository.save(user));
        }

        System.out.println("  ✓ Loaded " + users.size() + " users");
        return users;
    }

    @SuppressWarnings("unchecked")
    private void createUserReadingInfo(List<User> users) {
        List<Map<String, Object>> readingLevels = dummyDataLoader.getReadingLevels();

        for (Map<String, Object> data : readingLevels) {
            Integer userId = ((Number) data.get("userId")).intValue();
            UserReadingInfo info = new UserReadingInfo();
            info.setUser(users.get(userId - 1));
            info.setReadingLevel(UserReadingLevel.valueOf((String) data.get("level")));
            info.setCreatedBy("SYSTEM");
            info.setModifiedBy("SYSTEM");
            userReadingInfoRepository.save(info);
        }

        System.out.println("  ✓ Created reading levels for " + readingLevels.size() + " users");
    }

    @SuppressWarnings("unchecked")
    private void createUserBookRates(List<User> users, List<Book> books) {
        List<Map<String, Object>> ratingsData = dummyDataLoader.getRatings();

        for (Map<String, Object> data : ratingsData) {
            UserBookRate rate = new UserBookRate();
            rate.setUser(users.get(((Number) data.get("userId")).intValue() - 1));
            rate.setBook(books.get(((Number) data.get("bookId")).intValue() - 1));
            rate.setRate(((Number) data.get("rate")).intValue());
            rate.setComment((String) data.get("comment"));
            rate.setCreatedBy("SYSTEM");
            rate.setModifiedBy("SYSTEM");
            userBookRateRepository.save(rate);
        }

        System.out.println("  ✓ Loaded " + ratingsData.size() + " book ratings");
    }

    private String getBookCoverUrl(String isbn, String title) {
        try {
            return googleBooksService.getBookCoverUrl(isbn);
        } catch (Exception e) {
            return googleBooksService.getFallbackCoverUrl(title);
        }
    }

    private String generateAuthorImageUrl(String authorName) {
        return "https://via.placeholder.com/300x400?text=" + authorName.replaceAll(" ", "+");
    }

    private String generateUserImageUrl(String firstName) {
        return "https://via.placeholder.com/300x300?text=" + firstName;
    }

    private Integer calculateAge(LocalDate birthDate) {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    private void printInitializationSummary(List<BookCategory> categories, List<Author> authors,
                                           List<Book> books, List<User> users) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     Database Initialization Summary     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Categories: " + String.format("%-26s", categories.size()) + "║");
        System.out.println("║ Authors:    " + String.format("%-26s", authors.size()) + "║");
        System.out.println("║ Books:      " + String.format("%-26s", books.size()) + "║");
        System.out.println("║ Users:      " + String.format("%-26s", users.size()) + "║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}
    // ...existing code...


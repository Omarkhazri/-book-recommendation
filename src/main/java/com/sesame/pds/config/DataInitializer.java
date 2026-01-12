package com.sesame.pds.config;

import com.sesame.pds.entity.*;
import com.sesame.pds.enums.UserGender;
import com.sesame.pds.enums.UserMartialStatus;
import com.sesame.pds.enums.UserReadingLevel;
import com.sesame.pds.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.*;

/**
 * Initialize dummy data on application startup
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private final BookCategoryRepository bookCategoryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserReadingInfoRepository userReadingInfoRepository;
    private final UserBookRatingRepository userBookRateRepository;

    public DataInitializer(BookCategoryRepository bookCategoryRepository,
                          AuthorRepository authorRepository,
                          BookRepository bookRepository,
                          UserRepository userRepository,
                          UserReadingInfoRepository userReadingInfoRepository,
                           UserBookRatingRepository userBookRateRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userReadingInfoRepository = userReadingInfoRepository;
        this.userBookRateRepository = userBookRateRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (bookCategoryRepository.count() > 0) {
            System.out.println("Database already initialized with data");
            return;
        }

        System.out.println("Initializing database with dummy data...");

        List<BookCategory> categories = createCategories();
        List<Author> authors = createAuthors();
        List<Book> books = createBooks(authors, categories);
        List<User> users = createUsers();

        createUserReadingInfo(users);
        createUserBookRates(users, books);

        System.out.println("Database initialized successfully!");
    }

    private List<BookCategory> createCategories() {
        List<BookCategory> categories = new ArrayList<>();
        String[][] categoryData = {
            {"Science Fiction", "Explore futuristic worlds and technological innovations"},
            {"Mystery", "Thrilling detective stories and puzzles to solve"},
            {"Romance", "Tales of love and relationships"},
            {"Fantasy", "Magical worlds with dragons and wizards"},
            {"Thriller", "Heart-pounding suspenseful stories"},
            {"Biography", "Real-life stories of remarkable people"},
            {"History", "Historical accounts and events"},
            {"Self-Help", "Books for personal growth and development"},
            {"Business", "Professional and entrepreneurial guidance"},
            {"Children", "Stories and adventures for young readers"}
        };

        for (String[] data : categoryData) {
            BookCategory category = new BookCategory();
            category.setName(data[0]);
            category.setDescription(data[1]);
            category.setCreatedBy("SYSTEM");
            category.setModifiedBy("SYSTEM");
            categories.add(bookCategoryRepository.save(category));
        }

        return categories;
    }

    private List<Author> createAuthors() {
        List<Author> authors = new ArrayList<>();

        Object[][] authorData = {
            {"J.K. Rowling", "Author of the world-famous Harry Potter series", LocalDate.of(1965, 7, 31), "United Kingdom", 59, UserGender.FEMALE},
            {"George R.R. Martin", "Fantasy author known for A Song of Ice and Fire", LocalDate.of(1948, 9, 20), "USA", 76, UserGender.MALE},
            {"Agatha Christie", "The Queen of Mystery and Crime novels", LocalDate.of(1890, 1, 15), "United Kingdom", 132, UserGender.FEMALE},
            {"Isaac Asimov", "Pioneer of science fiction literature", LocalDate.of(1920, 1, 2), "USA", 104, UserGender.MALE},
            {"Stephen King", "Master of horror and suspense", LocalDate.of(1947, 9, 21), "USA", 77, UserGender.MALE},
            {"Paulo Coelho", "Author of The Alchemist", LocalDate.of(1947, 8, 24), "Brazil", 77, UserGender.MALE},
            {"Chimamanda Ngozi Adichie", "Contemporary African novelist", LocalDate.of(1977, 9, 15), "Nigeria", 47, UserGender.FEMALE},
            {"Malcolm Gladwell", "Non-fiction writer and journalist", LocalDate.of(1966, 9, 3), "Canada", 58, UserGender.MALE},
            {"Sarah J. Maas", "Fantasy and romance author", LocalDate.of(1986, 3, 5), "USA", 38, UserGender.FEMALE},
            {"Dan Brown", "Thriller author known for The Da Vinci Code", LocalDate.of(1964, 6, 22), "USA", 60, UserGender.MALE}
        };

        for (Object[] data : authorData) {
            Author author = new Author();
            author.setName((String) data[0]);
            author.setDescription((String) data[1]);
            author.setBirthdate(java.sql.Date.valueOf((LocalDate) data[2]));
            author.setCountry((String) data[3]);
            author.setAge((Integer) data[4]);
            author.setGender((UserGender) data[5]);
            author.setImageUrl("https://via.placeholder.com/300?text=" + ((String) data[0]).replace(" ", "+"));
            author.setCreatedBy("SYSTEM");
            author.setModifiedBy("SYSTEM");
            authors.add(authorRepository.save(author));
        }

        return authors;
    }

    private List<Book> createBooks(List<Author> authors, List<BookCategory> categories) {
        List<Book> books = new ArrayList<>();

        Object[][] booksData = {
            {"Harry Potter and the Philosopher's Stone", 1, 4, 4.9, 500000L, 15.99, 309, "The first novel in the Harry Potter series follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday.", "https://books.google.com/books/content?id=s5pG5PiDi3QC&printsec=frontcover&img=1&zoom=1", LocalDate.of(1997, 6, 26), 8},
            {"Harry Potter and the Chamber of Secrets", 1, 4, 4.8, 450000L, 15.99, 341, "Harry and his friends investigate mysterious happenings at Hogwarts School of Witchcraft and Wizardry.", "https://books.google.com/books/content?id=J9LbbS2HX9sC&printsec=frontcover&img=1&zoom=1", LocalDate.of(1998, 7, 2), 9},
            {"A Game of Thrones", 2, 4, 4.7, 380000L, 18.99, 694, "The first novel of A Song of Ice and Fire series, featuring multiple POV characters in a medieval fantasy world.", "https://books.google.com/books/content?id=a0JqzLqYZGQC&printsec=frontcover&img=1&zoom=1", LocalDate.of(1996, 8, 6), 15},
            {"Murder on the Orient Express", 3, 2, 4.6, 250000L, 12.99, 256, "Hercule Poirot investigates a murder on a luxury train bound for Istanbul.", "https://books.google.com/books/content?id=oPbxh8FhkyAC&printsec=frontcover&img=1&zoom=1", LocalDate.of(1934, 1, 1), 5},
            {"Foundation", 4, 1, 4.8, 300000L, 16.99, 255, "A science fiction classic about the fall and rise of galactic civilization.", "https://books.google.com/books/content?id=pJZb3u5V55EC&printsec=frontcover&img=1&zoom=1", LocalDate.of(1951, 6, 1), 8},
            {"The Shining", 5, 5, 4.7, 280000L, 14.99, 447, "A psychological horror novel about a family isolated in a haunted hotel during winter.", "https://books.google.com/books/content?id=2uNz6Rr6xg0C&printsec=frontcover&img=1&zoom=1", LocalDate.of(1977, 1, 28), 12},
            {"The Alchemist", 6, 8, 4.9, 600000L, 13.99, 224, "A philosophical novel about personal legend and self-discovery.", "https://books.google.com/books/content?id=1_h-1vU1m3kC&printsec=frontcover&img=1&zoom=1", LocalDate.of(1988, 1, 1), 5},
            {"Half of a Yellow Sun", 7, 3, 4.6, 220000L, 17.99, 433, "A novel about the Nigerian-Biafran War told through multiple perspectives.", "https://books.google.com/books/content?id=-Bkrc1Fbm-EC&printsec=frontcover&img=1&zoom=1", LocalDate.of(2006, 9, 28), 10},
            {"Outliers", 8, 9, 4.5, 350000L, 16.99, 309, "Non-fiction exploration of what makes people successful.", "https://books.google.com/books/content?id=3NSImqqnxnkC&printsec=frontcover&img=1&zoom=1", LocalDate.of(2008, 11, 18), 7},
            {"Crescent City", 9, 4, 4.8, 400000L, 19.99, 672, "A paranormal romance fantasy novel set in a magical city.", "https://books.google.com/books/content?id=HzIdxAEACAAJ&printsec=frontcover&img=1&zoom=1", LocalDate.of(2020, 1, 30), 14},
            {"The Da Vinci Code", 10, 5, 4.4, 500000L, 15.99, 487, "A mystery thriller about art, history, and codes.", "https://via.placeholder.com/300?text=DaVinci+Code", LocalDate.of(2003, 3, 18), 10},
            {"I, Robot", 4, 1, 4.5, 200000L, 12.99, 224, "A collection of science fiction short stories about robots and AI.", "https://via.placeholder.com/300?text=I+Robot", LocalDate.of(1950, 12, 2), 6},
            {"Death on the Nile", 3, 2, 4.7, 180000L, 12.99, 311, "Hercule Poirot solves a murder on a cruise ship on the Nile River.", "https://via.placeholder.com/300?text=Death+Nile", LocalDate.of(1937, 11, 1), 7},
            {"A Throne of Glass", 9, 4, 4.6, 350000L, 17.99, 416, "A fantasy novel following an assassin in a magical kingdom.", "https://via.placeholder.com/300?text=Throne+Glass", LocalDate.of(2012, 8, 7), 9},
            {"Thinking, Fast and Slow", 8, 8, 4.7, 280000L, 18.99, 499, "A groundbreaking book about human psychology and decision-making.", "https://books.google.com/books/content?id=AV9x8XakdV0C&printsec=frontcover&img=1&zoom=1", LocalDate.of(2011, 10, 25), 10}
        };

        for (Object[] data : booksData) {
            Book book = new Book();
            book.setName((String) data[0]);
            book.setAuthor(authors.get(((Number) data[1]).intValue() - 1));
            book.setCategory(categories.get(((Number) data[2]).intValue() - 1));
            book.setRate(((Number) data[3]).doubleValue());
            book.setUsersRateCount((Long) data[4]);
            book.setPrice(((Number) data[5]).doubleValue());
            book.setPagesNumber(((Number) data[6]).intValue());
            book.setDescription((String) data[7]);
            book.setImageUrl((String) data[8]);
            book.setPublishDate(java.sql.Date.valueOf((LocalDate) data[9]));
            book.setReadingDuration(((Number) data[10]).intValue());
            book.setCreatedBy("SYSTEM");
            book.setModifiedBy("SYSTEM");
            books.add(bookRepository.save(book));
        }

        return books;
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();

        Object[][] userData = {
            {"John", "Doe", "john.doe@example.com", "1234567890", LocalDate.of(1990, 1, 15), "USA", 34, UserGender.MALE, UserMartialStatus.SINGLE},
            {"Jane", "Smith", "jane.smith@example.com", "1234567891", LocalDate.of(1992, 5, 22), "USA", 32, UserGender.FEMALE, UserMartialStatus.MARRIED},
            {"Michael", "Johnson", "michael.johnson@example.com", "1234567892", LocalDate.of(1988, 8, 30), "UK", 36, UserGender.MALE, UserMartialStatus.IN_RELATIONSHIP},
            {"Emma", "Williams", "emma.williams@example.com", "1234567893", LocalDate.of(1995, 3, 10), "Canada", 29, UserGender.FEMALE, UserMartialStatus.SINGLE},
            {"David", "Brown", "david.brown@example.com", "1234567894", LocalDate.of(1991, 11, 20), "Australia", 33, UserGender.MALE, UserMartialStatus.MARRIED},
            {"Sarah", "Davis", "sarah.davis@example.com", "1234567895", LocalDate.of(1993, 7, 5), "USA", 31, UserGender.FEMALE, UserMartialStatus.SINGLE},
            {"Robert", "Miller", "robert.miller@example.com", "1234567896", LocalDate.of(1989, 9, 12), "USA", 35, UserGender.MALE, UserMartialStatus.MARRIED},
            {"Lisa", "Wilson", "lisa.wilson@example.com", "1234567897", LocalDate.of(1994, 2, 28), "Germany", 30, UserGender.FEMALE, UserMartialStatus.IN_RELATIONSHIP},
            {"James", "Moore", "james.moore@example.com", "1234567898", LocalDate.of(1987, 12, 18), "USA", 37, UserGender.MALE, UserMartialStatus.SINGLE},
            {"Angela", "Taylor", "angela.taylor@example.com", "1234567899", LocalDate.of(1996, 6, 11), "France", 28, UserGender.FEMALE, UserMartialStatus.MARRIED}
        };

        String hashedPassword = "$2a$10$cAaEdb381nMIUh16B0OpMOtKoslJuVLsma3vF7a9pUcBtHtKACYUK";

        for (Object[] data : userData) {
            User user = new User();
            user.setFirstName((String) data[0]);
            user.setLastName((String) data[1]);
            user.setEmail((String) data[2]);
            user.setPassword(hashedPassword);
            user.setPhoneNumber((String) data[3]);
            user.setBirthdate(java.sql.Date.valueOf((LocalDate) data[4]));
            user.setCountry((String) data[5]);
            user.setAge(((Number) data[6]).intValue());
            user.setGender((UserGender) data[7]);
            user.setMaritalStatus((UserMartialStatus) data[8]);
            user.setImageUrl("https://via.placeholder.com/300?text=" + ((String) data[0]));
            user.setCreatedBy("SYSTEM");
            user.setModifiedBy("SYSTEM");
            users.add(userRepository.save(user));
        }

        return users;
    }

    private void createUserReadingInfo(List<User> users) {
        UserReadingLevel[] levels = {
            UserReadingLevel.EXPERT,
            UserReadingLevel.EXPERT,
            UserReadingLevel.EXPERT,
            UserReadingLevel.INTERMEDIATE,
            UserReadingLevel.EXPERT,
            UserReadingLevel.BEGINNER,
            UserReadingLevel.EXPERT,
            UserReadingLevel.INTERMEDIATE,
            UserReadingLevel.EXPERT,
            UserReadingLevel.EXPERT
        };

        for (int i = 0; i < users.size(); i++) {
            UserReadingInfo info = new UserReadingInfo();
            info.setUser(users.get(i));
            info.setReadingLevel(levels[i]);
            info.setCreatedBy("SYSTEM");
            info.setModifiedBy("SYSTEM");
            userReadingInfoRepository.save(info);
        }
    }

    private void createUserBookRates(List<User> users, List<Book> books) {
        Object[][] ratingsData = {
            {1, 1, 5, "Amazing start to the series! Loved every page."},
            {1, 2, 5, "Just as good as the first!"},
            {1, 3, 4, "Great fantasy but heavy reading."},
            {1, 5, 5, "Mind-bending science fiction masterpiece."},
            {1, 11, 4, "Interesting mystery, kept me engaged."},
            {2, 1, 5, "Magical world-building is incredible."},
            {2, 4, 4, "Classic mystery, very well constructed."},
            {2, 7, 5, "Life-changing philosophical novel."},
            {2, 9, 4, "Fascinating insights about success."},
            {2, 15, 5, "Changed how I think about decision-making."},
            {3, 3, 5, "Epic fantasy with complex characters."},
            {3, 6, 5, "Terrifying and unforgettable."},
            {3, 8, 4, "Beautiful storytelling about history."},
            {3, 12, 4, "Excellent sci-fi collection."},
            {4, 1, 5, "Perfect for young adults and adults alike."},
            {4, 10, 5, "Addictive paranormal romance."},
            {4, 14, 4, "Strong female protagonist, great adventure."},
            {4, 7, 5, "Truly inspirational and motivating."},
            {5, 2, 5, "Continued brilliance throughout."},
            {5, 5, 5, "Foundation of science fiction greatness."},
            {5, 11, 4, "Page-turner mystery thriller."},
            {5, 13, 4, "Classic Agatha Christie at her best."},
            {6, 4, 5, "Masterpiece of detective fiction."},
            {6, 7, 5, "Everyone should read this book."},
            {6, 9, 3, "Interesting but some parts were slow."},
            {7, 3, 5, "Complex world-building, worth the read."},
            {7, 8, 5, "Powerful exploration of war and humanity."},
            {7, 15, 4, "Insightful look at human behavior."},
            {8, 1, 4, "Great introduction to the wizarding world."},
            {8, 6, 4, "Creepy and compelling."},
            {8, 10, 5, "Urban fantasy at its finest."},
            {8, 12, 4, "Thoughtful robot stories."},
            {9, 5, 5, "Visionary science fiction work."},
            {9, 11, 5, "Clever plot with great twist."},
            {9, 14, 5, "Exceptional character development."},
            {10, 7, 5, "Life-altering perspective on destiny."},
            {10, 9, 4, "Great insights into achievement."},
            {10, 15, 5, "Essential reading on thinking."}
        };

        for (Object[] data : ratingsData) {
            UserBookRate rate = new UserBookRate();
            rate.setUser(users.get(((Number) data[0]).intValue() - 1));
            rate.setBook(books.get(((Number) data[1]).intValue() - 1));
            rate.setRate(((Number) data[2]).intValue());
            rate.setComment((String) data[3]);
            rate.setCreatedBy("SYSTEM");
            rate.setModifiedBy("SYSTEM");
            userBookRateRepository.save(rate);
        }
    }
}


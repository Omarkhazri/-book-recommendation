package com.sesame.pds.service;

import org.springframework.stereotype.Service;

/**
 * Service for fetching book covers from Google Books API
 */
@Service
public class GoogleBooksService {

    private static final String GOOGLE_BOOKS_COVER_URL = "https://covers.openlibrary.org/b/isbn/{isbn}-M.jpg";
    private static final String FALLBACK_COVER_URL = "https://via.placeholder.com/300x450?text=Book+Cover";

    /**
     * Get book cover URL from Google Books API using ISBN
     *
     * @param isbn The ISBN of the book
     * @return The URL of the book cover
     */
    public String getBookCoverUrl(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return FALLBACK_COVER_URL;
        }

        // Clean ISBN (remove hyphens)
        String cleanedIsbn = isbn.replaceAll("-", "");

        // Return Open Library cover URL (more reliable than Google Books)
        return GOOGLE_BOOKS_COVER_URL.replace("{isbn}", cleanedIsbn);
    }

    /**
     * Get alternative cover URL for fallback
     *
     * @param bookTitle The title of the book
     * @return The fallback placeholder URL
     */
    public String getFallbackCoverUrl(String bookTitle) {
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            return FALLBACK_COVER_URL;
        }

        String encodedTitle = bookTitle.replaceAll(" ", "+");
        return "https://via.placeholder.com/300x450?text=" + encodedTitle;
    }
}


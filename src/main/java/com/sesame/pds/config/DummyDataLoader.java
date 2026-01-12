package com.sesame.pds.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.*;

/**
 * Utility class for loading and parsing dummy data from JSON file
 */
@Component
public class DummyDataLoader {

    private final ObjectMapper objectMapper;
    private Map<String, Object> dataMap;

    public DummyDataLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.dataMap = loadData();
    }

    /**
     * Load dummy data from JSON file
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> loadData() {
        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("data/dummy-data.json");
            if (inputStream == null) {
                throw new RuntimeException("dummy-data.json not found in classpath");
            }
            return objectMapper.readValue(inputStream, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load dummy data from JSON", e);
        }
    }

    /**
     * Get all categories from data
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getCategories() {
        return (List<Map<String, Object>>) dataMap.get("categories");
    }

    /**
     * Get all authors from data
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getAuthors() {
        return (List<Map<String, Object>>) dataMap.get("authors");
    }

    /**
     * Get all books from data
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getBooks() {
        return (List<Map<String, Object>>) dataMap.get("books");
    }

    /**
     * Get all users from data
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getUsers() {
        return (List<Map<String, Object>>) dataMap.get("users");
    }

    /**
     * Get all ratings from data
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getRatings() {
        return (List<Map<String, Object>>) dataMap.get("ratings");
    }

    /**
     * Get all reading levels from data
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getReadingLevels() {
        return (List<Map<String, Object>>) dataMap.get("readingLevels");
    }
}


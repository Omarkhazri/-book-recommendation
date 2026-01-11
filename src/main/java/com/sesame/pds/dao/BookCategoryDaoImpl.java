package com.sesame.pds.dao;

import com.sesame.pds.entity.BookCategory;
import com.sesame.pds.repository.BookCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Component
public class BookCategoryDaoImpl implements BookCategoryDao {
    private final BookCategoryRepository bookCategoryRepository;

    public BookCategoryDaoImpl(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Override
    public BookCategoryRepository getRepository() {
        return bookCategoryRepository;
    }

    @Override
    public List<BookCategory> findAll() {
        return getRepository().findAllByMarkedAsDeletedFalse();
    }
}

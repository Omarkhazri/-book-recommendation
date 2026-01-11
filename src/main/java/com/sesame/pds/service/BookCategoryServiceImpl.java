package com.sesame.pds.service;

import com.sesame.pds.dao.BookCategoryDao;
import com.sesame.pds.transformer.BookCategoryTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Slf4j
@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryDao bookCategoryDao;
    private final BookCategoryTransformer bookCategoryTransformer;

    public BookCategoryServiceImpl(BookCategoryDao bookCategoryDao, BookCategoryTransformer bookCategoryTransformer) {
        this.bookCategoryDao = bookCategoryDao;
        this.bookCategoryTransformer = bookCategoryTransformer;
    }

    @Override
    public BookCategoryDao getDao() {
        return bookCategoryDao;
    }

    @Override
    public BookCategoryTransformer getTransformer() {
        return bookCategoryTransformer;
    }
}

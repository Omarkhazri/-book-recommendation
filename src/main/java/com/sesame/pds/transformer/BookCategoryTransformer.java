package com.sesame.pds.transformer;

import com.sesame.pds.dto.BookCategoryDto;
import com.sesame.pds.entity.BookCategory;
import com.sesame.pds.transformer.base.BaseTransformer;
import com.sesame.pds.transformer.mapper.BookCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Component
@AllArgsConstructor
public class BookCategoryTransformer implements BaseTransformer<BookCategory, BookCategoryDto, BookCategoryMapper> {
    private final BookCategoryMapper bookCategoryMapper;

    @Override
    public BookCategoryMapper getMapper() {
        return bookCategoryMapper;
    }
}

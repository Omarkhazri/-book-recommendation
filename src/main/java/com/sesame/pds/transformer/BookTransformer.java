package com.sesame.pds.transformer;

import com.sesame.pds.dto.BookDto;
import com.sesame.pds.entity.Book;
import com.sesame.pds.transformer.base.BaseTransformer;
import com.sesame.pds.transformer.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Component
@AllArgsConstructor
public class BookTransformer implements BaseTransformer<Book, BookDto, BookMapper> {
    private final BookMapper bookMapper;

    @Override
    public BookMapper getMapper() {
        return bookMapper;
    }
}

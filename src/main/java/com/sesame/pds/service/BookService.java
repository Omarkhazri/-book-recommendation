package com.sesame.pds.service;

import com.sesame.pds.dao.BookDao;
import com.sesame.pds.dto.BookDto;
import com.sesame.pds.dto.BookFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.dto.base.response.PaginationResponse;
import com.sesame.pds.entity.Book;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.BookTransformer;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
public interface BookService extends BaseService<Book, BookDto, BookDao, BookTransformer> {
    List<BookDto> findAllBooksByAuthorId(Long authorId);

    PaginationResponse<BookDto> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest);

    List<BookDto> findAllRecommendedBooks();
}

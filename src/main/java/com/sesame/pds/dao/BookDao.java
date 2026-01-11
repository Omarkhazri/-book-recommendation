package com.sesame.pds.dao;

import com.sesame.pds.dao.base.BaseDao;
import com.sesame.pds.dto.BookFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.entity.Book;
import com.sesame.pds.repository.BookRepository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
public interface BookDao extends BaseDao<Book, BookRepository> {
    List<Book> findAllBooksByAuthorId(Long authorId);

    List<Book> findAllBooksByCategoriesAndLimit(List<String> categories, Integer limit);

    Page<Book> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest);
}

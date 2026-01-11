package com.sesame.pds.dao;

import com.sesame.pds.dto.BookFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.entity.Book;
import com.sesame.pds.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Component
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRepository getRepository() {
        return bookRepository;
    }

    @Override
    public List<Book> findAllBooksByAuthorId(Long authorId) {
        return getRepository().findAllByAuthorIdAndMarkedAsDeletedFalse(authorId);
    }

    @Override
    public List<Book> findAllBooksByCategoriesAndLimit(List<String> categories, Integer limit) {
        List<Book> books = getRepository().findAllByCategoryNameInAndMarkedAsDeletedFalse(categories);
        if (books.size() <= limit) return books;
        return books.subList(0, limit);
    }

    @Override
    public Page<Book> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        PageRequest pageRequest = getPageRequest(bookFilterPaginationRequest);
        BookFilterPaginationRequest criteria = bookFilterPaginationRequest.getCriteria();

        // Extract parameters with null safety
        String name = null;
        Set<Long> categoryIds = null;
        Double fromPrice = null;
        Double toPrice = null;
        Integer fromPagesNumber = null;
        Integer toPagesNumber = null;
        Integer fromReadingDuration = null;
        Integer toReadingDuration = null;

        if (criteria != null) {
            name = criteria.getName();
            categoryIds = criteria.getCategoryIdsAsSet();
            fromPrice = criteria.getFromPrice();
            toPrice = criteria.getToPrice();
            fromPagesNumber = criteria.getFromPagesNumber();
            toPagesNumber = criteria.getToPagesNumber();
            fromReadingDuration = criteria.getFromReadingDuration();
            toReadingDuration = criteria.getToReadingDuration();
        }

        // Default deletedRecords to false if null
        Boolean deletedRecords = bookFilterPaginationRequest.getDeletedRecords();
        if (deletedRecords == null) {
            deletedRecords = false;  // Default: show non-deleted books
        }

        System.out.println("[BookDAO] ========== DEBUG INFO ==========");
        System.out.println("[BookDAO] criteria: " + criteria);
        System.out.println("[BookDAO] name: " + name);
        System.out.println("[BookDAO] categoryIds from getCategoryIdsAsSet(): " + categoryIds);
        System.out.println("[BookDAO] deletedRecords: " + deletedRecords);

        // If no criteria or empty criteria, use simple query
        boolean hasNoCriteria = criteria == null || (name == null && categoryIds == null && fromPrice == null && toPrice == null &&
                fromPagesNumber == null && toPagesNumber == null && fromReadingDuration == null && toReadingDuration == null);

        System.out.println("[BookDAO] hasNoCriteria: " + hasNoCriteria);

        if (hasNoCriteria) {
            System.out.println("[BookDAO] Using simple query - no filters");
            return getRepository().findAllBooksPaginatedAndFiltered(deletedRecords, pageRequest);
        }

        // If category IDs are provided, use filtered query with categories
        if (categoryIds != null && !categoryIds.isEmpty()) {
            System.out.println("[BookDAO] Using query WITH categories: " + categoryIds);
            Page<Book> result = getRepository().findAllBooksPaginatedAndFilteredWithCategories(name, categoryIds,
                    fromPrice, toPrice, fromPagesNumber, toPagesNumber,
                    fromReadingDuration, toReadingDuration, deletedRecords, pageRequest);
            System.out.println("[BookDAO] Result count: " + result.getTotalElements());
            return result;
        }

        // Otherwise use filtered query without categories
        System.out.println("[BookDAO] Using query without categories");
        Page<Book> result = getRepository().findAllBooksPaginatedAndFilteredWithoutCategories(name,
                fromPrice, toPrice, fromPagesNumber, toPagesNumber,
                fromReadingDuration, toReadingDuration, deletedRecords, pageRequest);
        System.out.println("[BookDAO] Result count: " + result.getTotalElements());
        return result;
    }

    private PageRequest getPageRequest(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        // Handle page size: if -1, use max value; otherwise ensure it's at least 1
        int pageSize = bookFilterPaginationRequest.getPageSize();
        if (pageSize <= 0) {
            pageSize = pageSize == -1 ? Integer.MAX_VALUE : 12; // Default to 12 if invalid
        }
        bookFilterPaginationRequest.setPageSize(pageSize);

        // pageNumber is 0-based from frontend, PageRequest.of expects 0-based index
        // So we use pageNumber directly without subtracting 1
        int pageNumber = Math.max(0, bookFilterPaginationRequest.getPageNumber());

        return PageRequest.of(pageNumber, pageSize, buildSort(bookFilterPaginationRequest, Book.class));
    }
}

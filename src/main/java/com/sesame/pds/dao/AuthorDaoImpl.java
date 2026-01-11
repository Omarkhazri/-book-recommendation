package com.sesame.pds.dao;

import com.sesame.pds.dto.AuthorFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.entity.Author;
import com.sesame.pds.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Component
public class AuthorDaoImpl implements AuthorDao {
    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorRepository getRepository() {
        return authorRepository;
    }

    @Override
    public Page<Author> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest) {
        PageRequest pageRequest = getPageRequest(authorFilterPaginationRequest);
        AuthorFilterPaginationRequest criteria = authorFilterPaginationRequest.getCriteria();
        if (criteria == null)
            return getRepository().findAll(pageRequest, authorFilterPaginationRequest.getDeletedRecords());
        return getRepository().findAllAuthorsPaginatedAndFiltered(criteria.getName(), authorFilterPaginationRequest.getDeletedRecords(), pageRequest);
    }

    private PageRequest getPageRequest(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest) {
        if (authorFilterPaginationRequest.getPageSize() == -1)
            authorFilterPaginationRequest.setPageSize(Integer.MAX_VALUE);
        return PageRequest.of(authorFilterPaginationRequest.getPageNumber() - 1, authorFilterPaginationRequest.getPageSize(), buildSort(authorFilterPaginationRequest, Author.class));
    }
}

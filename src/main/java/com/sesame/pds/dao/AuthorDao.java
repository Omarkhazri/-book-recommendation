package com.sesame.pds.dao;

import com.sesame.pds.dao.base.BaseDao;
import com.sesame.pds.dto.AuthorFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.entity.Author;
import com.sesame.pds.repository.AuthorRepository;
import org.springframework.data.domain.Page;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
public interface AuthorDao extends BaseDao<Author, AuthorRepository> {
    Page<Author> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest);
}

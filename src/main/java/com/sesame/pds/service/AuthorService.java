package com.sesame.pds.service;

import com.sesame.pds.dao.AuthorDao;
import com.sesame.pds.dto.AuthorDto;
import com.sesame.pds.dto.AuthorFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.dto.base.response.PaginationResponse;
import com.sesame.pds.entity.Author;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.AuthorTransformer;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
public interface AuthorService extends BaseService<Author, AuthorDto, AuthorDao, AuthorTransformer> {
    PaginationResponse<AuthorDto> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest);
}

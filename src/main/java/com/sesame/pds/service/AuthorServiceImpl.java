package com.sesame.pds.service;

import com.sesame.pds.dao.AuthorDao;
import com.sesame.pds.dto.AuthorDto;
import com.sesame.pds.dto.AuthorFilterPaginationRequest;
import com.sesame.pds.dto.base.pagination.FilterPaginationRequest;
import com.sesame.pds.dto.base.response.PaginationResponse;
import com.sesame.pds.transformer.AuthorTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author KHAZRI OMAR
 * @since 07/11/2022
 */
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorTransformer authorTransformer;
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorTransformer authorTransformer, AuthorDao authorDao) {
        this.authorTransformer = authorTransformer;
        this.authorDao = authorDao;
    }

    @Override
    public AuthorDao getDao() {
        return authorDao;
    }

    @Override
    public AuthorTransformer getTransformer() {
        return authorTransformer;
    }

    @Override
    public PaginationResponse<AuthorDto> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest) {
        log.info("AuthorService: findAllAuthorsPaginatedAndFiltered() called");
        return buildPaginationResponse(getDao().findAllAuthorsPaginatedAndFiltered(authorFilterPaginationRequest));
    }
}

package com.sesame.pds.service;


import com.sesame.pds.dao.UserBookCategoryDao;
import com.sesame.pds.dto.UserBookCategoryDto;
import com.sesame.pds.entity.UserBookCategory;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.UserBookCategoryTransformer;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
public interface UserBookCategoryService extends BaseService<UserBookCategory, UserBookCategoryDto, UserBookCategoryDao, UserBookCategoryTransformer> {
    List<UserBookCategoryDto> findAllUserBookCategories();
}

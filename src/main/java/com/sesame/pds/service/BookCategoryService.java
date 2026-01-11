package com.sesame.pds.service;

import com.sesame.pds.dao.BookCategoryDao;
import com.sesame.pds.dto.BookCategoryDto;
import com.sesame.pds.entity.BookCategory;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.BookCategoryTransformer;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
public interface BookCategoryService extends BaseService<BookCategory, BookCategoryDto, BookCategoryDao, BookCategoryTransformer> {
}

package com.sesame.pds.dao;

import com.sesame.pds.dao.base.BaseDao;
import com.sesame.pds.entity.UserBookCategory;
import com.sesame.pds.repository.UserBookCategoryRepository;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
public interface UserBookCategoryDao extends BaseDao<UserBookCategory, UserBookCategoryRepository> {
    List<UserBookCategory> findAllByUserId(Long userId);
}

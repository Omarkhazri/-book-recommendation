package com.sesame.pds.dao;

import com.sesame.pds.entity.UserBookCategory;
import com.sesame.pds.repository.UserBookCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Component
public class UserBookCategoryDaoImpl implements UserBookCategoryDao {
    private final UserBookCategoryRepository userBookCategoryRepository;

    public UserBookCategoryDaoImpl(UserBookCategoryRepository userBookCategoryRepository) {
        this.userBookCategoryRepository = userBookCategoryRepository;
    }

    @Override
    public UserBookCategoryRepository getRepository() {
        return userBookCategoryRepository;
    }

    @Override
    public List<UserBookCategory> findAllByUserId(Long userId) {
        return getRepository().findAllByUserIdAndMarkedAsDeletedFalse(userId);
    }
}

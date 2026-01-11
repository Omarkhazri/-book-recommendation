package com.sesame.pds.dao;

import com.sesame.pds.dao.base.BaseDao;
import com.sesame.pds.entity.UserBookRate;
import com.sesame.pds.repository.UserBookRatingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 08/11/2022
 */
public interface UserBookRateDao extends BaseDao<UserBookRate, UserBookRatingRepository> {
    Optional<UserBookRate> findUserBookRateByUserIdAndBookId(Long userId, Long bookId);

    List<UserBookRate> findAllByBookIdAndMarkedAsDeletedFalse(Long bookId);
}

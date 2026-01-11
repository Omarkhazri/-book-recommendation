package com.sesame.pds.repository;

import com.sesame.pds.entity.UserBookRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author KHAZRI OMAR
 * @since 09/11/2022
 */
@Repository
public interface UserBookRatingRepository extends JpaRepository<UserBookRate, Long> {
    Optional<UserBookRate> findUserBookRateByUserIdAndBookId(Long userId, Long bookId);

    List<UserBookRate> findAllByUserIdAndMarkedAsDeletedFalse(Long userID);

    List<UserBookRate> findAllByBookIdAndMarkedAsDeletedFalse(Long bookId);
}
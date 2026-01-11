package com.sesame.pds.repository;

import com.sesame.pds.entity.UserReadingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Repository
public interface UserReadingInfoRepository extends JpaRepository<UserReadingInfo, Long> {
    Optional<UserReadingInfo> findByUserId(Long userId);
}

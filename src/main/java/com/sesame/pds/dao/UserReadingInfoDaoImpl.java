package com.sesame.pds.dao;

import com.sesame.pds.entity.UserReadingInfo;
import com.sesame.pds.repository.UserReadingInfoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Component
public class UserReadingInfoDaoImpl implements UserReadingInfoDao {
    private final UserReadingInfoRepository userReadingInfoRepository;

    public UserReadingInfoDaoImpl(UserReadingInfoRepository userReadingInfoRepository) {
        this.userReadingInfoRepository = userReadingInfoRepository;
    }

    @Override
    public UserReadingInfoRepository getRepository() {
        return userReadingInfoRepository;
    }

    @Override
    public Optional<UserReadingInfo> findByUserId(Long userId) {
        return getRepository().findByUserId(userId);
    }
}

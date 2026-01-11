package com.sesame.pds.dao;

import com.sesame.pds.dao.base.BaseDao;
import com.sesame.pds.entity.UserReadingInfo;
import com.sesame.pds.repository.UserReadingInfoRepository;

import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
public interface UserReadingInfoDao extends BaseDao<UserReadingInfo, UserReadingInfoRepository> {
    Optional<UserReadingInfo> findByUserId(Long userId);
}

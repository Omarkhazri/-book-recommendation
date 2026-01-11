package com.sesame.pds.service;


import com.sesame.pds.dao.UserReadingInfoDao;
import com.sesame.pds.dto.UserReadingInfoDto;
import com.sesame.pds.entity.UserReadingInfo;
import com.sesame.pds.service.base.BaseService;
import com.sesame.pds.transformer.UserReadingInfoTransformer;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
public interface UserReadingInfoService extends BaseService<UserReadingInfo, UserReadingInfoDto, UserReadingInfoDao, UserReadingInfoTransformer> {
    UserReadingInfoDto findUserReadingInfo();
}

package com.sesame.pds.transformer;

import com.sesame.pds.dto.UserReadingInfoDto;
import com.sesame.pds.entity.UserReadingInfo;
import com.sesame.pds.transformer.base.BaseTransformer;
import com.sesame.pds.transformer.mapper.UserReadingInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Component
@AllArgsConstructor
public class UserReadingInfoTransformer implements BaseTransformer<UserReadingInfo, UserReadingInfoDto, UserReadingInfoMapper> {
    private final UserReadingInfoMapper userReadingInfoMapper;

    @Override
    public UserReadingInfoMapper getMapper() {
        return userReadingInfoMapper;
    }
}

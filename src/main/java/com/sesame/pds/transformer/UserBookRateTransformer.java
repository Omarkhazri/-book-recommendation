package com.sesame.pds.transformer;

import com.sesame.pds.dto.UserBookRateDto;
import com.sesame.pds.entity.UserBookRate;
import com.sesame.pds.transformer.base.BaseTransformer;
import com.sesame.pds.transformer.mapper.UserBookRateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 09/11/2022
 */
@Component
@AllArgsConstructor
public class UserBookRateTransformer implements BaseTransformer<UserBookRate, UserBookRateDto, UserBookRateMapper> {
    private final UserBookRateMapper userBookRateMapper;

    @Override
    public UserBookRateMapper getMapper() {
        return userBookRateMapper;
    }
}

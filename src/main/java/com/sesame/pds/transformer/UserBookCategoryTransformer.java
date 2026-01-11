package com.sesame.pds.transformer;

import com.sesame.pds.dto.UserBookCategoryDto;
import com.sesame.pds.entity.UserBookCategory;
import com.sesame.pds.transformer.base.BaseTransformer;
import com.sesame.pds.transformer.mapper.UserBookCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Component
@AllArgsConstructor
public class UserBookCategoryTransformer implements BaseTransformer<UserBookCategory, UserBookCategoryDto, UserBookCategoryMapper> {
    private final UserBookCategoryMapper userBookCategoryMapper;

    @Override
    public UserBookCategoryMapper getMapper() {
        return userBookCategoryMapper;
    }
}

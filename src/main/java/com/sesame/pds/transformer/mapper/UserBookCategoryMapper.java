package com.sesame.pds.transformer.mapper;

import com.sesame.pds.dto.UserBookCategoryDto;
import com.sesame.pds.entity.UserBookCategory;
import com.sesame.pds.transformer.mapper.base.BaseMapper;
import com.sesame.pds.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserBookCategoryMapper extends BaseMapper<UserBookCategory, UserBookCategoryDto> {
}

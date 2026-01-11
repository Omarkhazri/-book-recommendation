package com.sesame.pds.transformer.mapper;

import com.sesame.pds.dto.BookCategoryDto;
import com.sesame.pds.entity.BookCategory;
import com.sesame.pds.transformer.mapper.base.BaseMapper;
import com.sesame.pds.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface BookCategoryMapper extends BaseMapper<BookCategory, BookCategoryDto> {
}

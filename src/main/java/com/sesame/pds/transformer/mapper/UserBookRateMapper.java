package com.sesame.pds.transformer.mapper;

import com.sesame.pds.dto.UserBookRateDto;
import com.sesame.pds.entity.UserBookRate;
import com.sesame.pds.transformer.mapper.base.BaseMapper;
import com.sesame.pds.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author KHAZRI OMAR
 * @since 09/11/2022
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserBookRateMapper extends BaseMapper<UserBookRate, UserBookRateDto> {
}

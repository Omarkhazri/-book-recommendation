package com.sesame.pds.transformer.mapper;

import com.sesame.pds.dto.UserDto;
import com.sesame.pds.entity.User;
import com.sesame.pds.transformer.mapper.base.BaseMapper;
import com.sesame.pds.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}

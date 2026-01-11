package com.sesame.pds.transformer.base;

import com.sesame.pds.dto.base.BaseDto;
import com.sesame.pds.entity.base.BaseEntity;
import com.sesame.pds.transformer.mapper.base.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
public interface BaseTransformer<Entity extends BaseEntity, Dto extends BaseDto, Mapper extends BaseMapper<Entity, Dto>> {

    Mapper getMapper();

    default Dto transformEntityToDto(Entity entity) {
        return getMapper().entityToDto(entity);
    }

    default List<Dto> transformEntityToDto(List<Entity> entities) {
        return entities.stream().map(this::transformEntityToDto).collect(Collectors.toList());
    }

    default Entity transformDtoToEntity(Dto dto) {
        return getMapper().dtoToEntity(dto);
    }

    default List<Entity> transformDtoToEntity(List<Dto> dtos) {
        return dtos.stream().map(this::transformDtoToEntity).collect(Collectors.toList());
    }

    default void updateEntity(Dto dto, Entity entity) {
        getMapper().updateEntity(dto, entity);
    }

}
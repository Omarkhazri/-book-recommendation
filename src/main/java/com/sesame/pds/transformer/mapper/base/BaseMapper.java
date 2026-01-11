package com.sesame.pds.transformer.mapper.base;

import com.sesame.pds.dto.base.BaseDto;
import com.sesame.pds.entity.base.BaseEntity;
import org.mapstruct.MappingTarget;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
public interface BaseMapper<Entity extends BaseEntity, Dto extends BaseDto> {

    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);

    void updateEntity(Dto dto, @MappingTarget Entity entity);
}

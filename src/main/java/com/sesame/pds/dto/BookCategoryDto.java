package com.sesame.pds.dto;

import com.sesame.pds.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryDto extends BaseDto {
    private Long id;
    private String name;
    private String description;
}

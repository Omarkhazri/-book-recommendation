package com.sesame.pds.dto;

import com.sesame.pds.dto.base.BaseDto;
import com.sesame.pds.enums.UserReadingLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReadingInfoDto extends BaseDto {
    private Long id;
    private UserDto user;
    private UserReadingLevel readingLevel;
    private List<UserBookCategoryDto> userBookCategories;
}

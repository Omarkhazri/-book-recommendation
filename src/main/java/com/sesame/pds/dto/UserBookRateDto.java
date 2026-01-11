package com.sesame.pds.dto;

import com.sesame.pds.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author KHAZRI OMAR
 * @since 05/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBookRateDto extends BaseDto {
    private Long id;
    private UserDto user;
    private BookDto book;
    private Integer rate;
    private String comment;
}

package com.sesame.pds.dto;

import com.sesame.pds.dto.base.BaseDto;
import com.sesame.pds.enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author KHAZRI OMAR
 * @since 05/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto extends BaseDto {
    private Long id;
    private String name;
    private String description;
    private Date birthdate;
    private Date deathdate;
    private String country;
    private Integer age;
    private UserGender gender;
    private String imageUrl;
}

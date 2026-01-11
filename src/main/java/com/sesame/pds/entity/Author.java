package com.sesame.pds.entity;

import com.sesame.pds.entity.base.BaseEntity;
import com.sesame.pds.enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

/**
 * @author KHAZRI OMAR
 * @since 05/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "author", schema = "public")
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "deathdate")
    private Date deathdate;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private UserGender gender;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}

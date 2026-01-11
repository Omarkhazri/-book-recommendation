package com.sesame.pds.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@Table(name = "refresh_token", schema = "public")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "refresh_count")
    private Long refreshCount;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "marked_as_deleted")
    private Boolean markedAsDeleted = false;
}

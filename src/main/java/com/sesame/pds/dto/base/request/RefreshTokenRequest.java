package com.sesame.pds.dto.base.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author KHAZRI OMAR
 * @since 03/11/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    private String email;
    private String refreshToken;
}

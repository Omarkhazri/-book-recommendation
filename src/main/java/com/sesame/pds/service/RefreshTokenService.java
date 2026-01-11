package com.sesame.pds.service;


import com.sesame.pds.dto.base.request.RefreshTokenRequest;
import com.sesame.pds.entity.RefreshToken;

/**
 * @author KHAZRI OMAR
 * @since 05/11/2022
 */
public interface RefreshTokenService {
    RefreshToken findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(String email);

    RefreshToken refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean deleteRefreshToken(String email);
}

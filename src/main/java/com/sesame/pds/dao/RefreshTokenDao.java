package com.sesame.pds.dao;

import com.sesame.pds.entity.RefreshToken;

import java.util.Optional;

/**
 * @author KHAZRI OMAR
 * @since 05/11/2022
 */
public interface RefreshTokenDao {
    Optional<RefreshToken> findRefreshTokenByEmail(String email);

    Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(RefreshToken refreshToken);

    RefreshToken updateRefreshToken(RefreshToken refreshToken);

    Boolean deleteRefreshTokenByEmail(String email);
}

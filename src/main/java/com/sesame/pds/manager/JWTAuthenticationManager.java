package com.sesame.pds.manager;

import com.sesame.pds.dto.base.request.AuthRequest;
import com.sesame.pds.dto.base.request.RefreshTokenRequest;
import com.sesame.pds.dto.base.response.AuthResponse;

/**
 * @author KHAZRI OMAR
 * @since 04/01/2025
 */
public interface JWTAuthenticationManager {
    AuthResponse login(AuthRequest authRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean logout();

    String getCurrentUserEmail();
}

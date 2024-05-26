package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String uuid);
    RefreshToken verifyRefreshToken(String refreshToken);

}

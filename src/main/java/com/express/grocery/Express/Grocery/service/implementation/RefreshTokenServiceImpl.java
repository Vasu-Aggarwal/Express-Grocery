package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.entity.RefreshToken;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.RefreshTokenRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    public long refreshTokenValidity = 5*60*60*1000;   //5 hr

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(String.format("User with email: %s not found", email), 0));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null){
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
        } else {
            refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        user.setRefreshToken(refreshToken);

        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new ResourceNotFoundException("Refresh token does not exists", 0));

        if (refreshTokenOb.getExpiry().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshTokenOb);
            throw new BadRequestException("Refresh Token expired");
        }

        return refreshTokenOb;
    }
}

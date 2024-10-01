package com.backEnd.service;

import com.backEnd.dto.AccessTokenDto;
import com.backEnd.dto.RegisterDto;
import com.backEnd.dto.UserDto;
import com.backEnd.entity.TokenInfo;
import com.backEnd.utils.AppResponse;

public interface AuthService {

    AppResponse login(String username, String password);
    UserDto register(RegisterDto registerDto);
    TokenInfo createLoginToken(String userName, Long userId);
    AccessTokenDto refreshAccessToken(String refreshToken);
    void logoutUser(String refreshToken);

}

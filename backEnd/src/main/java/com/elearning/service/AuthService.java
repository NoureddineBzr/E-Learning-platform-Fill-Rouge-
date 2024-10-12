package com.elearning.service;

import com.elearning.Dto.AccessTokenDto;
import com.elearning.Dto.JWTResponseDto;
import com.elearning.Dto.RegisterDto;
import com.elearning.Dto.UserDto;
import com.elearning.entity.AppUser;
import com.elearning.entity.TokenInfo;
import com.elearning.utils.AppResponse;

public interface AuthService {

     AppResponse login(String username, String password);
     UserDto register(RegisterDto registerDto);
     TokenInfo createLoginToken(String userName, Long userId);
     AccessTokenDto refreshAccessToken(String refreshToken);
     void logoutUser(String refreshToken);

}

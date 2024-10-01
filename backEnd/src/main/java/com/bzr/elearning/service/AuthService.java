package com.bzr.elearning.service;

import com.bzr.elearning.Dto.AccessTokenDto;
import com.bzr.elearning.Dto.JWTResponseDto;
import com.bzr.elearning.Dto.RegisterDto;
import com.bzr.elearning.Dto.UserDto;
import com.bzr.elearning.entity.AppUser;
import com.bzr.elearning.entity.TokenInfo;
import com.bzr.elearning.utils.AppResponse;

public interface AuthService {

     AppResponse login(String username, String password);
     UserDto register(RegisterDto registerDto);
     TokenInfo createLoginToken(String userName, Long userId);
     AccessTokenDto refreshAccessToken(String refreshToken);
     void logoutUser(String refreshToken);

}

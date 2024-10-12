package com.elearning.service;

import com.elearning.Dto.PublicUserDto;
import com.elearning.Dto.UserDto;
import com.elearning.entity.AppUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    AppUser save(UserDto registerRequest);

    List<UserDto> findAll();
    PublicUserDto findById(Long id);
    UserDto getMyProfile();
    UserDto updateProfile(UserDto newData);
    void updateProfileImage(MultipartFile file) throws IOException;
    UserDto updatePassword(String newPassword);


}

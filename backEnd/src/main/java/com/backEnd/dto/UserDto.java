package com.backEnd.dto;

import com.backEnd.entity.Organization;
import com.backEnd.enums.Country;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImage;
    private Date dateOfBirth;
    private Country nationality;
    private Organization organization;
    private Set<RoleDto> roles = new HashSet<>();
    private boolean isEnabled;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;

    private Set<CourseDto> uploadedCourses;
}

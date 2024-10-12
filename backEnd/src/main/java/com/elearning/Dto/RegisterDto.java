package com.elearning.Dto;

import com.elearning.entity.Organization;
import com.elearning.enums.Country;
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
public class RegisterDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}

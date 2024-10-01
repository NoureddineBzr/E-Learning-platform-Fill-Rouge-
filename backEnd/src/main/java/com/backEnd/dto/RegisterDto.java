package com.backEnd.dto;

import lombok.*;

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

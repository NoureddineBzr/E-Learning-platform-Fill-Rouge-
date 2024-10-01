package com.backEnd.dto;

import com.backEnd.entity.Organization;
import com.backEnd.enums.Country;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PublicUserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImage;
    private Date dateOfBirth;
    private Country nationality;
    private Organization organization;
    private boolean isEnabled;
    private List<CourseDto> uploadedCourses;
}

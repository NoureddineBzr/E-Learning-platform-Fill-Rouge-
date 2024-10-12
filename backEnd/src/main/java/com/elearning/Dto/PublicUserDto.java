package com.elearning.Dto;

import com.elearning.entity.Organization;
import com.elearning.enums.Country;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


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

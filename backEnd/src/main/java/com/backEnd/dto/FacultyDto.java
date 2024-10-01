package com.backEnd.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyDto {

    private Long id;
    private AcademicSpecializationDto sepicailization;
    private UniversityDto university;
}

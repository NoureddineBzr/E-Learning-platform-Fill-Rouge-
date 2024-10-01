package com.backEnd.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LectureUploadRequest {

    private String title;
    private String description;
    private CourseDto course;

}
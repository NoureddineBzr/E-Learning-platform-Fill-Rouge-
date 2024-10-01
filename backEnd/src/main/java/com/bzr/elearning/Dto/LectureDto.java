package com.bzr.elearning.Dto;


import com.bzr.elearning.Dto.CourseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LectureDto {

    private Long id;
    private String title;
    private String description;
    private int length;
    private String coverImage;
    private String video;
    private CourseDto course;
    private int lectureOrder;

}

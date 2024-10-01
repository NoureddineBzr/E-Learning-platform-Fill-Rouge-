package com.backEnd.mapping;

import com.backEnd.dto.StudentEnrolledCourseDto;
import com.backEnd.entity.StudentsEnrolledCourse;

public class StudentCourseMapper {

    public static StudentEnrolledCourseDto entityToDto(StudentsEnrolledCourse entity) {
        return StudentEnrolledCourseDto.builder()
                .course(CourseMapper.entityToDto(entity.getCourse()))
                .enrolledAt(entity.getEnrolledAt())
                .progress(entity.getProgress())
                .build();
    }

    public static StudentsEnrolledCourse dtoToEntity(CourseMapper dto) {


        return null;
    }

}
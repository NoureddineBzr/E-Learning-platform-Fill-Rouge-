package com.bzr.elearning.mapping;


import com.bzr.elearning.Dto.StudentEnrolledCourseDto;
import com.bzr.elearning.entity.StudentsEnrolledCourse;

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
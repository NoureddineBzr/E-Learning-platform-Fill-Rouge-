package com.backEnd.mapping;

import com.backEnd.dto.CourseDto;
import com.backEnd.dto.CourseUploadRequest;
import com.backEnd.entity.AppUser;
import com.backEnd.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseMapper {


    public static CourseDto entityToDto(Course entity){



        return CourseDto.builder().id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .hours(entity.getHours())
                .price(entity.getPrice())
                .isCourseFree(entity.getIsCourseFree())
                .discount(entity.getDiscount())
                .discountStartDate(entity.getDiscountStartDate())
                .discountEndDate(entity.getDiscountEndDate())
                .author(UserMapper.entityToPublicUserDto(entity.getAuthor()))
                .coverImage(entity.getCoverImage())
                .lecturesCount(entity.getLecturesCount())
                .rating(entity.getRating())
                .enrolledStudentsCount(entity.getEnrolledStudentsCount())
                .reviewsCount(entity.getReviewsCount())
                .build();

    }


    public static Course dtoToEntity(CourseDto dto){
        Set<AppUser> enrolledStudents = new HashSet<>();

        return Course.builder().id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .hours(dto.getHours())
                .price(dto.getPrice())
                .isCourseFree(dto.getIsCourseFree())
                .discount(dto.getDiscount())
                .discountStartDate(dto.getDiscountStartDate())
                .discountEndDate(dto.getDiscountEndDate())
                .author( new AppUser(dto.getAuthor().getId()))
                .coverImage(dto.getCoverImage())
                //.studentsEnrolledCourse(enrolledStudents)
                .rating(dto.getRating())
                .enrolledStudentsCount(dto.getEnrolledStudentsCount())
                .lecturesCount(dto.getLecturesCount())
                .reviewsCount(dto.getReviewsCount())
                .build();
    }


    public static CourseDto uploadRequestToDto(CourseUploadRequest courseUploadRequest, String coverImage) throws IOException {


        return CourseDto.builder().title(courseUploadRequest.getTitle())
                .description(courseUploadRequest.getDescription())
                .hours(courseUploadRequest.getHours())
                .price(courseUploadRequest.getPrice())
                .isCourseFree(courseUploadRequest.getIsCourseFree())
                .discount(courseUploadRequest.getDiscount())
                .discountStartDate(courseUploadRequest.getDiscountStartDate())
                .discountEndDate(courseUploadRequest.getDiscountEndDate())
                //.author(UserMapper.entityToPublicUserDto(appUser.get()))
                .coverImage(coverImage)
                .rating(0)
                .reviewsCount(0)
                .lecturesCount(0)
                .enrolledStudentsCount(0)
                .build();

    }

}

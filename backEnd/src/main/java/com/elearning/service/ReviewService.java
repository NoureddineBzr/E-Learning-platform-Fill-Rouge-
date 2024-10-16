package com.elearning.service;

import com.elearning.Dto.ReviewDto;
import com.elearning.enums.ReviewType;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAllByCourseId(Long id);
    List<ReviewDto> findAllByLectureId(Long id);

    ReviewDto save(ReviewDto reviewDto, ReviewType reviewType);

}

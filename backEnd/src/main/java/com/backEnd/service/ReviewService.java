package com.backEnd.service;

import com.backEnd.dto.ReviewDto;
import com.backEnd.enums.ReviewType;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAllByCourseId(Long id);
    List<ReviewDto> findAllByLectureId(Long id);

    ReviewDto save(ReviewDto reviewDto, ReviewType reviewType);

}

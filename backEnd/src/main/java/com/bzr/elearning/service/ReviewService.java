package com.bzr.elearning.service;

import com.bzr.elearning.Dto.ReviewDto;
import com.bzr.elearning.enums.ReviewType;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAllByCourseId(Long id);
    List<ReviewDto> findAllByLectureId(Long id);

    ReviewDto save(ReviewDto reviewDto, ReviewType reviewType);

}

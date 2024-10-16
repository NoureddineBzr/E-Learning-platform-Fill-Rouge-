package com.elearning.serviceImpl;

import com.elearning.Dto.ReviewDto;
import com.elearning.entity.Course;
import com.elearning.entity.Lecture;
import com.elearning.entity.Review;
import com.elearning.enums.ReviewType;
import com.elearning.exception.RecordNotFoundException;
import com.elearning.mapping.ReviewMapper;
import com.elearning.mapping.UserMapper;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.LectureRepository;
import com.elearning.repository.ReviewRepository;
import com.elearning.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserServiceImpl userService;
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;


    @Override
    public List<ReviewDto> findAllByCourseId(Long id) {

        return reviewRepository.findByCourseId(id).stream()
                .map(ReviewMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findAllByLectureId(Long id) {

        return reviewRepository.findByLectureId(id).stream()
                .map(ReviewMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReviewDto save(ReviewDto reviewDto, ReviewType reviewType) {


        reviewDto.setAuthor(UserMapper.entityToPublicUserDto(userService.getCurrentAuthUser()));

        Review courseReview = reviewRepository.save(ReviewMapper.dtoToEntity(reviewDto));

        if(reviewType == ReviewType.COURSE){
            updateReview(reviewDto.getCourse().getId(), reviewType);
        }else if (reviewType == ReviewType.LECTURE){
            updateReview(reviewDto.getLecture().getId(), reviewType);
        }

        ReviewDto dto = ReviewMapper.entityToDto(courseReview);
        dto.setAuthor(UserMapper.entityToPublicUserDto(userService.getCurrentAuthUser()));

        return dto;
    }

    public void updateReview(Long id, ReviewType reviewType){
        if(reviewType == ReviewType.COURSE){
            Course currentCourse = courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Course not found"));
            currentCourse.setReviewsCount(currentCourse.getReviewsCount() + 1);
            courseRepository.save(currentCourse);

            double sum = 0.0;
            int count = 0;
            for (Review review : currentCourse.getReviews()) {
                sum += review.getRatingValue();
                count++;
            }
            if (count > 0) {
                currentCourse.setRating(sum / count);
            } else {
                currentCourse.setRating(0); // Set to null if no reviews
            }
            courseRepository.save(currentCourse); // Update course entity
        }else{
            Lecture currentLecture = lectureRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Lecture not found"));
            currentLecture.setReviewsCount(currentLecture.getReviewsCount() + 1);
            lectureRepository.save(currentLecture);

            double sum = 0.0;
            int count = 0;
            for (Review review : currentLecture.getReviews()) {
                sum += review.getRatingValue();
                count++;
            }
            if (count > 0) {
                currentLecture.setRating(sum / count);
            } else {
                currentLecture.setRating(0); // Set to null if no reviews
            }
            lectureRepository.save(currentLecture); // Update course entity
        }

    }



}

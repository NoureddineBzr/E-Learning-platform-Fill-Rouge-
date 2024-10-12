package com.elearning.serviceImpl;

import com.elearning.Dto.ReviewDto;
import com.elearning.entity.AppUser;
import com.elearning.entity.Course;
import com.elearning.entity.Lecture;
import com.elearning.entity.Review;
import com.elearning.enums.ReviewType;
import com.elearning.exception.RecordNotFoundException;
import com.elearning.mapping.ReviewMapper;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.LectureRepository;
import com.elearning.repository.ReviewRepository;
import com.elearning.serviceImpl.ReviewServiceImpl;
import com.elearning.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private ReviewDto reviewDto;
    private Review review;
    private Course course;
    private Lecture lecture;

    @BeforeEach
    public void setup() {
        reviewDto = ReviewDto.builder()
                .id(1L)
                .contentText("Great Course!")
                .ratingValue(4.5)
                .build();

        review = Review.builder()
                .id(1L)
                .contentText("Great Course!")
                .ratingValue(4.5)
                .build();

        course = new Course();
        course.setId(1L);
        course.setRating(4.0);
        course.setReviewsCount(2);

        lecture = new Lecture();
        lecture.setId(1L);
        lecture.setRating(3.5);
        lecture.setReviewsCount(1);
    }

    @Test
    public void testFindAllByCourseId() {
        when(reviewRepository.findByCourseId(1L)).thenReturn(Arrays.asList(review));

        List<ReviewDto> result = reviewService.findAllByCourseId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reviewRepository, times(1)).findByCourseId(1L);
    }

    @Test
    public void testFindAllByLectureId() {
        when(reviewRepository.findByLectureId(1L)).thenReturn(Arrays.asList(review));

        List<ReviewDto> result = reviewService.findAllByLectureId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reviewRepository, times(1)).findByLectureId(1L);
    }

    @Test
    public void testSaveCourseReview() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        when(userService.getCurrentAuthUser()).thenReturn(new AppUser(1L));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        ReviewDto savedReview = reviewService.save(reviewDto, ReviewType.COURSE);

        assertNotNull(savedReview);
        verify(reviewRepository, times(1)).save(any(Review.class));
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void testSaveLectureReview() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        when(userService.getCurrentAuthUser()).thenReturn(new AppUser(1L));
        when(lectureRepository.findById(1L)).thenReturn(Optional.of(lecture));

        ReviewDto savedReview = reviewService.save(reviewDto, ReviewType.LECTURE);

        assertNotNull(savedReview);
        verify(reviewRepository, times(1)).save(any(Review.class));
        verify(lectureRepository, times(1)).save(any(Lecture.class));
    }

    @Test
    public void testUpdateReviewForCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        reviewService.updateReview(1L, ReviewType.COURSE);

        verify(courseRepository, times(1)).save(course);
        assertEquals(3, course.getReviewsCount());
    }

    @Test
    public void testUpdateReviewForLecture() {
        when(lectureRepository.findById(1L)).thenReturn(Optional.of(lecture));

        reviewService.updateReview(1L, ReviewType.LECTURE);

        verify(lectureRepository, times(1)).save(lecture);
        assertEquals(2, lecture.getReviewsCount());
    }

    @Test
    public void testUpdateReviewForNonExistentCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> {
            reviewService.updateReview(1L, ReviewType.COURSE);
        });
    }

    @Test
    public void testUpdateReviewForNonExistentLecture() {
        when(lectureRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> {
            reviewService.updateReview(1L, ReviewType.LECTURE);
        });
    }

}

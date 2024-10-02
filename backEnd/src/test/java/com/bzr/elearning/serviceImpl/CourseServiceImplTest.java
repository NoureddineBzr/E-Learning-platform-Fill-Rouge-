package com.bzr.elearning.serviceImpl;

import com.bzr.elearning.Dto.CourseDto;
import com.bzr.elearning.Dto.CourseUploadRequest;
import com.bzr.elearning.Dto.StudentEnrolledCourseDto;
import com.bzr.elearning.entity.Course;
import com.bzr.elearning.entity.StudentsEnrolledCourse;
import com.bzr.elearning.entity.AppUser;
import com.bzr.elearning.exception.RecordNotFoundException;
import com.bzr.elearning.mapping.CourseMapper;
import com.bzr.elearning.repository.CourseRepository;
import com.bzr.elearning.repository.StudentsEnrolledCourcesRepository;

import com.bzr.elearning.utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    private static final Long COURSE_ID = 1L;
    private static final Long AUTHOR_ID = 2L;
    private static final String COURSE_TITLE = "Test Course";
    private static final String COVER_IMAGE_NAME = "test-image.jpg";

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentsEnrolledCourcesRepository studentsEnrolledCourcesRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        // Setup necessary configurations or pre-conditions here if needed
    }

    @Test
    void save_shouldSaveCourseSuccessfully() throws IOException {
        // given
        CourseUploadRequest uploadRequest = new CourseUploadRequest();
        uploadRequest.setTitle(COURSE_TITLE);

        CourseDto courseDto = new CourseDto();
        courseDto.setTitle(COURSE_TITLE);
        Course courseEntity = new Course();
        courseEntity.setId(COURSE_ID);

        when(multipartFile.getOriginalFilename()).thenReturn(COVER_IMAGE_NAME);
        when(FileUtils.SaveFileAndGetName(multipartFile, COURSE_TITLE)).thenReturn(COVER_IMAGE_NAME);
        when(courseRepository.save(any(Course.class))).thenReturn(courseEntity);
        when(CourseMapper.uploadRequestToDto(any(), any())).thenReturn(courseDto);
        when(CourseMapper.dtoToEntity(any())).thenReturn(courseEntity);
        when(CourseMapper.entityToDto(any())).thenReturn(courseDto);

        // when
        CourseDto result = courseService.save(uploadRequest, multipartFile);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(COURSE_TITLE);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void findAll_shouldReturnListOfCourses() {
        // given
        Course course = new Course();
        course.setId(COURSE_ID);
        List<Course> courses = Collections.singletonList(course);

        CourseDto courseDto = new CourseDto();
        courseDto.setId(COURSE_ID);

        when(courseRepository.findAll()).thenReturn(courses);
        when(CourseMapper.entityToDto(any())).thenReturn(courseDto);

        // when
        List<CourseDto> result = courseService.findAll();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(COURSE_ID);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnCourseDto_whenCourseExists() {
        // given
        Course course = new Course();
        course.setId(COURSE_ID);
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        CourseDto courseDto = new CourseDto();
        courseDto.setId(COURSE_ID);
        when(CourseMapper.entityToDto(any())).thenReturn(courseDto);

        // when
        CourseDto result = courseService.findById(COURSE_ID);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(COURSE_ID);
        verify(courseRepository, times(1)).findById(COURSE_ID);
    }

    @Test
    void findById_shouldThrowRecordNotFoundException_whenCourseDoesNotExist() {
        // given
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.empty());

        // when / then
        assertThrows(RecordNotFoundException.class, () -> courseService.findById(COURSE_ID));
        verify(courseRepository, times(1)).findById(COURSE_ID);
    }

    @Test
    void enRollToCourse_shouldEnrollStudentInCourse() {
        // given
        AppUser student = new AppUser();
        student.setId(AUTHOR_ID);

        Course course = new Course();
        course.setId(COURSE_ID);

        when(userService.getCurrentAuthUser()).thenReturn(student);
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        StudentsEnrolledCourse enrolledCourse = StudentsEnrolledCourse.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .progress(0)
                .build();

        // when
        courseService.enRollToCourse(COURSE_ID);

        // then
        verify(studentsEnrolledCourcesRepository, times(1)).save(any(StudentsEnrolledCourse.class));
        verify(courseRepository, times(1)).findById(COURSE_ID);
    }

    @Test
    void incrementCourseStudentEnrolledCountByOne_shouldIncrementCount() {
        // given
        Course course = new Course();
        course.setId(COURSE_ID);
        course.setEnrolledStudentsCount(5);

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        // when
        courseService.incrementCourseStudentEnrolledCountByOne(COURSE_ID);

        // then
        assertThat(course.getEnrolledStudentsCount()).isEqualTo(6);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void incrementCourseLectureNumbersByOne_shouldIncrementLectureCount() {
        // given
        Course course = new Course();
        course.setId(COURSE_ID);
        course.setLecturesCount(3);

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        // when
        courseService.incrementCourseLectureNumbersByOne(COURSE_ID);

        // then
        assertThat(course.getLecturesCount()).isEqualTo(4);
        verify(courseRepository, times(1)).save(course);
    }
}

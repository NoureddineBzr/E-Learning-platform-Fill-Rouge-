//package com.elearning.serviceImpl;
//
//import com.elearning.Dto.CourseDto;
//import com.elearning.Dto.CourseUploadRequest;
//import com.elearning.entity.Course;
//import com.elearning.exception.RecordNotFoundException;
//import com.elearning.mapping.CourseMapper;
//import com.elearning.repository.CourseRepository;
//import com.elearning.utils.FileUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class CourseServiceImplTest {
//
//    @Mock
//    private CourseRepository courseRepository;
//
//    @Mock
//    private MultipartFile coverImageFile;
//
//    @InjectMocks
//    private CourseServiceImpl courseService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSaveCourse() throws IOException {
//        // Arrange
//        CourseUploadRequest courseUploadRequest = CourseUploadRequest.builder()
//                .title("Java Course")
//                .description("Learn Java")
//                .hours(10.5)
//                .isCourseFree(false)
//                .price(new BigDecimal("49.99"))
//                .build();
//
//        String coverImageName = "java-course-cover.jpg";
//        when(FileUtils.SaveFileAndGetName(any(MultipartFile.class), anyString())).thenReturn(coverImageName);
//        when(courseRepository.save(any(Course.class))).thenReturn(new Course());
//
//        // Act
//        CourseDto savedCourse = courseService.save(courseUploadRequest, coverImageFile);
//
//        // Assert
//        assertNotNull(savedCourse);
//        verify(courseRepository, times(1)).save(any(Course.class));
//    }
//
//    @Test
//    void testFindById_CourseExists() {
//        // Arrange
//        Course course = new Course(1L);
//        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
//
//        // Act
//        CourseDto courseDto = courseService.findById(1L);
//
//        // Assert
//        assertNotNull(courseDto);
//        verify(courseRepository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    void testFindById_CourseNotFound() {
//        // Arrange
//        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(RecordNotFoundException.class, () -> courseService.findById(1L));
//        verify(courseRepository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    void testFindAll() {
//        // Arrange
//        List<Course> courses = new ArrayList<>();
//        courses.add(new Course(1L));
//        courses.add(new Course(2L));
//        when(courseRepository.findAll()).thenReturn(courses);
//
//        // Act
//        List<CourseDto> courseDtos = courseService.findAll();
//
//        // Assert
//        assertEquals(2, courseDtos.size());
//        verify(courseRepository, times(1)).findAll();
//    }
//
//}

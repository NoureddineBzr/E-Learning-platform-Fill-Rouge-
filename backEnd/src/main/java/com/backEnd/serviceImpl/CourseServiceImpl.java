package com.backEnd.serviceImpl;

import com.backEnd.dto.CourseDto;
import com.backEnd.dto.CourseUploadRequest;
import com.backEnd.dto.PublicUserDto;
import com.backEnd.dto.StudentEnrolledCourseDto;
import com.backEnd.entity.AppUser;
import com.backEnd.entity.Course;
import com.backEnd.entity.StudentsEnrolledCourse;
import com.backEnd.exception.RecordNotFoundException;
import com.backEnd.mapping.CourseMapper;
import com.backEnd.mapping.ReviewMapper;
import com.backEnd.mapping.StudentCourseMapper;
import com.backEnd.mapping.UserMapper;
import com.backEnd.repository.CourseRepository;
import com.backEnd.repository.LectureRepository;
import com.backEnd.repository.StudentsEnrolledCourcesRepository;
import com.backEnd.service.CourseService;
import com.backEnd.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final StudentsEnrolledCourcesRepository studentsEnrolledCourcesRepository;
    private final UserServiceImpl userService;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto save(CourseUploadRequest courseUploadRequest, MultipartFile coverImageFile) throws IOException {

        String courseCoverImageName = FileUtils.SaveFileAndGetName(coverImageFile, courseUploadRequest.getTitle());
        CourseDto courseDto = CourseMapper.uploadRequestToDto(courseUploadRequest, courseCoverImageName);
        courseDto.setLecturesCount(0);
        courseDto.setEnrolledStudentsCount(0);
        courseDto.setReviewsCount(0);
        Course newCourse = courseRepository.save(CourseMapper.dtoToEntity(courseDto));

        return CourseMapper.entityToDto(newCourse);
    }

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> findAllWithAuthed() {


        Set<Long> myCoursesIds = new HashSet<>();

        Set<StudentEnrolledCourseDto> myEnrolledCourses = findMyCourses();

        for(StudentEnrolledCourseDto row : myEnrolledCourses){
            myCoursesIds.add(row.getCourse().getId());
        }

        List<CourseDto> allCourses = courseRepository.findAll().stream()
                .map(CourseMapper::entityToDto)
                .collect(Collectors.toList());

        for(CourseDto courseDto : allCourses){
            if (myCoursesIds.contains(courseDto.getId())) {
                courseDto.setIsEnrolled(true);
            }
        }

        return allCourses;


    }

    @Override
    public CourseDto findById(Long id) {

        Set<PublicUserDto> enrolledStudents = new HashSet<>();

        Course course = courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("course not found"));
        course.getStudentsEnrolledCourse().forEach( e-> enrolledStudents.add( UserMapper.entityToPublicUserDto(e.getStudent())));

        CourseDto courseDto = CourseMapper.entityToDto(course);
        courseDto.setEnrolledStudents(enrolledStudents);
        courseDto.setEnrolledStudentsCount(enrolledStudents.size());
        courseDto.setReviews(
                course.getReviews().stream()
                        .map(ReviewMapper::entityToDto)
                        .collect(Collectors.toList()));

        return  courseDto;
    }

    @Override
    public List<CourseDto> findByAuthorId(Long authorId) {

        return courseRepository.findAllByAuthorId(authorId).stream()
                .map(CourseMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Object enRollToCourse(Long courseId){
        AppUser student =  userService.getCurrentAuthUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));


        StudentsEnrolledCourse data = StudentsEnrolledCourse.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .progress(0)
                .build();

        incrementCourseStudentEnrolledCountByOne(course.getId());
        studentsEnrolledCourcesRepository.save(data);

        return course;
    }

    @Override
    public void updateCoverImage(MultipartFile file) throws IOException {
    }

    @Override
    public Set<CourseDto> findMyEnrolledCourses() {
        return null;
    }


    public void incrementCourseLectureNumbersByOne(Long courseId){
        Course currentCourse = courseRepository.findById(courseId).orElseThrow(() -> new RecordNotFoundException("Course not found"));
        currentCourse.setLecturesCount(currentCourse.getLecturesCount()+1);
        courseRepository.save(currentCourse);
    }

    public void incrementCourseStudentEnrolledCountByOne(Long courseId){
        Course currentCourse = courseRepository.findById(courseId).orElseThrow(() -> new RecordNotFoundException("Course not found"));
        currentCourse.setEnrolledStudentsCount(currentCourse.getEnrolledStudentsCount() +1);
        courseRepository.save(currentCourse);
    }


    public Set<StudentEnrolledCourseDto> findMyCourses(){

        return studentsEnrolledCourcesRepository.findByStudentId(userService.getCurrentAuthUser().getId()).stream()
                .map(StudentCourseMapper::entityToDto)
                .collect(Collectors.toSet());
    }


}


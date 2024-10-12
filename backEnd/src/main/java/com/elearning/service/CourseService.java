package com.elearning.service;

import com.elearning.Dto.CourseDto;
import com.elearning.Dto.CourseUploadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CourseService {

    List<CourseDto> findAll();
    Set<CourseDto> findMyEnrolledCourses();
    List<CourseDto> findByAuthorId(Long authorId);
    CourseDto findById(Long id);
    CourseDto save(CourseUploadRequest courseUploadRequest, MultipartFile coverImageFile) throws IOException;
    void updateCoverImage(MultipartFile file) throws IOException;



}

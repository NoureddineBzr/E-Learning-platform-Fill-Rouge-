package com.bzr.elearning.service;

import com.bzr.elearning.Dto.CourseUploadRequest;
import com.bzr.elearning.Dto.LectureDto;
import com.bzr.elearning.Dto.LectureUploadRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface LectureService {

    List<LectureDto> findByAllCourseId(Long courseId);
    LectureDto findById(Long id);
    LectureDto save(LectureUploadRequest courseUploadRequest, MultipartFile coverImageFile, MultipartFile lectureVideo) throws IOException;
    void updateCoverImage(MultipartFile file) throws IOException;

    Mono<Resource> retrieveContent(String fileName);



}

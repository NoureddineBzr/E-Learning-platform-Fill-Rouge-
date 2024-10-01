package com.backEnd.service;

import com.backEnd.dto.LectureDto;
import com.backEnd.dto.LectureUploadRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface LectureService {

    List<LectureDto> findByAllCourseId(Long courseId);
    LectureDto findById(Long id);
    LectureDto save(LectureUploadRequest courseUploadRequest, MultipartFile coverImageFile, MultipartFile lectureVideo) throws IOException;
    void updateCoverImage(MultipartFile file) throws IOException;

    Mono<Resource> retrieveContent(String fileName);



}

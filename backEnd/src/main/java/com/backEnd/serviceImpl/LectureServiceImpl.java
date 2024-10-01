package com.backEnd.serviceImpl;

import com.backEnd.dto.LectureDto;
import com.backEnd.dto.LectureUploadRequest;
import com.backEnd.dto.MyVideo;
import com.backEnd.entity.Course;
import com.backEnd.entity.Lecture;
import com.backEnd.exception.RecordNotFoundException;
import com.backEnd.mapping.LectureMapper;
import com.backEnd.repository.LectureRepository;
import com.backEnd.service.LectureService;
import com.backEnd.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final CourseServiceImpl courseService;
    @Value("${uploads.videos.path}")
    private String VIDEO_PATH;


    @Transactional
    @Override
    public LectureDto save(LectureUploadRequest lectureUploadRequest, MultipartFile coverImageFile, MultipartFile lectureVideo) throws IOException {
        MyVideo video = FileUtils.SaveVideo(lectureVideo, lectureUploadRequest.getTitle());

        Lecture lecture = Lecture.builder()
                .title(lectureUploadRequest.getTitle())
                .description(lectureUploadRequest.getDescription())
                .course(new Course(lectureUploadRequest.getCourse().getId()))
                .coverImage(FileUtils.SaveFileAndGetName(coverImageFile, lectureUploadRequest.getTitle()))
                .video(video.getFileName())
                .length((int) video.getDuration())
                .lectureOrder(lectureRepository.findMaxLectureOrder() +1)
                .build();

        Lecture newLecture = lectureRepository.save(lecture);
        courseService.incrementCourseLectureNumbersByOne(lectureUploadRequest.getCourse().getId());

        return LectureMapper.entityToDto(newLecture);
    }

    @Override
    public List<LectureDto> findByAllCourseId(Long courseId) {
        return lectureRepository.findAllByCourseIdOrderByLectureOrderAsc(courseId).stream()
                .map(LectureMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LectureDto findById(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("lecture not found"));
        return LectureMapper.entityToDto(lecture);
    }


    @Override
    public void updateCoverImage(MultipartFile file) throws IOException {
            /*String fileName = FileUtils.SaveFileAndGetName(file);

            currentUser.setProfileImage(fileName);
            userRepository.save(currentUser);*/
    }

    @Override
    public Mono<Resource> retrieveContent(String fileName){
        return Mono.fromSupplier(()-> new FileSystemResource(VIDEO_PATH+"/"+fileName));
    }


}

package com.backEnd.mapping;

import com.backEnd.dto.LectureDto;
import com.backEnd.entity.Course;
import com.backEnd.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureMapper {

    public static LectureDto entityToDto(Lecture entity){


        return LectureDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .length(entity.getLength())
                .course(CourseMapper.entityToDto(entity.getCourse()))
                .coverImage(entity.getCoverImage())
                .video(entity.getVideo())
                .lectureOrder(entity.getLectureOrder())
                .build();
    }


    public static Lecture dtoToEntity(LectureDto dto){

        return Lecture.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .length(dto.getLength())
                .course( new Course(dto.getCourse().getId()) )
                .coverImage(dto.getCoverImage())
                .video(dto.getVideo())
                .lectureOrder(dto.getLectureOrder())
                .build();
    }

}

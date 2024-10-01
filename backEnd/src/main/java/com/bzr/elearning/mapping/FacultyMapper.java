package com.bzr.elearning.mapping;

import com.bzr.elearning.Dto.FacultyDto;
import com.bzr.elearning.entity.Faculty;

public class FacultyMapper {

    public static FacultyDto entityToDto(Faculty entity){

        return FacultyDto.builder().id(entity.getId())
                .sepicailization(AcademicSpecializationMapper.entityToDto(entity.getAcademicSpecialization()))
                .university(UniversityMapper.entityToDto(entity.getUniversity()))
                .build();
    }


    public static Faculty dtoToEntity(FacultyDto dto){

        return Faculty.builder().id(dto.getId())
                .academicSpecialization(AcademicSpecializationMapper.dtoToEntity(dto.getSepicailization()))
                .university(UniversityMapper.dtoToEntity(dto.getUniversity()))
                .build();
    }
}

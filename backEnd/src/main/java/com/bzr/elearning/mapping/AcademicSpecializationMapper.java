package com.bzr.elearning.mapping;

import com.bzr.elearning.Dto.AcademicSpecializationDto;
import com.bzr.elearning.entity.AcademicSpecialization;

public class AcademicSpecializationMapper {

    public static AcademicSpecializationDto entityToDto(AcademicSpecialization entity){
        return AcademicSpecializationDto.builder().id(entity.getId())
                .name(entity.getName())
                .build();
    }


    public static AcademicSpecialization dtoToEntity(AcademicSpecializationDto dto){
        return AcademicSpecialization.builder().id(dto.getId())
                .name(dto.getName())
                .build();
    }
}

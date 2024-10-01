package com.backEnd.mapping;

import com.backEnd.dto.AcademicSpecializationDto;
import com.backEnd.entity.AcademicSpecialization;

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

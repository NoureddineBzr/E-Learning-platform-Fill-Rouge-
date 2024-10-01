package com.bzr.elearning.mapping;

import com.bzr.elearning.Dto.RoleDto;
import com.bzr.elearning.Dto.UniversityDto;
import com.bzr.elearning.entity.Role;
import com.bzr.elearning.entity.University;
import com.bzr.elearning.enums.Country;

public class UniversityMapper {
    public static UniversityDto entityToDto(University entity){

        if(entity.getCountry() != null){
            return UniversityDto.builder().id(entity.getId())
                    .name(entity.getName())
                    .country(entity.getCountry())
                    .build();
        }
        return UniversityDto.builder().id(entity.getId())
                .name(entity.getName())
                .build();
    }


    public static University dtoToEntity(UniversityDto dto){

        if(dto.getCountry() != null){
            return University.builder().id(dto.getId())
                    .name(dto.getName())
                    .country(dto.getCountry())
                .build();
        }
        return University.builder().id(dto.getId())
                .name(dto.getName())
                .build();

    }
}

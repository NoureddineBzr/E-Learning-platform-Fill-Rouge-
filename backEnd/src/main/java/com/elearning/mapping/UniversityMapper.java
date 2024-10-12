package com.elearning.mapping;

import com.elearning.Dto.RoleDto;
import com.elearning.Dto.UniversityDto;
import com.elearning.entity.Role;
import com.elearning.entity.University;
import com.elearning.enums.Country;

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

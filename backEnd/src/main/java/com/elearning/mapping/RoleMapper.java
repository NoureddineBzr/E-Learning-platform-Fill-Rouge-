package com.elearning.mapping;

import com.elearning.Dto.RoleDto;
import com.elearning.entity.Role;

public class RoleMapper {

    public static RoleDto entityToDto(Role entity){
        return RoleDto.builder().id(entity.getId())
                .name(entity.getName())
                .build();
    }


    public static Role dtoToEntity(RoleDto dto){
        return Role.builder().id(dto.getId()).name(dto.getName()).build();
    }
}

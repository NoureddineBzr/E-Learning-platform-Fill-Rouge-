package com.bzr.elearning.mapping;

import com.bzr.elearning.Dto.RoleDto;
import com.bzr.elearning.entity.Role;

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

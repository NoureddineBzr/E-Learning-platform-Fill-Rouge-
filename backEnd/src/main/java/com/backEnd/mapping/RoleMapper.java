package com.backEnd.mapping;

import com.backEnd.dto.RoleDto;
import com.backEnd.entity.Role;

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

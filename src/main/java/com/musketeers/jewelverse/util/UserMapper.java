// src/main/java/com/musketeers/jewelverse/mapper/UserMapper.java
// A new mapper for converting between User entities and DTOs.

package com.musketeers.jewelverse.mapper;

import com.musketeers.jewelverse.dto.user.UserCreateDto;
import com.musketeers.jewelverse.dto.user.UserDto;
import com.musketeers.jewelverse.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.name", target = "role")
    UserDto toUserDto(User user);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "enabled", constant = "true") // New users are enabled by default
    })
    User toUserEntity(UserCreateDto createDto);
}
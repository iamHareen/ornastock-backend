// src/main/java/com/musketeers/jewelverse/mapper/AuthMapper.java
// A new mapper specifically for authentication-related conversions.

package com.musketeers.jewelverse.mapper;

import com.musketeers.jewelverse.dto.auth.RegisterRequest;
import com.musketeers.jewelverse.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "enabled", constant = "true"), // Customers are enabled by default
            @Mapping(target = "role", ignore = true) // Role will be set in the service
    })
    User registerRequestToUser(RegisterRequest registerRequest);
}
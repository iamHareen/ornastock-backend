package com.musketeers.jewelverse.util.mapper;

import com.musketeers.jewelverse.dto.user.*;
import com.musketeers.jewelverse.model.entity.user.*;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User entities and DTOs
 */
@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getUserRole())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User toUserEntity(UserCreateDto dto) {
        if (dto == null) {
            return null;
        }

        User user;
        // Create the appropriate subclass based on the role
        switch (dto.getRole()) {
            case ADMIN:
                user = new Admin();
                break;
            case MANAGER:
                user = new Manager();
                break;
            case SALES_ASSISTANT:
                user = new SalesAssistant();
                break;
            case CUSTOMER:
            default:
                user = new Customer();
                break;
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUserRole(dto.getRole()); // Set the enum directly
        user.setEnabled(true); // New users are enabled by default

        return user;
    }

    public Customer toCustomerEntity(UserCreateDto dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setUserRole(dto.getRole()); // Set the enum directly
        customer.setEnabled(true); // New users are enabled by default

        return customer;
    }
}
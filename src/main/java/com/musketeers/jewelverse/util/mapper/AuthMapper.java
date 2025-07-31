package com.musketeers.jewelverse.util.mapper;

import com.musketeers.jewelverse.dto.auth.*;
import com.musketeers.jewelverse.model.entity.user.*;
import com.musketeers.jewelverse.model.enums.UserRole;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between authentication-related entities and DTOs
 */
@Component
public class AuthMapper {

    public LoginResponse toLoginResponse(User user, String token) {
        if (user == null) {
            return null;
        }

        UserRole userRole = null;
        if (user.getRole() != null) {
            try {
                userRole = UserRole.valueOf(user.getRole().getName());
            } catch (IllegalArgumentException e) {
                // Handle case where role name doesn't match enum
                userRole = null;
            }
        }

        return LoginResponse.builder()
                .token(token)
                .role(userRole)
                .build();
    }

    public Customer registerRequestToUser(RegisterRequest dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setEnabled(true); // New customers are enabled by default

        return customer;
    }
}
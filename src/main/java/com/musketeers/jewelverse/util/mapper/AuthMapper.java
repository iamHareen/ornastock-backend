package com.musketeers.jewelverse.util.mapper;

import com.musketeers.jewelverse.dto.auth.*;
import com.musketeers.jewelverse.model.entity.user.*;
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

        return LoginResponse.builder()
                .token(token)
                .role(user.getUserRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
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
        customer.setUserRole(com.musketeers.jewelverse.model.enums.UserRole.CUSTOMER); // Set role explicitly
        customer.setEnabled(true); // New customers are enabled by default

        return customer;
    }
}
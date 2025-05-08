package com.musketeers.jewelverse.service;
import com.musketeers.jewelverse.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}

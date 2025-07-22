package com.musketeers.jewelverse.service;

import com.musketeers.jewelverse.entity.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}

// src/main/java/com/musketeers/jewelverse/security/UserDetailsServiceImpl.java
// This service loads user-specific data for Spring Security.

package com.musketeers.jewelverse.security;

import com.musketeers.jewelverse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserPrinciple::build)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
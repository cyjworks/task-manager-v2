package com.cyj.taskmanager.service;

import com.cyj.taskmanager.domain.User;
import com.cyj.taskmanager.dto.*;
import com.cyj.taskmanager.repository.UserRepository;
import com.cyj.taskmanager.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Long signup(UserSignupDTO dto) {
        // 1. Check for duplicates
        if(duplicateCheckUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if(duplicateCheckEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // 2. Encrypt password
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 3. Create entity
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(encodedPassword)
                .fullName(dto.getFullName())
                .build();

        // 4. Save to DB
        userRepository.save(user);

        return user.getId();
    }

    public boolean duplicateCheckUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean duplicateCheckEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public LoginResponseDTO login(UserLoginDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtProvider.generateToken(user.getUsername());
        return new LoginResponseDTO(token, user.getUsername());
    }

    public UserResponseDTO getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return UserResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public void updateUserProfile(String username, UserUpdateDTO dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.updateProfile(dto);
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }
}

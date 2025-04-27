package com.cyj.taskmanager.service;

import com.cyj.taskmanager.common.CustomException;
import com.cyj.taskmanager.common.ErrorCode;
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
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if(duplicateCheckEmail(dto.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
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
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_CREDENTIALS));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        String token = jwtProvider.generateToken(user.getUsername());
        return new LoginResponseDTO(token, user.getUsername());
    }

    public UserResponseDTO getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public UserResponseDTO updateUserProfile(String username, UserUpdateDTO dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(dto);
        userRepository.save(user);

        return UserResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }
}

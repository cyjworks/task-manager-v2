package com.cyj.taskmanager.controller;

import com.cyj.taskmanager.common.ApiResponse;
import com.cyj.taskmanager.dto.*;
import com.cyj.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Long>> signup(@RequestBody UserSignupDTO dto) {
        Long userId = userService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody UserLoginDTO dto) {
        LoginResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getMyProfile(@RequestParam String username) {
        UserResponseDTO user = userService.getUserProfile(username);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateMyProfile(@RequestParam String username, @RequestBody UserUpdateDTO dto) {
        UserResponseDTO updatedUser = userService.updateUserProfile(username, dto);
        return ResponseEntity.ok(ApiResponse.success(updatedUser));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyAccount(@RequestParam String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}

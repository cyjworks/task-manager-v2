package com.cyj.taskmanager.controller;

import com.cyj.taskmanager.dto.*;
import com.cyj.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupDTO dto) {
        Long userId = userService.signup(dto);
        return ResponseEntity.ok("User registered with ID: " + userId);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO dto) {
        LoginResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile(@RequestParam String username) {
        UserResponseDTO user = userService.getUserProfile(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateMyProfile(
            @RequestParam String username,
            @RequestBody UserUpdateDTO dto) {
        userService.updateUserProfile(username, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyAccount(@RequestParam String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}

package com.cyj.taskmanager.controller;

import com.cyj.taskmanager.dto.UserSignupDTO;
import com.cyj.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

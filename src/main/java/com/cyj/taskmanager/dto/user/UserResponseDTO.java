package com.cyj.taskmanager.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String username;
    private String email;
    private String fullName;
    private String bio;
    private String location;
    private String profileImageUrl;
}

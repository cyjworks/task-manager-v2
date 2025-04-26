package com.cyj.taskmanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupDTO {
    private String username;
    private String email;
    private String password;
    private String fullName;
}

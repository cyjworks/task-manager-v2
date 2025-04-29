package com.cyj.taskmanager.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("USER-001", "User not found"),
    USERNAME_ALREADY_EXISTS("USER-002", "Username already exists"),
    EMAIL_ALREADY_EXISTS("USER-003", "Email already exists"),
    INVALID_CREDENTIALS("USER-004", "Invalid username or password"),

    TASK_NOT_FOUND("TASK-001", "Task not found"),
    USER_ID_NULL("TASK-002", "User ID must not be null"),

    UNAUTHORIZED_ACCESS("AUTH-001", "Unauthorized access"),
    INVALID_TOKEN("AUTH-002", "Invalid token"),
    FORBIDDEN("AUTH-003", "Forbidden access");

    private final String code;
    private final String message;
}

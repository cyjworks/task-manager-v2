package com.cyj.taskmanager.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    public final String code;
    public final String message;
}

package com.cyj.taskmanager.dto;

import com.cyj.taskmanager.domain.enums.TaskPriority;
import com.cyj.taskmanager.domain.enums.TaskProgress;
import com.cyj.taskmanager.domain.enums.TaskType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {
    @NotNull(message = "Type is required")
    private TaskType type;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskPriority priority;
    private TaskProgress progress;
    private Integer reminderDaysBefore;
}

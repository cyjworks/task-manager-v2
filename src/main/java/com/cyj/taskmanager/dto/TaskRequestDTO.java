package com.cyj.taskmanager.dto;

import com.cyj.taskmanager.domain.enums.TaskPriority;
import com.cyj.taskmanager.domain.enums.TaskProgress;
import com.cyj.taskmanager.domain.enums.TaskType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {
    private Long userId;
    private TaskType type;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskPriority priority;
    private TaskProgress progress;
    private Integer reminderDaysBefore;
}

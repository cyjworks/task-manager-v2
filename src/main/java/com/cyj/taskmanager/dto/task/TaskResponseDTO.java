package com.cyj.taskmanager.dto.task;

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
public class TaskResponseDTO {
    private Long id;
    private TaskType type;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskPriority priority;
    private TaskProgress progress;
    private Integer reminderDaysBefore;
}

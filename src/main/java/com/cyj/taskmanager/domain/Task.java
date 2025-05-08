package com.cyj.taskmanager.domain;

import com.cyj.taskmanager.domain.enums.TaskPriority;
import com.cyj.taskmanager.domain.enums.TaskProgress;
import com.cyj.taskmanager.domain.enums.TaskType;
import com.cyj.taskmanager.dto.task.TaskRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskProgress progress;

    private Integer reminderDaysBefore;

    public void updateFromDTO(TaskRequestDTO dto) {
        this.type = dto.getType();
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.priority = dto.getPriority();
        this.progress = dto.getProgress();
        this.reminderDaysBefore = dto.getReminderDaysBefore();
    }
}

package com.cyj.taskmanager.domain;

import com.cyj.taskmanager.domain.enums.TaskPriority;
import com.cyj.taskmanager.domain.enums.TaskProgress;
import com.cyj.taskmanager.domain.enums.TaskType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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
}

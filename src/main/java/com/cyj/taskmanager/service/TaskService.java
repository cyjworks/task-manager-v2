package com.cyj.taskmanager.service;

import com.cyj.taskmanager.domain.Task;
import com.cyj.taskmanager.domain.User;
import com.cyj.taskmanager.dto.TaskRequestDTO;
import com.cyj.taskmanager.dto.TaskResponseDTO;
import com.cyj.taskmanager.repository.TaskRepository;
import com.cyj.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Long createTask(TaskRequestDTO dto) {
        if(dto.getUserId() == null) {
            throw new IllegalArgumentException("User ID is null");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Task task = Task.builder()
                .owner(user)
                .type(dto.getType())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .priority(dto.getPriority())
                .progress(dto.getProgress())
                .reminderDaysBefore(dto.getReminderDaysBefore())
                .build();

        taskRepository.save(task);
        return task.getId();
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return toDTO(task);
    }

    public void updateTask(Long id, TaskRequestDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.updateFromDTO(dto);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    private TaskResponseDTO toDTO(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .type(task.getType())
                .title(task.getTitle())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .priority(task.getPriority())
                .progress(task.getProgress())
                .reminderDaysBefore(task.getReminderDaysBefore())
                .build();
    }
}

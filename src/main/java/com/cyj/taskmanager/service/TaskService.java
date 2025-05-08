package com.cyj.taskmanager.service;

import com.cyj.taskmanager.common.CustomException;
import com.cyj.taskmanager.common.ErrorCode;
import com.cyj.taskmanager.domain.Task;
import com.cyj.taskmanager.domain.User;
import com.cyj.taskmanager.dto.task.TaskRequestDTO;
import com.cyj.taskmanager.dto.task.TaskResponseDTO;
import com.cyj.taskmanager.repository.TaskRepository;
import com.cyj.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public Long createTask(TaskRequestDTO dto) {
        User user = getCurrentUser();

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
        User user = getCurrentUser();

        return taskRepository.findByOwner(user).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        User user = getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));

        if (!task.getOwner().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        return toDTO(task);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        User user = getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
        task.updateFromDTO(dto);

        if (!task.getOwner().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        taskRepository.save(task);
        return toDTO(task);
    }

    public void deleteTask(Long id) {
        User user = getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));

        if (!task.getOwner().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        taskRepository.delete(task);
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

package com.cyj.taskmanager.repository;

import com.cyj.taskmanager.domain.Task;
import com.cyj.taskmanager.domain.enums.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwnerId(Long ownerId);
    List<Task> findByOwnerIdAndStartDateBetween(Long ownerId, LocalDate start, LocalDate end);
    List<Task> findByOwnerIdAndProgress(Long ownerId, TaskProgress progress);
    List<Task> findByOwnerIdOrderByEndDateAsc(Long ownerId);
}

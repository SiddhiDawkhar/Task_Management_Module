package com.siddhi.taskmanagement.service;

import com.siddhi.taskmanagement.dto.TaskDto;
import com.siddhi.taskmanagement.exception.ResourceNotFoundException;
import com.siddhi.taskmanagement.model.Role;
import com.siddhi.taskmanagement.model.Task;
import com.siddhi.taskmanagement.model.TaskStatus;
import com.siddhi.taskmanagement.model.User;
import com.siddhi.taskmanagement.repository.TaskRepository;
import com.siddhi.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private TaskRepository taskRepository;

    // ================= CREATE TASK =================
    public TaskDto createTask(TaskDto dto) {

        User manager = userRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

        if (manager.getRole() != Role.MANAGER) {
            throw new IllegalArgumentException("Only MANAGER can create tasks");
        }

        User employee = userRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setAssignedDate(LocalDate.now());
        task.setDueDate(dto.getDueDate());
        task.setStatus(TaskStatus.PENDING);
        task.setManager(manager);
        task.setEmployee(employee);

        Task saved = taskRepository.save(task);
        return mapToDto(saved);
    }

    // ================= COMPLETE TASK =================
    public TaskDto completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setCompletedDate(LocalDate.now());

        if (task.getCompletedDate().isAfter(task.getDueDate())) {
            task.setStatus(TaskStatus.COMPLETED_LATE);
        } else {
            task.setStatus(TaskStatus.COMPLETED_ON_TIME);
        }

        return mapToDto(taskRepository.save(task));
    }

    // ================= FETCH =================
    public List<TaskDto> getTasksByEmployee(Long employeeId) {
        return taskRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksByManager(Long managerId) {
        return taskRepository.findByManagerId(managerId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= MAPPER =================
    private TaskDto mapToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setAssignedDate(task.getAssignedDate());
        dto.setDueDate(task.getDueDate());
        dto.setCompletedDate(task.getCompletedDate());
        dto.setStatus(task.getStatus());
        dto.setManagerId(task.getManager().getId());
        dto.setEmployeeId(task.getEmployee().getId());
        return dto;
    }

    public void deleteTask(Long taskId, Long managerId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + managerId));

        // Role check
        if (manager.getRole() != Role.MANAGER) {
            throw new IllegalArgumentException("Only MANAGER can delete tasks");
        }

        // Optional safety: ensure same manager created the task
        if (!task.getManager().getId().equals(managerId)) {
            throw new IllegalArgumentException("Manager can delete only their own tasks");
        }

        // HARD DELETE (mistaken task)
        if (task.getStatus() == TaskStatus.PENDING) {
            taskRepository.delete(task);
            return;
        }

        // SOFT DELETE (completed on time)
        if (task.getStatus() == TaskStatus.COMPLETED_ON_TIME) {
            task.setDeletedAt(LocalDate.now());
            taskRepository.save(task);
            return;
        }

        // ❌ All other cases
        throw new IllegalArgumentException(
                "Task cannot be deleted in current status: " + task.getStatus()
        );
    }





}

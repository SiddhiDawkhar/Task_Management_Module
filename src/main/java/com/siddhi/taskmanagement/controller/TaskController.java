package com.siddhi.taskmanagement.controller;

import com.siddhi.taskmanagement.dto.TaskDto;
import com.siddhi.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping("/api/tasks")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/api/tasks/{taskId}/complete")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    public ResponseEntity<TaskDto> completeTask(@PathVariable Long taskId) {
        TaskDto completedTask = taskService.completeTask(taskId);
        return ResponseEntity.ok(completedTask);
    }

    @GetMapping("/api/tasks/employee/{employeeId}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    public ResponseEntity<List<TaskDto>> getTasksByEmployee(
            @PathVariable Long employeeId) {

        List<TaskDto> tasks = taskService.getTasksByEmployee(employeeId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/api/tasks/manager/{managerId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<TaskDto>> getTasksByManager(
            @PathVariable Long managerId) {

        List<TaskDto> tasks = taskService.getTasksByManager(managerId);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/api/tasks/{taskId}/manager/{managerId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            @PathVariable Long managerId) {

        taskService.deleteTask(taskId, managerId);
        return ResponseEntity.noContent().build();
    }
}

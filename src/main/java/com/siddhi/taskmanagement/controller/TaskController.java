package com.siddhi.taskmanagement.controller;

import com.siddhi.taskmanagement.dto.TaskDto;
import com.siddhi.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping("/api/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/api/tasks/{taskId}/complete")
    public ResponseEntity<TaskDto> completeTask(@PathVariable Long taskId) {
        TaskDto completedTask = taskService.completeTask(taskId);
        return ResponseEntity.ok(completedTask);
    }

    @GetMapping("/api/tasks/employee/{employeeId}")
    public ResponseEntity<List<TaskDto>> getTasksByEmployee(
            @PathVariable Long employeeId) {

        List<TaskDto> tasks = taskService.getTasksByEmployee(employeeId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/api/tasks/manager/{managerId}")
    public ResponseEntity<List<TaskDto>> getTasksByManager(
            @PathVariable Long managerId) {

        List<TaskDto> tasks = taskService.getTasksByManager(managerId);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{taskId}/manager/{managerId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            @PathVariable Long managerId) {

        taskService.deleteTask(taskId, managerId);
        return ResponseEntity.noContent().build();
    }
}

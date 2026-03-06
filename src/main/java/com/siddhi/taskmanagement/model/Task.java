package com.siddhi.taskmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private LocalDate assignedDate;

    private LocalDate dueDate;

    private LocalDate completedDate;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // Manager who created the task
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User manager;

    // Employee to whom task is assigned
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User employee;

    public Task(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", assignedDate=" + assignedDate +
                ", dueDate=" + dueDate +
                ", completedDate=" + completedDate +
                ", managerId=" + (manager != null ? manager.getId() : null) +
                ", employeeId=" + (employee != null ? employee.getId() : null) +
                '}';
    }

}

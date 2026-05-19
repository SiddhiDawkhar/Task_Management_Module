package com.siddhi.taskmanagement.dto;

import com.siddhi.taskmanagement.model.TaskStatus;
import com.siddhi.taskmanagement.model.TaskPriority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskDto {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private LocalDate assignedDate;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;

    private LocalDate completedDate;

    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @NotNull(message = "Manager id is required")
    private Long managerId;

    @NotNull(message = "Employee id is required")
    private Long employeeId;

    public TaskDto() {
    }

    // Full constructor
    public TaskDto(Long id, String title, String description,
                   LocalDate assignedDate, LocalDate dueDate,
                   LocalDate completedDate, TaskStatus status, TaskPriority priority,
                   Long managerId, Long employeeId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.status = status;
        this.priority = priority;
        this.managerId = managerId;
        this.employeeId = employeeId;
    }

    // getters & setters
    public Long getId()
    { return id;
    }

    public void setId(Long id)
    { this.id = id;
    }

    public String getTitle()
    { return title;
    }

    public void setTitle(String title)
    { this.title = title;
    }

    public String getDescription()
    { return description;
    }

    public void setDescription(String description)
    { this.description = description;
    }

    public LocalDate getAssignedDate()
    { return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate)
    { this.assignedDate = assignedDate;
    }

    public LocalDate getDueDate()
    { return dueDate;
    }

    public void setDueDate(LocalDate dueDate)
    { this.dueDate = dueDate;
    }

    public LocalDate getCompletedDate()
    { return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate)
    { this.completedDate = completedDate; }

    public TaskStatus getStatus()
    { return status;
    }

    public void setStatus(TaskStatus status)
    { this.status = status;
    }

    public TaskPriority getPriority()
    { return priority;
    }

    public void setPriority(TaskPriority priority)
    { this.priority = priority;
    }

    public Long getManagerId()
    { return managerId;
    }

    public void setManagerId(Long managerId)
    { this.managerId = managerId;
    }

    public Long getEmployeeId()
    { return employeeId;
    }

    public void setEmployeeId(Long employeeId)
    { this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", managerId=" + managerId +
                ", employeeId=" + employeeId +
                '}';
    }
}

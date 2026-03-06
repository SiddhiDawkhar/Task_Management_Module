package com.siddhi.taskmanagement.dto;

import com.siddhi.taskmanagement.model.TaskStatus;
import java.time.LocalDate;

public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate assignedDate;
    private LocalDate dueDate;
    private LocalDate completedDate;
    private TaskStatus status;
    private Long managerId;
    private Long employeeId;

    public TaskDto() {
    }

    // Full constructor
    public TaskDto(Long id, String title, String description,
                   LocalDate assignedDate, LocalDate dueDate,
                   LocalDate completedDate, TaskStatus status,
                   Long managerId, Long employeeId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.status = status;
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
                ", managerId=" + managerId +
                ", employeeId=" + employeeId +
                '}';
    }
}

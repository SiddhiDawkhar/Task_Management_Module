package com.siddhi.taskmanagement.repository;

import com.siddhi.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task ,Long> {

    //List of task assigned to employee
    List<Task> findByEmployeeId (Long employeeId);

    //List of task assigned by manager
    List<Task> findByManagerId (Long managerId);
}

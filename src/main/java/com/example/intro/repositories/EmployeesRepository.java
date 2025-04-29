package com.example.intro.repositories;

import com.example.intro.models.Department;
import com.example.intro.models.Employee;
import com.example.intro.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(Status status);
    List<Employee> findByDepartment(Department department);
}

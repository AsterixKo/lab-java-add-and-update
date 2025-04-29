package com.example.intro.repositories;

import com.example.intro.models.Department;
import com.example.intro.models.Patient;
import com.example.intro.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByDateOfBirthBetween(Date start, Date end);

    @Query("SELECT p FROM Patient p INNER JOIN p.employee e where e.department = :department")
    List<Patient> findAllByEmployeesDepartment(@Param("department") Department department);

    @Query("SELECT p FROM Patient p INNER JOIN p.employee e where e.status = :status")
    List<Patient> findAllByEmployeesStatus(@Param("status") Status status);
}

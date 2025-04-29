package com.example.intro.services;

import com.example.intro.dtos.EmployeeCreationDTO;
import com.example.intro.dtos.EmployeeUpdateDepartmentDTO;
import com.example.intro.dtos.PatientCreationDTO;
import com.example.intro.models.Department;
import com.example.intro.models.Employee;
import com.example.intro.models.Patient;
import com.example.intro.models.Status;
import com.example.intro.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository;


    public Employee createEmployee(EmployeeCreationDTO employeeCreationDTO) {
        Employee employee = null;
        if (employeeCreationDTO.getName().isEmpty()) {
            System.out.println("employee name is empty");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            //comprobamos los department y status
            System.out.println("checking department and status, and creating employee");
            employee = new Employee(
                    Department.valueOf(employeeCreationDTO.getDepartment().toUpperCase()),
                    employeeCreationDTO.getName(),
                    Status.valueOf(employeeCreationDTO.getStatus().toUpperCase())
            );
            System.out.println("saving employee");
            employeesRepository.save(employee);
        }

        return employee;
    }

    public Employee updateEmployeeDepartment(Long id, EmployeeUpdateDepartmentDTO employeeUpdateDepartmentDTO) {
        Employee employee = null;
        if (id == null) {
            System.out.println("employee not found by id null");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            Optional<Employee> employeeOptional = employeesRepository.findById(id);
            if (employeeOptional.isPresent()) {
                if (employeeUpdateDepartmentDTO.getDepartment() == null ||
                        employeeUpdateDepartmentDTO.getDepartment().isEmpty()) {
                    System.out.println("employee department null or empty");
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT);
                }
                Employee employeeToUpdate = employeeOptional.get();
                employeeToUpdate.setDepartment(
                        Department.valueOf(employeeUpdateDepartmentDTO.getDepartment().toUpperCase()));
                employee = employeesRepository.save(employeeToUpdate);
            } else {
                System.out.println("employee not found by id");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return employee;
    }
}

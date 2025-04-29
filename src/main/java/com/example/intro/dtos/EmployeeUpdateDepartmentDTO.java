package com.example.intro.dtos;

public class EmployeeUpdateDepartmentDTO {
    private String department;

    public EmployeeUpdateDepartmentDTO() {
    }

    public EmployeeUpdateDepartmentDTO(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeUpdateDepartmentDTO{" +
                "department='" + department + '\'' +
                '}';
    }
}

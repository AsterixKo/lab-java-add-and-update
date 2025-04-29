package com.example.intro.dtos;

public class EmployeeCreationDTO {
    private String department;
    private String name;
    private String status;

    public EmployeeCreationDTO() {
    }

    public EmployeeCreationDTO(String department, String name, String status) {
        this.department = department;
        this.name = name;
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeCreationDTO{" +
                "department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

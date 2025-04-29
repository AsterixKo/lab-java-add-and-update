package com.example.intro.dtos;

public class EmployeeUpdateStatusDTO {
    String status;

    public EmployeeUpdateStatusDTO() {
    }

    public EmployeeUpdateStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeUpdateStatusDTO{" +
                "status='" + status + '\'' +
                '}';
    }
}

package com.example.intro.dtos;

public class PatientCreationDTO {
    private String name;
    private String dateOfBirth;
    private Long employee;

    public PatientCreationDTO() {
    }

    public PatientCreationDTO(String name, String dateOfBirth, Long employee) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PatientCreationDTO{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", employee=" + employee +
                '}';
    }
}

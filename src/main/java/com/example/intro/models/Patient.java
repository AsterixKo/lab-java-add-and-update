package com.example.intro.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne
    @JoinColumn(name = "admitted_by", nullable = false)
    private Employee employee;

    public Patient() {
    }

    public Patient(String name, Date dateOfBirth, Employee employee) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.employee = employee;
    }

    public Patient(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", employee=" + employee +
                '}';
    }
}

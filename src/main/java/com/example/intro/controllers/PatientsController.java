package com.example.intro.controllers;

import com.example.intro.dtos.PatientCreationDTO;
import com.example.intro.dtos.PatientUpdateAllDTO;
import com.example.intro.models.Department;
import com.example.intro.models.Employee;
import com.example.intro.models.Patient;
import com.example.intro.models.Status;
import com.example.intro.repositories.PatientsRepository;
import com.example.intro.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPacients() {
        return patientsRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getEmployeeById(@PathVariable("id") Long patientId) {

        return patientsRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @GetMapping("/byDateBirthRange")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDateBirthRange(
            @RequestParam(name = "startDate", required = true) String startDate,
            @RequestParam(name = "endDate", required = true) String endDate) {

        java.util.Date utilDateStart = null;
        java.util.Date utilDateEnd = null;
        java.sql.Date sqlDateStart = null;
        java.sql.Date sqlDateEnd = null;
        try {
            utilDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error parsing start date");
        }

        try {
            utilDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error parsing end date");
        }
        sqlDateStart = new java.sql.Date(utilDateStart.getTime());
        sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());

        return patientsRepository.findAllByDateOfBirthBetween(sqlDateStart, sqlDateEnd);
    }

    @GetMapping("/byDepartment")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDepartment(
            @RequestParam(name = "department", required = true) Optional<String> departmentOptional) {

        if (departmentOptional.isPresent()) {
            return patientsRepository.findAllByEmployeesDepartment(
                    Department.valueOf(departmentOptional.get().toUpperCase()));
        } else {
            return null;
        }
    }

    @GetMapping("/byStatus")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByStatus(
            @RequestParam(name = "status", required = true) Optional<String> statusOptional) {

        if (statusOptional.isPresent()) {
            return patientsRepository.findAllByEmployeesStatus(Status.valueOf(statusOptional.get().toUpperCase()));
        } else {
            return null;
        }
    }

    @GetMapping("/byStatusOFF")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByStatusOFF() {
        return patientsRepository.findAllByEmployeesStatus(Status.OFF);
    }

    // utilizara la ruta "/api/patients"
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createNewPatient(@RequestBody PatientCreationDTO patientCreationDTO){
        return patientService.createPatient(patientCreationDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable Long id, @RequestBody PatientUpdateAllDTO patientUpdateAllDTO) {
        return patientService.updatePatient(id, patientUpdateAllDTO);
    }
}

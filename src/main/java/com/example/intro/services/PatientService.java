package com.example.intro.services;

import com.example.intro.dtos.PatientCreationDTO;
import com.example.intro.dtos.PatientUpdateAllDTO;
import com.example.intro.models.Employee;
import com.example.intro.models.Patient;
import com.example.intro.repositories.EmployeesRepository;
import com.example.intro.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    public Patient createPatient(PatientCreationDTO patientCreationDTO) {
        Patient patient = null;
        if (patientCreationDTO.getEmployee() == null) {
            System.out.println("employee is null");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            Optional<Employee> employee = employeesRepository.findById(patientCreationDTO.getEmployee());
            if (employee.isPresent()) {
                System.out.println("employee is present");
                System.out.println("converting string date to sql Date");
                java.util.Date utilDateOfBirth = null;
                java.sql.Date sqlDateOfBirth = null;
                try {
                    utilDateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(patientCreationDTO.getDateOfBirth());
                } catch (ParseException e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error parsing dateOfBirth");
                }

                sqlDateOfBirth = new java.sql.Date(utilDateOfBirth.getTime());

                patient = new Patient(patientCreationDTO.getName(), sqlDateOfBirth, employee.get());
                System.out.println("saving patient: " + patient);
                patient = patientsRepository.save(patient);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return patient;
    }

    public Patient updatePatient(Long id, PatientUpdateAllDTO patientUpdateAllDTO) {
        Patient patient = null;

        Optional<Patient> patientOptionalFound = patientsRepository.findById(id);
        if (patientOptionalFound.isEmpty()) {
            System.out.println("patient not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (patientUpdateAllDTO.getEmployee() == null) {
            System.out.println("employee is null");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee can not be null");
        } else {
            Optional<Employee> employee = employeesRepository.findById(patientUpdateAllDTO.getEmployee());
            if (employee.isPresent()) {
                System.out.println("employee is present");
                System.out.println("converting string date to sql Date");
                java.util.Date utilDateOfBirth = null;
                java.sql.Date sqlDateOfBirth = null;
                try {
                    utilDateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(patientUpdateAllDTO.getDateOfBirth());
                } catch (ParseException e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error parsing dateOfBirth");
                }

                sqlDateOfBirth = new java.sql.Date(utilDateOfBirth.getTime());

                Patient patientToSave = patientOptionalFound.get();
                patientToSave.setName(patientUpdateAllDTO.getName());
                patientToSave.setDateOfBirth(sqlDateOfBirth);
                patientToSave.setEmployee(employee.get());
                System.out.println("updating patient: " + patientToSave);
                patient = patientsRepository.save(patientToSave);
            } else {
                System.out.println("Employee not found");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
            }
        }

        return patient;
    }
}

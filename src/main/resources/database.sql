
CREATE TABLE employees (
    employee_id int NOT NULL AUTO_INCREMENT,
    department varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE TABLE patients (
	patient_id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(255) NOT NULL,
    date_of_birth Date NOT NULL,
    admitted_by int,
    FOREIGN KEY (admitted_by) REFERENCES employees(employee_id)
);

-- Empleados (con departments en mayúsculas)
INSERT INTO employees (employee_id, department, name, status) VALUES
  (356712, 'CARDIOLOGY',  'Alonso Flores',      'ON_CALL'),
  (564134, 'IMMUNOLOGY',  'Sam Ortega',         'ON'),
  (761527, 'CARDIOLOGY',  'German Ruiz',        'OFF'),
  (166552, 'PULMONARY',   'Maria Lin',          'ON'),
  (156545, 'ORTHOPAEDIC', 'Paolo Rodriguez',    'ON_CALL'),
  (172456, 'PSYCHIATRIC', 'John Paul Armes',    'OFF');

-- Pacientes
INSERT INTO patients (patient_id, name, date_of_birth, admitted_by) VALUES
  (1, 'Jaime Jordan',       '1984-03-02', 564134),
  (2, 'Marian Garcia',      '1972-01-12', 564134),
  (3, 'Julia Dusterdieck',  '1954-06-11', 356712),
  (4, 'Steve McDuck',       '1931-11-10', 761527),
  (5, 'Marian Garcia',      '1999-02-15', 172456);
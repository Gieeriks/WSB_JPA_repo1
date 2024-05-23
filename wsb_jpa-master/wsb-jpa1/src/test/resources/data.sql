-- Usuwanie tabel, jeśli istnieją
DROP TABLE IF EXISTS visit CASCADE;
DROP TABLE IF EXISTS patient CASCADE;
DROP TABLE IF EXISTS doctor CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS medical_treatment CASCADE;

-- Tworzenie tabel
CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(255),
    postal_code VARCHAR(10)
);

CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    telephone_number VARCHAR(20),
    email VARCHAR(255),
    doctor_number VARCHAR(20),
    specialization VARCHAR(50),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    telephone_number VARCHAR(20),
    email VARCHAR(255),
    patient_number VARCHAR(20),
    date_of_birth DATE,
    active BOOLEAN,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE medical_treatment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    type VARCHAR(50)
);

CREATE TABLE visit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    time TIMESTAMP,
    doctor_id BIGINT,
    patient_id BIGINT,
    medical_treatment_id BIGINT,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (medical_treatment_id) REFERENCES medical_treatment(id)
);

-- Wstawianie danych do tabeli address
insert into address (id, address_line1, address_line2, city, postal_code)
values (1, 'xx', 'yy', 'city', '62-030');

insert into address (id, address_line1, address_line2, city, postal_code)
values (2, 'aa', 'bb', 'another_city', '72-050');

-- Wstawianie danych do tabeli doctor
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values (1, 'John', 'Doe', '123456789', 'john.doe@example.com', 'DOC001', 'CARDIOLOGY', 1);

insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values (2, 'Jane', 'Smith', '987654321', 'jane.smith@example.com', 'DOC002', 'DERMATOLOGY', 2);

-- Wstawianie danych do tabeli patient
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, active, address_id)
values (1, 'Alice', 'Johnson', '555666777', 'alice.johnson@example.com', 'PAT001', '1990-01-01', TRUE, 1);

insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, active, address_id)
values (2, 'Bob', 'Brown', '444555666', 'bob.brown@example.com', 'PAT002', '1985-05-05', TRUE, 2);

-- Wstawianie danych do tabeli medical_treatment
insert into medical_treatment (id, description, type)
values (1, 'Physical Therapy', 'PHYSIOTHERAPY');

insert into medical_treatment (id, description, type)
values (2, 'Chemotherapy', 'CHEMOTHERAPY');

-- Wstawianie danych do tabeli visit
insert into visit (id, description, time, doctor_id, patient_id, medical_treatment_id)
values (1, 'Initial consultation', '2024-05-01T10:00:00', 1, 1, 1);

insert into visit (id, description, time, doctor_id, patient_id, medical_treatment_id)
values (2, 'Follow-up visit', '2024-05-08T14:00:00', 2, 2, 2);

insert into visit (id, description, time, doctor_id, patient_id, medical_treatment_id)
values (3, 'Physical Therapy Session', '2024-06-01T10:00:00', 1, 1, 1);

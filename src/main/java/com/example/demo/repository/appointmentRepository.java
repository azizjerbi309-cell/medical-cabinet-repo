package com.example.demo.repository;

import com.example.demo.model.appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface appointmentRepository extends JpaRepository<appointment, Long> {

    // Find all appointments for a specific patient
    List<appointment> findByPatient(Patient patient);

    // Find all appointments for a specific doctor
    List<appointment> findByDoctor(Doctor doctor);

    // Find all appointments on a specific date
    List<appointment> findByAppointmentDate(LocalDate date);

    // Find all appointments for a doctor on a specific date
    List<appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDate date);

    // Find all appointments for a patient on a specific date
    List<appointment> findByPatientAndAppointmentDate(Patient patient, LocalDate date);

}

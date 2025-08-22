package com.example.demo.service;

import com.example.demo.model.appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.appointment;
import com.example.demo.repository.appointmentRepository;
import com.example.demo.repository.appointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class appointmentService {

    private final appointmentRepository appointmentRepository;

    public appointmentService(appointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Create or update an appointment
    public appointment saveAppointment(appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Optional<appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Delete appointment
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Get appointments by patient
    public List<appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    // Get appointments by doctor
    public List<appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    // Get appointments by date
    public List<appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    // Get appointments for doctor on a specific date
    public List<appointment> getAppointmentsByDoctorAndDate(Doctor doctor, LocalDate date) {
        return appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);
    }

    // Get appointments for patient on a specific date
    public List<appointment> getAppointmentsByPatientAndDate(Patient patient, LocalDate date) {
        return appointmentRepository.findByPatientAndAppointmentDate(patient, date);
    }
}

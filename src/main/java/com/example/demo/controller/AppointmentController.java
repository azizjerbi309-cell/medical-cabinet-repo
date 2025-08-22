package com.example.demo.controller;

import com.example.demo.model.appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.appointment;
import com.example.demo.service.appointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3306")
public class AppointmentController {

    private final appointmentService appointmentService;

    public AppointmentController(appointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get all appointments
    @GetMapping
    public List<appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<appointment> getAppointmentById(@PathVariable Long id) {
        Optional<appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new appointment
    @PostMapping
    public appointment createAppointment(@RequestBody appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    // Update an existing appointment
    @PutMapping("/{id}")
    public ResponseEntity<appointment> updateAppointment(@PathVariable Long id, @RequestBody appointment appointmentDetails) {
        Optional<appointment> appointmentOptional = appointmentService.getAppointmentById(id);
        if (appointmentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        appointment appointment = appointmentOptional.get();
        appointment.setPatient(appointmentDetails.getPatient());
        appointment.setDoctor(appointmentDetails.getDoctor());
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setStartTime(appointmentDetails.getStartTime());
        appointment.setEndTime(appointmentDetails.getEndTime());
        appointment.setStatus(appointmentDetails.getStatus());
        appointment.setNotes(appointmentDetails.getNotes());

        appointment updatedAppointment = appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    // Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        Optional<appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // Get appointments for a patient by patient ID
    @GetMapping("/patient/{patientId}")
    public List<appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        return appointmentService.getAppointmentsByPatient(patient);
    }

    // Get appointments for a doctor by doctor ID
    @GetMapping("/doctor/{doctorId}")
    public List<appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        return appointmentService.getAppointmentsByDoctor(doctor);
    }

    // Get appointments by date (YYYY-MM-DD)
    @GetMapping("/date/{date}")
    public List<appointment> getAppointmentsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return appointmentService.getAppointmentsByDate(localDate);
    }
}

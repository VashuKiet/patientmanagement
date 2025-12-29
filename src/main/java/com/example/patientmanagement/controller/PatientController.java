package com.example.patientmanagement.controller;

import com.example.patientmanagement.model.Patient;
import com.example.patientmanagement.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // ===================== WEB (HTML) =====================

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("patients", service.getAllPatients());
        model.addAttribute("patient", new Patient());
        return "patients";
    }

    @PostMapping("/patients")
    public String addPatient(Patient patient) {
        service.addPatient(patient);
        return "redirect:/";
    }

    @GetMapping("/patients/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", service.getPatientById(id));
        model.addAttribute("patients", service.getAllPatients());
        return "patients";
    }

    @PostMapping("/patients/update")
    public String updatePatient(Patient patient) {
        service.updatePatient(patient.getId(), patient);
        return "redirect:/";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return "redirect:/";
    }

    // ===================== POSTMAN (REST APIs) =====================

    @PostMapping("/api/patients")
    @ResponseBody
    public Patient addPatientApi(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    @GetMapping("/api/patients")
    @ResponseBody
    public Collection<Patient> getAllPatientsApi() {
        return service.getAllPatients();
    }

    @GetMapping("/api/patients/{id}")
    @ResponseBody
    public Patient getPatientByIdApi(@PathVariable Long id) {
        return service.getPatientById(id);
    }

    @PutMapping("/api/patients/{id}")
    @ResponseBody
    public Patient updatePatientApi(@PathVariable Long id,
                                    @RequestBody Patient patient) {
        return service.updatePatient(id, patient);
    }

    @DeleteMapping("/api/patients/{id}")
    @ResponseBody
    public String deletePatientApi(@PathVariable Long id) {
        service.deletePatient(id);
        return "Patient deleted successfully";
    }
}

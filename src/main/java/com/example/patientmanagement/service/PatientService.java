package com.example.patientmanagement.service;

import com.example.patientmanagement.model.Patient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PatientService {

    private final Map<Long, Patient> store = new HashMap<>();
    private Long idCounter = 1L;

    public Patient addPatient(Patient patient) {
        patient.setId(idCounter++);
        store.put(patient.getId(), patient);
        return patient;
    }

    public Collection<Patient> getAllPatients() {
        return store.values();
    }

    public Patient getPatientById(Long id) {
        return store.get(id);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Patient existing = store.get(id);
        if (existing != null) {
            existing.setName(patient.getName());
            existing.setDisease(patient.getDisease());
        }
        return existing;
    }

    public void deletePatient(Long id) {
        store.remove(id);
    }
}

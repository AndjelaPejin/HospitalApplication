package com.example.hospitalApi.service;

import com.example.hospitalApi.dto.PatientDTO;

import java.util.List;

public interface IPatientService {
    PatientDTO create(PatientDTO patientDTO);
    List<PatientDTO> getAll();
    PatientDTO getById(String id);
    PatientDTO update(PatientDTO patientDTO, String id);
    PatientDTO deleteById(String id);

}

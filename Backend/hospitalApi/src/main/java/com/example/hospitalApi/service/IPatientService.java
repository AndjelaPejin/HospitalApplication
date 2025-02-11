package com.example.hospitalApi.service;

import com.example.hospitalApi.dto.PatientDTO;
import com.example.hospitalApi.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface IPatientService {
    PatientDTO create(PatientDTO patientDTO);
    Page<Patient> getAllByPage(int page, int size);
    List<PatientDTO> getAll();
    PatientDTO getById(String id);
    PatientDTO update(PatientDTO patientDTO, String id);
    PatientDTO deleteById(String id);

}

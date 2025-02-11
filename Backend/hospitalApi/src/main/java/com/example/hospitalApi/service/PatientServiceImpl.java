package com.example.hospitalApi.service;

import com.example.hospitalApi.dto.PatientDTO;
import com.example.hospitalApi.model.Address;
import com.example.hospitalApi.model.Doctor;
import com.example.hospitalApi.model.Patient;
import com.example.hospitalApi.repository.IAddressRepo;
import com.example.hospitalApi.repository.IDoctorRepo;
import com.example.hospitalApi.repository.IPatientRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private final IPatientRepo patientRepo;
    private final IDoctorRepo doctorRepo;
    private final IAddressRepo addressRepo;
    private final ModelMapper mapper;

    @Override
    public PatientDTO create(PatientDTO patientDTO) {
        Patient patient = mapper.map(patientDTO, Patient.class);
        if(patientDTO.getDoctor() != null){
            Doctor doctor = doctorRepo.findById(patientDTO.getDoctor().getId()).orElseThrow(() -> new RuntimeException("Doctor not found."));
            patient.setDoctor(doctor);
        }
        Patient saved = patientRepo.save(patient);
        return mapper.map(saved, PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAll(){
        List<Patient>patients = patientRepo.findAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        patients.forEach(p -> patientDTOS.add(mapper.map(p,PatientDTO.class)));
        return patientDTOS;
    }



    @Override
    public Page<Patient> getAllByPage(int page, int size){
        return patientRepo.findAll(PageRequest.of(page,size, Sort.by("name")));

    }

    @Override
    public PatientDTO getById(String id) {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new RuntimeException("Patient with id "+ id + "not found"));
        return mapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO update(PatientDTO patientDTO, String id) {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient with id "+ id + " not found."));
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        Address address = addressRepo.findById(patientDTO.getAddress().getId()).orElseThrow(() -> new EntityNotFoundException("Address with id "+ id + " not found."));
        patient.setAddress(address);
        patient.setEmail(patientDTO.getEmail());
        patient.setPhone(patientDTO.getPhone());
        patient.setUsername(patientDTO.getUsername());
        patient.setHealthCardNumber(patientDTO.getHealthCardNumber());
        patientRepo.save(patient);
        return mapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO deleteById(String id) {
        Patient patient = patientRepo.findById(id).orElseThrow(() ->  new RuntimeException("Patient with id " + id + "not found"));
        PatientDTO deleted = mapper.map(patient, PatientDTO.class);
        patientRepo.deleteById(id);
        return deleted;

    }
}


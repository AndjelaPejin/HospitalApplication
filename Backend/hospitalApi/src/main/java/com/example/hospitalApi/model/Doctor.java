package com.example.hospitalApi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor extends BaseEntity{
    private String photoUrl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @JsonManagedReference(value = "patient-doctors")
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    private List<Patient> patients = new ArrayList<>();

    @JsonManagedReference(value = "appointment-doctors")
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    private List<Appointment> appointments = new ArrayList<>();
}

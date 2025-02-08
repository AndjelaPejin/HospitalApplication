package com.example.hospitalApi.dto;

import com.example.hospitalApi.model.Address;
import com.example.hospitalApi.model.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Address address;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String photoUrl;
    @NotNull
    private String healthCardNumber;
    private String username;
    private Doctor doctor;
}

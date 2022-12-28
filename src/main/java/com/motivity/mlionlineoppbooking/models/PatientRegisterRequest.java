package com.motivity.mlionlineoppbooking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterRequest {
    private String email;
    private String password;
    private String phoneNo;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String age;
}

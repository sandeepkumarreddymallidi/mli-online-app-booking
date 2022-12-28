package com.motivity.mlionlineoppbooking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegisterRequest {
    private String email;
    private String password;
    private String phoneNo;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String department;
    private String experience;
}

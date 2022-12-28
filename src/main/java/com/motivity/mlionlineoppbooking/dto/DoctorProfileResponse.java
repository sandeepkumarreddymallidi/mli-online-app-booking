package com.motivity.mlionlineoppbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileResponse {
    private long userId;
    private String email;
    private String phoneNo;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String department;
    private String experience;
}

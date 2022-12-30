package com.motivity.mlionlineoppbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorListRequest {
    private int userId;
    private String firstName;
    private String lastName;
    private String department;
    private String phoneNo;
    private String gender;
    private String email;
    private String experience;
}

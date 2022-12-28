package com.motivity.mlionlineoppbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String age;
    private String address;
    private String experience;
}

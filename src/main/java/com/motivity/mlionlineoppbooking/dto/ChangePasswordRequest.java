package com.motivity.mlionlineoppbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    private long userId;
    private String currentPassword;
    private String newPassword;
}

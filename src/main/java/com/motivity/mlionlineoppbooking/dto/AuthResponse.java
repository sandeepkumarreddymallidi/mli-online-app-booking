package com.motivity.mlionlineoppbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String authToken;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    private String refreshToken;
}

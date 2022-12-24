package com.motivity.mlionlineoppbooking.controller;

import com.motivity.mlionlineoppbooking.dto.AuthRequest;
import com.motivity.mlionlineoppbooking.dto.AuthResponse;
import com.motivity.mlionlineoppbooking.entity.Users;
import com.motivity.mlionlineoppbooking.models.PatientRegisterRequest;
import com.motivity.mlionlineoppbooking.service.UsersService;
import com.motivity.mlionlineoppbooking.service.serviceimpl.UsersServiceImpl;
import com.motivity.mlionlineoppbooking.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersServiceImpl service;
    @PostMapping("/userRegister")
    public String patientRegister(@RequestBody PatientRegisterRequest registerRequest)
    {
        Users user = usersService.saveUsers(registerRequest);
        if(user==null)
        {
            return "failed";
        }
        else
        {
            return "success";
        }

    }

    @PostMapping("/patientLogin")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) {
        //  : validate un/pwd with data base
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword())
        );

        UserDetails userDetails = service.loadUserByUsername(authRequest.getEmail());
        if (passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(authRequest.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
        }
        return null;
    }


}

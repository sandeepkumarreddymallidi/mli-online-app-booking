package com.motivity.mlionlineoppbooking.controller;

import com.motivity.mlionlineoppbooking.dto.*;
import com.motivity.mlionlineoppbooking.entity.Users;
import com.motivity.mlionlineoppbooking.models.DoctorRegisterRequest;
import com.motivity.mlionlineoppbooking.models.PatientRegisterRequest;
import com.motivity.mlionlineoppbooking.repository.UsersRepository;
import com.motivity.mlionlineoppbooking.service.UsersService;
import com.motivity.mlionlineoppbooking.service.serviceimpl.UsersServiceImpl;
import com.motivity.mlionlineoppbooking.utility.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersServiceImpl service;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/userRegister")
    public ResponseEntity<String > patientRegister(@RequestBody PatientRegisterRequest registerRequest)
    {
        Users user = usersService.saveUsers(registerRequest);
        if(user==null)
        {
            return new ResponseEntity<>("failed",HttpStatus.EXPECTATION_FAILED);
        }
        else
        {
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
    }
    @PostMapping("/doctorRegister")
    public ResponseEntity<String> doctorRegister(@RequestBody DoctorRegisterRequest registerRequest)
    {
        Users user = usersService.saveDoctor(registerRequest);
        if(user==null)
        {
            return new ResponseEntity<>("failed",HttpStatus.EXPECTATION_FAILED);

        }
        else
        {
            return new ResponseEntity<>("success",HttpStatus.OK);

        }
    }
    @GetMapping("getDoctorProfileData")
    public ResponseEntity<DoctorProfileResponse> doctorProfileResponse(@RequestParam (name = "email") String email)
    {
       Users users = usersRepository.findByEmail(email).get();

    return new ResponseEntity<>(modelMapper.map(users,DoctorProfileResponse.class), HttpStatus.OK);
    }
    @GetMapping("getPatientProfileData")
    public ResponseEntity<PatientProfileResponse> patientProfileResponse(@RequestParam (name = "email") String email)
    {
        Users users = usersRepository.findByEmail(email).get();

        return new ResponseEntity<>(modelMapper.map(users,PatientProfileResponse.class), HttpStatus.OK);
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

    @PostMapping("updateDoctor")
    public ResponseEntity<DoctorProfileResponse> updateDoctors(@RequestBody UpdateProfileRequest profile)
    {

        int result=usersRepository.updateDoctor(profile.getFirstName(), profile.getLastName(),profile.getPhoneNo(),profile.getAddress(),profile.getExperience(), profile.getUserId());

        if(result==1)
        {
            return new ResponseEntity<>(modelMapper.map(doctorProfileResponse(profile.getEmail()),DoctorProfileResponse.class),HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("updatePatient")
    public ResponseEntity<PatientProfileResponse> updatePatient(@RequestBody UpdateProfileRequest profile)
    {

        int result=usersRepository.updatePatient(profile.getFirstName(), profile.getLastName(),profile.getPhoneNo(),profile.getAddress(),profile.getAge(), profile.getUserId());

        if(result==1)
        {
            return new ResponseEntity<>(modelMapper.map(patientProfileResponse(profile.getEmail()),PatientProfileResponse.class),HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/passwordChange")
    public ResponseEntity<String> ChangePassword(@RequestBody ChangePasswordRequest passwordRequest) {
        System.out.println(passwordRequest.getUserId());
        Users user = usersRepository.findById(passwordRequest.getUserId()).get();

        if (passwordEncoder.matches(passwordRequest.getCurrentPassword(),user.getPassword())) {

            int result = usersRepository.changeDoctorPassword(passwordEncoder.encode(passwordRequest.getNewPassword()), passwordRequest.getUserId());
            return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
        }


    }


}

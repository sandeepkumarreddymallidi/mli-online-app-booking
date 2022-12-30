package com.motivity.mlionlineoppbooking.service;

import com.motivity.mlionlineoppbooking.entity.Users;
import com.motivity.mlionlineoppbooking.models.DoctorRegisterRequest;
import com.motivity.mlionlineoppbooking.models.PatientRegisterRequest;
import com.motivity.mlionlineoppbooking.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsersService {

    public Users saveUsers(PatientRegisterRequest registerRequest);

    public Users saveDoctor(DoctorRegisterRequest registerRequest);

    public Optional<Users> findByEmail(String email);

    public List<Users> findByRoles(String role);

}

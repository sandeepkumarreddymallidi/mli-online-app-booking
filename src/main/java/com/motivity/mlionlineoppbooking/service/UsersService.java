package com.motivity.mlionlineoppbooking.service;

import com.motivity.mlionlineoppbooking.entity.Users;
import com.motivity.mlionlineoppbooking.models.PatientRegisterRequest;
import com.motivity.mlionlineoppbooking.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsersService {

    public Users saveUsers(PatientRegisterRequest registerRequest);

    public Optional<Users> findByEmail(String email);

}

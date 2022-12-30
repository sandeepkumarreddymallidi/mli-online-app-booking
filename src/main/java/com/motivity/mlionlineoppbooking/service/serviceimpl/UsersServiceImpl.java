package com.motivity.mlionlineoppbooking.service.serviceimpl;

import com.motivity.mlionlineoppbooking.entity.Users;
import com.motivity.mlionlineoppbooking.models.DoctorRegisterRequest;
import com.motivity.mlionlineoppbooking.models.PatientRegisterRequest;
import com.motivity.mlionlineoppbooking.repository.UsersRepository;
import com.motivity.mlionlineoppbooking.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService , UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Users saveUsers(PatientRegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
      Users users  =modelMapper.map(registerRequest,Users.class);
      users.setRoles(Collections.singleton("user"));
        return usersRepository.save(users);
    }

    @Override
    public Users saveDoctor(DoctorRegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Users users  =modelMapper.map(registerRequest,Users.class);
        users.setRoles(Collections.singleton("doctor"));
        return usersRepository.save(users);
    }
    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<Users> findByRoles(String role) {
        return usersRepository.findByRoles(role);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users users= findByEmail(username).get();
        if(users==null)
            throw new UsernameNotFoundException("user not exits");


        return new User(username,
                users.getPassword(),
                users.getRoles().stream().map(role->new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList())
        );
    }
}

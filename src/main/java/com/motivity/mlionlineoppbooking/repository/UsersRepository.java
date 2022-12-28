package com.motivity.mlionlineoppbooking.repository;

import com.motivity.mlionlineoppbooking.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
       public  Optional<Users> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update Users user set user.firstName=:firstname,user.lastName=:lastname,user.phoneNo=:phoneNo,user.address=:address,user.experience=:experience where user.userId=:id")
    public int updateDoctor(String firstname,String lastname,String phoneNo,String address,String experience,long id);
    @Transactional
    @Modifying
    @Query("update Users user set user.password=:password where user.userId=:userId")
    public int changeDoctorPassword(String password,long userId);
    @Transactional
    @Modifying
    @Query("update Users user set user.firstName=:firstName,user.lastName=:lastName,user.phoneNo=:phoneNo,user.address=:address,user.age=:age where user.userId=:userId")
    public int updatePatient(String firstName,String lastName,String phoneNo,String address,int age,long userId);


}

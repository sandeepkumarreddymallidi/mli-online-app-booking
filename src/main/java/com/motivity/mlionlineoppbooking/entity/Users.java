package com.motivity.mlionlineoppbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users_Table")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long userId;
    @Column(unique=true)
    private String email;
    private String password;
    private String phoneNo;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String department;
    private String experience;
    @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "RolesTable",
                        joinColumns = @JoinColumn(name = "userId"))
    private Set<String> roles;

}

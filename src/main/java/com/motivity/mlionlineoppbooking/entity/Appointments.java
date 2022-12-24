package com.motivity.mlionlineoppbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
@DynamicInsert
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;
    private String patientId;
    private String patientName;
    private String patientPhoneNo;
    private String patientGender;
    private String patientAge;
    private String problem;
    private String applyDate;
    private String time;
    private String appointmentDate;
    private String doctorName;
    private String doctorGender;
    private String doctorPhoneNo;
    private int doctorId;
    @Column(columnDefinition = "varchar(255) default 'pending'")
    private String status;
}

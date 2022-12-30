package com.motivity.mlionlineoppbooking.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private int appointmentId;
    private long patientId;
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
    private long doctorId;
    private String status;
}

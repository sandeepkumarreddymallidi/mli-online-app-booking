package com.motivity.mlionlineoppbooking.repository;

import com.motivity.mlionlineoppbooking.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointments,Long> {

    @Query("select appointment from Appointments appointment where appointment.patientId=:patientId")
    public List<Appointments> patientAppointmentStatus(long patientId);

    @Query("select a from Appointments a where a.problem=:department and a.status=:status")
    public List<Appointments> showAndAcceptAppointment(String department,String status);

    @Query("select a from Appointments a where a.problem=:department and a.status=:status and a.doctorId=:doctorId")
    public List<Appointments> showAcceptedAppointment(String department,long doctorId, String status);

    @Transactional
    @Modifying
    @Query("update Appointments a set a.doctorPhoneNo=:doctorPhoneNo,a.status=:status, a.time=:time,a.appointmentDate=:appointmentDate,a.doctorName=:doctorName,a.doctorGender=:doctorGender,a.doctorId=:doctorId where a.appointmentId=:appointmentId")
    public int acceptUpdate(String time,String appointmentDate,String doctorName,String doctorGender,long appointmentId,String status,String doctorPhoneNo, long doctorId);

}

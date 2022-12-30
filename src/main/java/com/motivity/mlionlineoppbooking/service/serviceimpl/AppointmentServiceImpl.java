package com.motivity.mlionlineoppbooking.service.serviceimpl;

import com.motivity.mlionlineoppbooking.entity.Appointments;
import com.motivity.mlionlineoppbooking.repository.AppointmentRepo;
import com.motivity.mlionlineoppbooking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Override
    public Appointments saveAppointment(Appointments appointments) {
        return appointmentRepo.save(appointments);
    }
}

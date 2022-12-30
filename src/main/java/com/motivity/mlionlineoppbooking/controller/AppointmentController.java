package com.motivity.mlionlineoppbooking.controller;

import com.motivity.mlionlineoppbooking.dto.AppointmentRequest;
import com.motivity.mlionlineoppbooking.entity.Appointments;
import com.motivity.mlionlineoppbooking.entity.Users;
import com.motivity.mlionlineoppbooking.repository.AppointmentRepo;
import com.motivity.mlionlineoppbooking.repository.UsersRepository;
import com.motivity.mlionlineoppbooking.service.AppointmentService;
import com.motivity.mlionlineoppbooking.service.EmailNotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/insertAppointment")
    public String insertAppointment(@RequestBody AppointmentRequest appointmentRequest) throws MessagingException, UnsupportedEncodingException {

       // Patients patient = patientRepo.findById(Integer.parseInt(appointments.getPatientId())).get();
        Appointments appointmentData = appointmentService.saveAppointment(modelMapper.map(appointmentRequest, Appointments.class));
        Users patient=usersRepository.findById(appointmentRequest.getPatientId()).get();
        if(appointmentData==null)
        {
            return "failed";
        }
        else
        {
            String text = "your request for doctor appointment was created.\n"
                    + "  we will notify you whenever doctor was accepted";
            String to = patient.getEmail();
            String subject = "doctor appointment request created";
            emailNotificationService.sendEmail(to, subject, text);

            return "success";
        }
    }
    @GetMapping("/showStatus")
    public List<Appointments> showStatus(@RequestParam("patientId") long patientId)
    {
        List<Appointments> pt = appointmentRepo.patientAppointmentStatus(patientId);
        return pt;
    }

    @GetMapping("showPendingAppointments")
    public List<Appointments> showPendingAppointments(@RequestParam("department") String department,String status) {

        List<Appointments> appList = appointmentRepo.showAndAcceptAppointment(department, "pending");
        return appList;
    }

    @GetMapping("showAcceptedAppointments")
    public  List<Appointments> showAcceptedAppointments(@RequestParam("department") String department,@RequestParam("doctorId") long doctorId){

        List<Appointments> appList = appointmentRepo.showAcceptedAppointment(department,doctorId,"accepted");
        return appList;
    }

    @GetMapping("/acceptAppointments" )
    public List<Appointments> acceptAppointments(@RequestParam("appointmentId") long appointmentId,@RequestParam("doctorId") long doctorId) throws MessagingException, UnsupportedEncodingException {
        Users doctor = usersRepository.findById(doctorId).get();
        Appointments appointment = appointmentRepo.findById(appointmentId).get();
        Users patient = usersRepository.findById(appointment.getPatientId()).get();
        int status=appointmentRepo.acceptUpdate("", "", doctor.getFirstName()+" "+doctor.getLastName(), doctor.getGender(),appointmentId,"accepted",doctor.getPhoneNo(),doctor.getUserId());
        List<Appointments> appList = appointmentRepo.showAndAcceptAppointment(doctor.getDepartment(), "pending");
        if(status == 1) {
            String text = "<h1>Hai  "+patient.getFirstName()+" "+patient.getLastName()+"</h1>"
                    + "<br><i>your appointment was accepted by "+ doctor.getFirstName()+"</i>,"
                    + "<br> <a href='#'> if any queries contact us </a>"
                    +"<br> <br>"
                    +"<h4>Thank you.</h4>"
                    +"<br><p>Motivity hospitals<p>";
            String to = patient.getEmail();
            String subject = "Motivity hospital appointment status";
            emailNotificationService.sendEmail(to, subject, text);
        }
        return appList;
    }
}

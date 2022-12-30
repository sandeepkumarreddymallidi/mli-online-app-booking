package com.motivity.mlionlineoppbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailNotificationService {

    @Autowired
   private JavaMailSender javaMailSender;

    boolean html=false;
    public void sendEmail(String to,String subject,String text) throws MessagingException, UnsupportedEncodingException {

       MimeMessage mimeMessage =javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
        html=true;
        messageHelper.setFrom("motivityhospital@gmail.com","ML-OCS");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(text,html);
        messageHelper.setCc(new String[]{"veera.rangina@motivitylabs.com", "hari.sana@motivitylabs.com"});

        javaMailSender.send(mimeMessage);

    }
}

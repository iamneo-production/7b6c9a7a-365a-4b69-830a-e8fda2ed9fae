package com.example.springapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceUtil {
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendMessageByEmail(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
//		System.out.println(email);
		message.setFrom("abiramisv27@gmail.com");
		message.setTo(email);
		message.setSubject("Otp Verification Code");
		message.setText("Your Otp Verification code is : " + otp);
		emailSender.send(message);
	}
	
}

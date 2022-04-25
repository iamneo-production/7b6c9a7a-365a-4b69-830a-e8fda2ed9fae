package com.example.springapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OtpController {
	
	@Autowired EmailServiceUtil emailService;
	JwtTokenUtils tokenObject = new JwtTokenUtils();
	
	
	@CrossOrigin
	@SuppressWarnings("unchecked")
	@PostMapping("/user/verifyOtp")
	public Object verifyOtp(@RequestHeader("Authorization") String tokenBearer, @RequestBody String otp) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			String token = tokenBearer.substring(7);
			ObjectMapper m = new ObjectMapper();
			HashMap<String, Object> claims = (HashMap<String, Object>) m.convertValue(tokenObject.getClaimsFromToken(token), Map.class);
			
			if (claims.get("otp").equals(otp)) {	
				response.put("error", null);
				response.put("verified", true);
			}
			else {
				response.put("error", "Otp verification failed");
				response.put("verified", false);
			}
			return response;
		} catch(Exception e) {	
			response.put("error", "Otp verification failed");
			response.put("verified", false);
			return response;
		}
	}	
	
	@CrossOrigin
	@PostMapping("/user/requestOtp")
	public Object generateOtp(@RequestBody String email) {
		
		
		OtpUtils otpWrapper = new OtpUtils();
		String newOtp = otpWrapper.generateOtp();
		
		HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("otp", newOtp);
		HashMap<String, Object> token = tokenObject.generateToken(claims);
		
		emailService.sendMessageByEmail(email, newOtp);
		return token;
	}
}

package com.example.springapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	/*
	 * Getting data perfectly from angular
	 * OBJECTIVES
	 * 1. Hash the password
	 * 2. Validate with the data in the database
	 * 3. Return the message from the response generator*/
	@CrossOrigin
	@PostMapping("/login")
	public Object userLogin(@Valid @RequestBody LoginModel userLoginDataFromClient, BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			List<ObjectError> errs = theBindingResult.getAllErrors();
			ArrayList<String> errorMessages = new ArrayList<String>();
			for (ObjectError errorObject: errs) errorMessages.add(errorObject.getDefaultMessage());
			System.out.println(String.join("\n", errorMessages));
			return false;
		}
		List<UserEntity> data = userRepository.findUserByEmailAndPassword(userLoginDataFromClient.getEmail(), userLoginDataFromClient.getPassword());
		if (data != null && data.size() != 0) {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			wrapper.put("userId", data.get(0).getUser_id());
			wrapper.put("validUser", true);
			if (userLoginDataFromClient.getEmail().contains("baitntackle.com")) wrapper.put("role", "admin");
			return wrapper;
		}
		return false;
	}
}

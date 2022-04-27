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
	/*
	 * Getting data perfectly from angular
	 * OBJECTIVES
	 * 1. Validate the data
	 * 2. Hash the password
	 * 3. Store the data in the database
	 * 4. Return the message from the response generator
	 * */
	@CrossOrigin
	@PostMapping("/signup") 
	public Object userSignup(@RequestBody UserEntity userData) {
		UserEntity storedUserObject;
		try {
//			System.out.println(userData.toString());
//			UserEntity dummyUserObject = new UserEntity("umakanthpendyala@gmail.com", "12345678", "9440622045", "umakanth", "Male");
			if (userRepository.findByEmail(userData.getEmail()) == null)  {
				storedUserObject = userRepository.save(userData);
			} else return false;
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			wrapper.put("userId", storedUserObject.getUser_id());
			wrapper.put("validUser", true);
			return wrapper;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	@GetMapping("/getUser") 
	public Object getUser() {
		return false;
	}
	
	
	@PutMapping("/updateUser")
	public Boolean updateUser() {
		return false;
	}
	
	
	@DeleteMapping("/deleteUser")
	public Boolean deleteUser() {
		return false;
	}
}

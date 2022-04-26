package com.example.springapp.controllers;


import java.util.HashMap;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.entities.CartEntity;
import com.example.springapp.entities.OrderEntity;
import com.example.springapp.entities.ProductEntity;
import com.example.springapp.entities.UserEntity;
import com.example.springapp.repositorys.CartRepository;
import com.example.springapp.repositorys.OrderRepository;
import com.example.springapp.repositorys.ProductRepository;
import com.example.springapp.repositorys.UserRepository;
import com.example.springapp.utils.OrderUtils;

@RestController
public class OrderController {

	@Autowired UserRepository userRepo;
	@Autowired ProductRepository productRepo;
	@Autowired CartRepository cartRepo;
	@Autowired OrderRepository orderRepo;
	
	
	@Autowired OrderUtils orderUtils;
	
	@CrossOrigin
	@PostMapping("/saveOrder")
	public Object saveOrders(@RequestBody HashMap<String, String> data) {
		return orderUtils.orderAllItems(Long.valueOf(data.get("userId")));
	}

	
	@CrossOrigin
	@PostMapping("/getAllOrders")
	public Object getAllUserOrders(@RequestBody HashMap<String, String> data) {
		return orderUtils.getOrderItems(Long.valueOf(data.get("userId")));
	}
	
	
}

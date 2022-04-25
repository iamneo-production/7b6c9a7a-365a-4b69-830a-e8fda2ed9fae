package com.example.springapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


public class TestController {
	@Autowired UserRepository userRepository;
	@Autowired ProductRepository productRepository;
	@Autowired CartRepository cartRepository;
	
	@GetMapping("/test")
	public Object testRoute() {
		Optional<UserEntity> user = userRepository.findById((long) 9);
		Optional<ProductEntity> product = productRepository.findById((long) 18);
		CartEntity cartObject = new CartEntity(user.get(), product.get(), 5, 10000);
		cartRepository.save(cartObject);
		return false;
	}
}

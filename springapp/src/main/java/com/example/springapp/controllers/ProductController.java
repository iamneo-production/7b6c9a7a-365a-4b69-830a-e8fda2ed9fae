package com.example.springapp.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.BaitAndTackleModified.entities.OrderEntity;
import com.example.BaitAndTackleModified.entities.ProductEntity;
import com.example.BaitAndTackleModified.repositorys.OrderRepository;
import com.example.BaitAndTackleModified.repositorys.ProductRepository;


@RestController
public class ProductController {
    
	@Autowired ProductRepository productRepository;
	@Autowired OrderRepository orderRepository;
	
	@CrossOrigin
	@GetMapping("/getAllProducts")
	public Object returnAllProducts() {
		
		return productRepository.getAllProducts();
	}
	
	@CrossOrigin
	@GetMapping("/admin")
	public Object getAllAdminProducts() {
		
		return productRepository.getAllProducts();
	}
	
	
//	this method should return a string according to SRS document.
//	but I am returning a boolean
	@CrossOrigin
	@PostMapping("/admin/addProduct")
	public Object addProductIntoDatabase(@RequestBody ProductEntity productDataFromAdmin) {
		try {
			System.out.println("I went here");
			productRepository.save(productDataFromAdmin);
			return true;
			
		} catch( Exception e) {
			System.out.println(e);
			return false;
		}
				
	}
	
	@CrossOrigin
	@PostMapping("/admin/saveProduct/{id}")
	public Object updateProductDetails(@RequestBody ProductEntity updatedProductDataFromAdmin, @PathVariable("id") Long id) {
		try {
			Integer updated = (Integer) productRepository.updateProduct(
					updatedProductDataFromAdmin.getProduct_id(), 
					updatedProductDataFromAdmin.getProductName(), 
					updatedProductDataFromAdmin.getProductImageUrl(), 
					updatedProductDataFromAdmin.getPrice(), 
					updatedProductDataFromAdmin.getStockQuantity());
			if (updated >= 1) 
				return true;
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@CrossOrigin
	@GetMapping("/admin/delete/{id}")
	public Object deleteProductFromDatabase(@PathVariable("id") Long id) {
		try {
			Long rowsBeforeDeletion = productRepository.count();
			productRepository.deleteProduct(id);
			if (productRepository.count() == rowsBeforeDeletion) return false;
			else return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@GetMapping("/admin/productEdit/{id}")
	public Object returnProductDetails(@PathVariable("id") Long id) {
		try {
			return productRepository.findById(id);
		} catch (Exception e) {
			return "failed to fetch product details";
		}
	}
	
	/*
	 * When added to cart subract quantity from stock
	 * when deleted from cart add quantity to stock*/
	
	@CrossOrigin
	@GetMapping("/admin/orders")
	public Object getAllOrdersForAdmin() {
		try {
			List<OrderEntity> orderedItems = orderRepository.findAll();
			return orderedItems;
		} 
		catch(Exception e) {
			return false;
		}
	}
	
}


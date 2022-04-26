package com.example.springapp.controllers;

import java.util.HashMap;
import java.util.Optional;

import javax.transaction.Transactional;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BaitAndTackleModified.entities.CartEntity;
import com.example.BaitAndTackleModified.entities.ProductEntity;
import com.example.BaitAndTackleModified.entities.UserEntity;
import com.example.BaitAndTackleModified.repositorys.CartRepository;
import com.example.BaitAndTackleModified.repositorys.ProductRepository;
import com.example.BaitAndTackleModified.repositorys.UserRepository;
import com.example.BaitAndTackleModified.utils.CartUtils;


@RestController
public class CartController {

	@Autowired UserRepository userRepo;
	@Autowired ProductRepository productRepo;
	@Autowired CartRepository cartRepo;
	
	@Autowired CartUtils cartUtilities;
	/*
	 * 
	 * Cart Object details
	 *  1. product id
	 *  2. product price
	 *  3. product quantity
	 * */
	@CrossOrigin
	@PostMapping("/home/cart/{id}")
	public Object addItemToCart(@PathVariable("id") Long userId, @RequestBody HashMap<String, Object> cartDetails) {
		Optional<UserEntity> user = userRepo.findById(userId);
		Long productId = Long.parseLong(cartDetails.get("product_id").toString());
		if (user.isPresent()) {
			Optional<ProductEntity> product = productRepo.findById(Long.parseLong(cartDetails.get("product_id").toString()));
			
			if (product.isPresent() && product.get().getStockQuantity() >= (Integer)cartDetails.get("productQuantity")) {
				
				if (cartUtilities.checkIfItemExistsInCart(userId, productId)) {
					List<CartEntity> cartItems = cartRepo.getIfTheProductExistsInUsersCart(userId, productId);
					cartItems.get(0).setQuantity(cartItems.get(0).getQuantity() + 1);
					Integer data = cartRepo.updateCartItem(cartItems.get(0).getQuantity(), cartItems.get(0).getCart_id());
					if (data == 1) return true;
					return false;
				} 
				else {
					CartEntity cartObject = new CartEntity(user.get(), product.get(), (Integer)cartDetails.get("productQuantity"), (Integer)cartDetails.get("price"));
					Object data = cartRepo.save(cartObject);
					if (data != null) return true;
					else return false;
				}
				

			} else {
//				return "product id does not exist or product quantity is less";
				return false;
			}
		} else {
//			return "user id does not exist";
			return false;
		}
	}
	
	@CrossOrigin
	@GetMapping("/cart/addInstance/{id}")
	public Object addItemInstance(@PathVariable("id") Long cartId) {
		Optional<CartEntity> cartItem = cartRepo.findById(cartId);
		if (cartItem.isPresent()) {
			Integer rowsUpdated = cartRepo.updateCartItem(cartItem.get().getQuantity() + 1, cartId);
			if (rowsUpdated == 1) {
				System.out.println(cartRepo.getAllCartElementsOfAUser(cartItem.get().getUser().getUser_id()).get(0).getQuantity());
				return cartRepo.getAllCartElementsOfAUser(cartItem.get().getUser().getUser_id());
			} else return false;
			
		} else {
			return false;
		}
	}
	
	@CrossOrigin
	@GetMapping("/cart/deleteInstance/{id}")
	public Object deleteItemInstance(@PathVariable("id") Long cartId) {
		Optional<CartEntity> cartItem = cartRepo.findById(cartId);
		if (cartItem.isPresent() && cartItem.get().getQuantity() > 0) {
			Integer rowsUpdated = cartRepo.updateCartItem(cartItem.get().getQuantity() - 1, cartId);
			if (rowsUpdated == 1) {
				return cartRepo.getAllCartElementsOfAUser(cartItem.get().getUser().getUser_id());
			} else return false;
			
		} else {
			return false;
		}
	}
	
	
	
	@CrossOrigin
	@GetMapping("/cart/{id}")
	public Object getCartItems(@PathVariable("id") Long userId) {
		return cartRepo.getAllCartElementsOfAUser(userId);
	}
	
	
	@PostMapping("/home/{id}")
	public Object updateCartItems(@PathVariable("id") Long userId, @RequestBody HashMap<String, Object> cartDetails) {
		
		return true;
	}
	
	@CrossOrigin
	@DeleteMapping("/cart/delete")
	public Object deleteCartItem(@RequestBody HashMap<String, String> data) {
		Optional<UserEntity> user = userRepo.findById(Long.valueOf(data.get("userId")).longValue());
		if (user.isPresent()) {
			Integer rowsEffected = (Integer) cartRepo.deleteCartItemBasedOnUserIdAndCartId(user.get().getUser_id(), Long.valueOf(data.get("cartId")).longValue()); 
			if (rowsEffected == 1) return cartRepo.getAllCartElementsOfAUser(Long.valueOf(data.get("userId")).longValue());
			System.out.println("rowseffected " + rowsEffected);
			return cartRepo.getAllCartElementsOfAUser(Long.valueOf(data.get("userId")).longValue());
			
		}
		return cartRepo.getAllCartElementsOfAUser(Long.valueOf(data.get("userId")).longValue());
	}
	
	@CrossOrigin
	@PostMapping("/resetCart")
	public Object resetCartItems(@RequestBody HashMap<String, String> data) {
		try {
			Optional<UserEntity> user = userRepo.findById(Long.valueOf(data.get("userId")).longValue());
			if (user.isPresent()) {
				List<CartEntity> cartItems = cartRepo.getAllCartElementsOfAUser(user.get().getUser_id());
				for (int i = 0; i < cartItems.size(); i++) {
					if (cartItems.get(i).getQuantity() > cartItems.get(i).getProduct().getStockQuantity()) {
						Optional<ProductEntity> product = productRepo.findById(cartItems.get(i).getProduct().getProduct_id());
						Integer newCartQuantity = cartItems.get(i).getProduct().getStockQuantity();
						cartRepo.updateCartItem(newCartQuantity, cartItems.get(i).getCart_id());
						
					}
				}
			} 
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}

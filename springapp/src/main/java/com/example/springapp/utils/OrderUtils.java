package com.example.springapp.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderUtils {
	@Autowired UserRepository userRepo;
	@Autowired ProductRepository productRepo;
	@Autowired CartRepository cartRepo;
	@Autowired OrderRepository orderRepo;
	
	public OrderUtils() {}
	
	public Object getOrderItems(Long userId) {
		try {
			return orderRepo.findByUserId(userId);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Object orderAllItems(Long userId) {
		try {
			Optional<UserEntity> user = userRepo.findById(Long.valueOf(userId));
			if (user.isEmpty()) return false;
			List<CartEntity> cartItems = cartRepo.getAllCartElementsOfAUser(userId);
			if (cartItems.size() > 0) {
				for (int i = 0; i < cartItems.size(); i++) {
					if (cartItems.get(i).getQuantity() > cartItems.get(i).getProduct().getStockQuantity()) return false;
				}
				for (int i = 0; i < cartItems.size(); i++) {
					Integer quantityOrdered = cartItems.get(i).getQuantity();
					Integer newStock = cartItems.get(i).getProduct().getStockQuantity() - quantityOrdered;
					Integer rowsUpdated;
					rowsUpdated = (Integer)cartRepo.deleteCartItemBasedOnUserIdAndCartId(user.get().getUser_id(), cartItems.get(i).getCart_id());
					rowsUpdated = productRepo.updateStockQuantity(cartItems.get(i).getProduct().getProduct_id(), newStock);
					OrderEntity newOrder = new OrderEntity(cartItems.get(i).getUser(), 
							cartItems.get(i).getProduct(), 
							cartItems.get(i).getQuantity(), 
							cartItems.get(i).getQuantity() * cartItems.get(i).getPrice(), 
							"order placed", 
							cartItems.get(i).getPrice());
					orderRepo.save(newOrder);
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
}

package com.example.springapp.repositorys;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.springapp.entities.CartEntity;
import com.example.springapp.entities.UserEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
	
	@Query(value = "SELECT * FROM cart where user_id = ?1", nativeQuery = true)
	public List<CartEntity> getAllCartElementsOfAUser(Long userId); 
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart where user_id = ?1 and cart_id = ?2", nativeQuery = true)
	public Object deleteCartItemBasedOnUserIdAndCartId(Long userId, Long cartId);
	
	
	@Query(value = "SELECT * FROM cart where user_id = ?1 and product_id = ?2", nativeQuery = true)
	public List<CartEntity> getIfTheProductExistsInUsersCart(Long userId, Long productId);
	
	
//	public Integer updateCartItem(Long cartId)
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE cart set quantity = ?1 where cart_id = ?2", nativeQuery = true)
	public Integer updateCartItem(Integer quantity, Long cartId);
}

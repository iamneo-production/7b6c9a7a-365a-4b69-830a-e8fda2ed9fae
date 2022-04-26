package com.example.springapp.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springapp.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>  {
	
	@Query(value = "SELECT * FROM orders where user_id = ?1", nativeQuery = true)
	public List<OrderEntity> findByUserId(Long userId);
}

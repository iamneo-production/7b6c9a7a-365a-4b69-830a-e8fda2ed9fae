package com.example.springapp.repositorys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.springapp.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	@Query(value = "SELECT * FROM product", nativeQuery = true)
	public List<ProductEntity> getAllProducts();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "DELETE FROM product WHERE product_id = ?1", nativeQuery = true)
	public void deleteProduct(Long product_id);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product SET product_name = ?2 , product_image_url = ?3, stock_quantity = ?5, product_price = ?4"
			+ " WHERE product_id = ?1 ", nativeQuery = true)
	public Object updateProduct(Long product_id, String productName, String productImageUrl, Integer productPrice, Integer productQuantity);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product SET stock_quantity = ?2 WHERE product_id = ?1", nativeQuery = true)
	public Integer updateStockQuantity(Long product_id, Integer newStockValue); 
	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM product where product_id = ?1", nativeQuery = true)
	public Integer deleteProductById(Long product_id);
}

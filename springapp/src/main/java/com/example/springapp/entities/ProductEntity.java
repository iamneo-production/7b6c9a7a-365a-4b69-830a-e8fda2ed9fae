package com.example.springapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	
	public ProductEntity() {}
	
	public ProductEntity(Long product_id, String productName, Integer price, String productImageUrl,
	Integer productQuantity) 
	{
		this.product_id = product_id;
		this.productName = productName;
		this.price = price;
		this.productImageUrl = productImageUrl;
		this.stockQuantity = productQuantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@Column(name = "product_price", nullable = false)
	private Integer price; 
	
	@Column(name = "product_image_url", nullable = false)
	private String productImageUrl;
	
	@Column(name = "stock_quantity", nullable = false)
	private Integer stockQuantity;

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public Integer getStockQuantity() 
	{
		return stockQuantity;
	}

	public void setStockQuantity(Integer productQuantity) 
	{
		this.stockQuantity = productQuantity;
	}

	
}
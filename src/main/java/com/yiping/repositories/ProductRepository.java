package com.yiping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiping.product.model.Product100;

public interface ProductRepository extends JpaRepository<Product100, Integer>{
	public Product100 findByProductName(String productName);
}

package com.yiping.product.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.yiping.product.model.Product100;

public interface ProductDao {
	public void addProduct(Product100 product100);
	public void modifyProduct(Product100 product100);
	public void deleteProductById(int product100Id);
	public Product100 findProductById(int product100Id);
	public Product100 findProductByProductName(String productName);
	public List<Product100> findProductAll();

}

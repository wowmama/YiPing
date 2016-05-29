package com.yiping.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yiping.product.model.Product100;
import com.yiping.product.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	/*
	 	public void addProduct(Product100 product100);
		public void modifyProduct(Product100 product100);
		public void deleteProductById(int product100Id);
		public Product100 findProductById(int product100Id);
		public Product100 findProductByProductName(String productName);
		public List<Product100> findProductAll();
	 */
	
	@RequestMapping(value="/product/", method = RequestMethod.POST, produces = "application/json")
	public void addProduct(@RequestBody Product100 product100){
		productService.addProduct(product100);
	}
	
	@RequestMapping(value="/product/", method = RequestMethod.PUT, produces = "application/json")
	public void modifyProduct(@RequestBody Product100 product100){
		productService.modifyProduct(product100);
	}
	
	@RequestMapping(value="/product/{product100Id}", method = RequestMethod.GET, produces = "application/json")
	public Product100 findProductById(@PathVariable("product100Id") int product100Id){
		return productService.findProductById(product100Id);
	}
	
	@RequestMapping(value="/product/productName:{productName}", method = RequestMethod.GET, produces = "application/json")
	public Product100 findProductByProductName(@PathVariable("productName") String productName){
		return productService.findProductByProductName(productName)	;
	}
	
	@RequestMapping(value="/products/", method = RequestMethod.GET, produces = "application/json")
	public List<Product100> findProductAll(){
		return productService.findProductAll();
	}
}

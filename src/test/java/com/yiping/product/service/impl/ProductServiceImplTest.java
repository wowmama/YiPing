package com.yiping.product.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yiping.customer.service.CustomerService;
import com.yiping.order.service.OrderService;
import com.yiping.product.model.Product100;
import com.yiping.product.service.ProductService;
import com.yiping.sys.service.SysStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/unitTest-context.xml"})
public class ProductServiceImplTest{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private ProductService producService;
	
	/*
	 * public void addProduct(Product100 product100);
		public void modifyProduct(Product100 product100);
		public void deleteProductById(int product100Id);
		public Product100 findProductById(int product100Id);
		public List<Product100> findProductAll();
	 */
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddProduct(){
		Product100 product100 = new Product100();
		product100.setProductName("New Product");
		product100.setProductDetail("New Product Detail");
		product100.setProductPrice(100);
		producService.addProduct(product100);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testModifyProduct(){
		Product100 product100 = producService.findProductById(1);
		entityManager.detach(product100);
		
		product100.setProductName("new Name");
		producService.modifyProduct(product100);
	}
	
	
	
}

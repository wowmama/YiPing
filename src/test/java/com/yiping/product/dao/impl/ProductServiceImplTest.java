package com.yiping.product.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yiping.customer.dao.CustomerDao;
import com.yiping.customer.model.Customer100;
import com.yiping.order.dao.OrderDao;
import com.yiping.order.model.Order100;
import com.yiping.order.model.Order101;
import com.yiping.product.dao.ProductDao;
import com.yiping.product.model.Product100;
import com.yiping.sys.dao.SysStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unitTest-context.xml"})
public class ProductServiceImplTest{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerDao customerService;
	@Autowired
	private OrderDao orderService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private ProductDao producService;
	
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

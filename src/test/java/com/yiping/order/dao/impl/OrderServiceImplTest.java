package com.yiping.order.dao.impl;

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
import com.yiping.sys.model.SysStatus100;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unitTest-context.xml"})
public class OrderServiceImplTest{
	
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
	  	public void addOrder(Order100 order100);
		public void modifyOrder(Order100 order100);
		public void deletetOrderById(int order100Id);
		public Order100 findOrderById(int order100Id);
		public List<Order100> findOrderByCustomerId(int cutomer100Id);
		public List<Order100> findOrderByCustomerPhone(String cusPhone);
		public List<Order100> findOrderByCustomerName(String cusNmame);
		public List<Order100> findOrderAll();
		public List<Order100> findOrderByMultiCondition(Order100 whereCondition);
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testAddOrder(){
		int customer100Id = 1;
		int orderStatusNum = 2;
		int productStatusNum = 4;
		Customer100 customer100 = customerService.findCustomerById(customer100Id);
		Product100 product1 = producService.findProductById(1);
		Product100 product2 = producService.findProductById(2);
		SysStatus100 orderStatus = sysStatusService.findSysStatusById(orderStatusNum);
		SysStatus100 productStatus = sysStatusService.findSysStatusById(productStatusNum);
		entityManager.detach(customer100);
		entityManager.detach(orderStatus);
		entityManager.detach(productStatus);
		entityManager.detach(product1);
		entityManager.detach(product2);
		
		
		Order100 order100 = new Order100();
		order100.setCustomer100(customer100);
		order100.setPickupTime(new Date());
		order100.setCreateTime(new Date());
		order100.setOrderStatus(orderStatus);
		
		Order101 order101 = new Order101();
		order101.setProduct100(product1);
		order101.setProductStatus(productStatus);
		order101.setProductNum(10);
		order100.addOrderDetail(order101);
		order101 = new Order101();
		order101.setProduct100(product2);
		order101.setProductStatus(productStatus);
		order100.addOrderDetail(order101);
		order101.setProductNum(10);
		
		orderService.addOrder(order100);
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testModifyOrder(){
		int productStatusNum = 4;
		Order100 order100 = orderService.findOrderById(1);
		Product100 product3 = producService.findProductById(3);
		SysStatus100 productStatus = sysStatusService.findSysStatusById(productStatusNum);
		entityManager.detach(order100);
		entityManager.detach(product3);
		entityManager.detach(productStatus);
		order100.setOrderStatus(sysStatusService.findSysStatusById(2));
		
		Order101 order101 = new Order101();
		order101.setProduct100(product3);
		order101.setProductStatus(productStatus);
		order101.setProductNum(10);
		order100.addOrderDetail(order101);
		
		for(Order101 orderDetail : order100.getOrder101s()){
			orderDetail.setProductStatus(productStatus);
		}
		
		orderService.modifyOrder(order100);
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindOrderByCustomerId(){
		List<Order100> list = orderService.findOrderByCustomerId(1);
		for(Order100 order100 : list){
			System.out.println(order100.getCustomer100().getCusName());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindOrderByCustomerPhone(){
		List<Order100> list = orderService.findOrderByCustomerPhone("0955772867");
		for(Order100 order100 : list){
			System.out.println(order100.getCustomer100().getCusName());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindOrderByCustomerName(){
		List<Order100> list = orderService.findOrderByCustomerName("oy");
		for(Order100 order100 : list){
			System.out.println(order100.getCustomer100().getCusName());
		}
	}
	

}

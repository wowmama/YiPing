package com.yiping.order.service.impl;

import java.awt.SystemColor;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yiping.customer.model.Customer100;
import com.yiping.customer.service.CustomerService;
import com.yiping.order.model.Order100;
import com.yiping.order.model.Order101;
import com.yiping.order.service.OrderService;
import com.yiping.product.model.Product100;
import com.yiping.product.service.ProductService;
import com.yiping.sys.constant.SysStatusConstant;
import com.yiping.sys.model.SysStatus100;
import com.yiping.sys.service.SysStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/unitTest-context.xml"})
public class OrderServiceImplTest{
	
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
	  	public void addOrder(Order100 order100);
		public void modifyOrder(Order100 order100);
		public void modifyOrderStatusToShipping(Integer order100Id);
		public void modifyOrderStatusToNoShipping(Integer order100Id);
		public void deletetOrderById(Integer order100Id);
		public Order100 findOrderById(Integer order100Id);
		public List<Order100> findOrderByCustomerId(Integer customer100Id);
		public List<Order100> findOrderByCustomerPhone(String cusPhone);
		public List<Order100> findOrderByCustomerName(String cusNmame);
		public List<Order100> findOrderAll();
		public List<Order100> findOrderByMultiCondition(Order100 whereCondition);
		public List<Order100> findOrderByConditions(Map<String,Object> whereCondition);
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testAddOrder(){
		int customer100Id = 1;
		int orderStatusNum = 2;
		int productStatusNum = 4;
		Customer100 customer100 = new Customer100();
		customer100.setCustomer100Id(1);
		Product100 product1 = new Product100();
		product1.setProduct100Id(1);
		Product100 product2 = new Product100();
		product2.setProduct100Id(2);
		SysStatus100 orderStatus = new SysStatus100();
		orderStatus.setSysStatus100Id(SysStatusConstant.ORDER_STATUS_NO_SHIPPING);
		SysStatus100 productStatus = new SysStatus100();
		productStatus.setSysStatus100Id(SysStatusConstant.PRODUCT_STATUS_HOT);
		
		Order100 order100 = new Order100();
		order100.setCustomer100(customer100);
		order100.setPickupTime(new Date());
		order100.setCreateTime(new Date());
		order100.setDeposit(0);
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

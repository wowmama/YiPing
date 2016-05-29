package com.yiping.customer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/unitTest-context.xml"})
public class CustomerServiceImplTest{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerService customerService;
	
	/*
	   	public void addCustomer(Customer100 customer100);
		public void modifyCustomer(Customer100 customer100);
		public void deleteCustomerById(Integer customer100Id);
		public Customer100 findCustomerById(Integer customer100Id);
		public List<Customer100> findCustomerAll();
		
	 */
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddCustomer(){
		Customer100 customer100 = new Customer100();
		customer100.setCusName("Kevin");
		customer100.setCusPhoneNum("0975689547");
		customer100.setCusSex(1);
		customerService.addCustomer(customer100);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testModifyCustomer(){
		Customer100 customer100 = customerService.findCustomerById(1);
		
		entityManager.detach(customer100);
		
		customer100.setCusName("test");
		customer100.setCusPhoneNum("555");
		customer100.setCusSex(0);
		customerService.modifyCustomer(customer100);
	}
	
	@Test
	@Transactional
	public void testFindCustomerById(){
		int customer100Id = 1;
		Customer100 customer100 = customerService.findCustomerById(customer100Id);
		assertEquals(customer100Id, customer100.getCustomer100Id());
		System.out.println("CusName : " + customer100.getCusName() + ", Phone : " + customer100.getCusPhoneNum());
	}
	
	@Test
	@Transactional
	public void testFindCustomerAll(){
		List<Customer100> customer100List = customerService.findCustomerAll();
		for(Customer100 customer100 : customer100List){
			System.out.println("CusName : " + customer100.getCusName() + ", Phone : " + customer100.getCusPhoneNum());
			for(Order100 order100 : customer100.getOrder100s()){
				System.out.println("OrderId : " + order100.getOrder100Id());
			}
		}
	}

}

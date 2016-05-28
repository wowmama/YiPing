package com.yiping.customer.dao.impl;

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

import com.yiping.customer.dao.CustomerDao;
import com.yiping.customer.model.Customer100;
import com.yiping.order.model.Order100;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unitTest-context.xml"})
public class CustomerServiceImplTest{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerDao customerDao;
	
	/*
	   	public void addCustomer(Customer100 customer100);
		public void modifyCustomer(Customer100 customer100);
		public void deleteCustomerById(int customer100Id);
		public Customer100 findCustomerById(int customer100Id);
		public List<Customer100> findCustomerByMultiCondition(Customer100 whereCondition);
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
		customerDao.addCustomer(customer100);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testModifyCustomer(){
		Customer100 customer100 = customerDao.findCustomerById(1);
		
		entityManager.detach(customer100);
		
		customer100.setCusName("test");
		customer100.setCusPhoneNum("555");
		customer100.setCusSex(0);
		customerDao.modifyCustomer(customer100);
	}
	
	@Test
	@Transactional
	public void testFindCustomerById(){
		int customer100Id = 1;
		Customer100 customer100 = customerDao.findCustomerById(customer100Id);
		assertEquals(customer100Id, customer100.getCustomer100Id());
		System.out.println("CusName : " + customer100.getCusName() + ", Phone : " + customer100.getCusPhoneNum());
	}
	
	@Test
	@Transactional
	public void testFindCustomerAll(){
		List<Customer100> customer100List = customerDao.findCustomerAll();
		for(Customer100 customer100 : customer100List){
			System.out.println("CusName : " + customer100.getCusName() + ", Phone : " + customer100.getCusPhoneNum());
			for(Order100 order100 : customer100.getOrder100s()){
				System.out.println("OrderId : " + order100.getOrder100Id());
			}
		}
	}

	
	
	@Test
//	@Transactional
	public void testFindCustomerByMultiCondition() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String cusName = "y";
		String cusPhoneNum = "72";
		Customer100 whereCondition = new Customer100();
		whereCondition.setCusName(cusName);
		whereCondition.setCusPhoneNum(cusPhoneNum);
		List<Customer100> customer100List = customerDao.findCustomerByMultiCondition(whereCondition);
		for(Customer100 customer100 :customer100List){
			assertTrue(customer100.getCusName().contains(cusName));
			assertTrue(customer100.getCusPhoneNum().contains(cusPhoneNum));
		}
		
	}
}

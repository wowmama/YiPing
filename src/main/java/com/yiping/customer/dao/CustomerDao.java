package com.yiping.customer.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yiping.customer.model.Customer100;
import com.yiping.dao.Dao;

public interface CustomerDao {
	public void addCustomer(Customer100 customer100);
	public void modifyCustomer(Customer100 customer100);
	public void deleteCustomerById(int customer100Id);
	public Customer100 findCustomerById(int customer100Id);
	public List<Customer100> findCustomerAll();
//	public List<Customer100> findCustomerByMultiCondition(Customer100 entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException ;
}

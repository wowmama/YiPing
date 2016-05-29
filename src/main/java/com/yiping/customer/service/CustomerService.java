package com.yiping.customer.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yiping.customer.model.Customer100;
import com.yiping.dao.Dao;

public interface CustomerService {
	public void addCustomer(Customer100 customer100);
	public void modifyCustomer(Customer100 customer100);
	public void deleteCustomerById(Integer customer100Id);
	public Customer100 findCustomerById(Integer customer100Id);
	public List<Customer100> findCustomerAll();
}

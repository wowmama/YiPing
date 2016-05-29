package com.yiping.customer.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiping.customer.model.Customer100;
import com.yiping.customer.service.CustomerService;
import com.yiping.dao.Dao;
import com.yiping.repositories.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	@Override
	public void addCustomer(Customer100 customer100) {
		customerRepository.save(customer100);
	}

	@Transactional
	@Override
	public void modifyCustomer(Customer100 customer100) {
		customerRepository.save(customer100);
	}

	@Transactional
	@Override
	public void deleteCustomerById(Integer customer100Id) {
		customerRepository.delete(customer100Id);
	}

	@Transactional
	@Override
	public Customer100 findCustomerById(Integer customer100Id) {
		return customerRepository.findOne(customer100Id);
	}

	@Override
	public List<Customer100> findCustomerAll() {
		return customerRepository.findAll();
	}
}

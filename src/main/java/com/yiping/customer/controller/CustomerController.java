package com.yiping.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yiping.customer.model.Customer100;
import com.yiping.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
    public void createUser(@RequestBody Customer100 customer100) {
		customerService.addCustomer(customer100);
    }
	
	@RequestMapping(value = "/customer/", method = RequestMethod.PUT)
    public void modifyCustomer(@RequestBody Customer100 customer100) {
		customerService.modifyCustomer(customer100);
    }
	
	@RequestMapping(value = "/customers/",method = RequestMethod.GET, produces = "application/json")
	public List<Customer100> findCustomerAll() {
		return customerService.findCustomerAll();
	}
	
	@RequestMapping(value = "/customer/{customer100Id}",method = RequestMethod.GET, produces = "application/json")
	public Customer100 findCustomerById(@PathVariable("customer100Id") int customer100Id){
		return customerService.findCustomerById(customer100Id);
	}
}

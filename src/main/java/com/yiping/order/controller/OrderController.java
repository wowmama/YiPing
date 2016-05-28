package com.yiping.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yiping.order.dao.OrderDao;
import com.yiping.order.model.Order100;

@RestController
public class OrderController {
	@Autowired
	private OrderDao orderService;
	
	/*
	  	public void addOrder(Order100 order100);
		public void modifyOrder(Order100 order100);
		public void modifyOrderStatusToShipping(int order100Id);
		public void modifyOrderStatusToNoShipping(int order100Id);
		public void deletetOrderById(int order100Id);
		public Order100 findOrderById(int order100Id);
		public List<Order100> findOrderByCustomerId(int cutomer100Id);
		public List<Order100> findOrderByCustomerPhone(String cusPhone);
		public List<Order100> findOrderByCustomerName(String cusNmame);
		public List<Order100> findOrderAll();
		public List<Order100> findOrderByMultiCondition(Order100 whereCondition);
	 */
	
	@RequestMapping(value="/order/" ,method = RequestMethod.POST, produces = "application/json")
	public void addOrder(@RequestBody Order100 order100){
		orderService.addOrder(order100);
	}
	
	@RequestMapping(value = "/order/",method = RequestMethod.PUT, produces = "application/json")
	public void modifyOrder(@RequestBody Order100 order100){
		orderService.modifyOrder(order100);
	}
	
	@RequestMapping(value = "/order/shipping/{order100Id}",method = RequestMethod.PUT, produces = "application/json")
	public void modifyOrderStatusToShipping(@PathVariable int order100Id){
		orderService.modifyOrderStatusToShipping(order100Id);
	}
	
	@RequestMapping(value = "/order/noShipping/{order100Id}",method = RequestMethod.PUT, produces = "application/json")
	public void modifyOrderStatusToNoShipping(@PathVariable int order100Id){
		orderService.modifyOrderStatusToNoShipping(order100Id);
	}
	
	@RequestMapping(value = "/order/{order100Id}",method = RequestMethod.GET, produces = "application/json")
	public Order100 findOrderById(@PathVariable("order100Id") int order100Id){
		return orderService.findOrderById(order100Id);
	}
	
	@RequestMapping(value = "/orders/cusId:{customer100Id}",method = RequestMethod.GET, produces = "application/json")
	public List<Order100> findOrderByCustomerId(@PathVariable("customer100Id") int customer100Id){
		return orderService.findOrderByCustomerId(customer100Id);
	}
	
	@RequestMapping(value = "/orders/cusPhone:{cusPhone}",method = RequestMethod.GET, produces = "application/json")
	public List<Order100> findOrderByCustomerPhone(@PathVariable("cusPhone") String cusPhone){
		return orderService.findOrderByCustomerPhone(cusPhone);
	}
	
	@RequestMapping(value = "/orders/cusName:{cusName}",method = RequestMethod.GET, produces = "application/json")
	public List<Order100> findOrderByCustomerName(@PathVariable("cusName") String cusName){
		return orderService.findOrderByCustomerName(cusName);
	}
	
	@RequestMapping(value = "/orders/",method = RequestMethod.GET, produces = "application/json")
	public List<Order100> findOrderAll(){
		return orderService.findOrderAll();
	}
	
	@RequestMapping(value = "/orders/searchConditions",method = RequestMethod.GET, produces = "application/json")
	public List<Order100> findOrderByConditions(@RequestBody Map<String,Object> searchConditions){
		return orderService.findOrderByConditions(searchConditions);
	}
	
	
	
}

package com.yiping.order.service;

import java.util.List;
import java.util.Map;

import com.yiping.order.model.Order100;

public interface OrderService {
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
}

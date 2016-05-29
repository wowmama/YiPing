package com.yiping.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiping.dao.GenericDao;
import com.yiping.order.model.Order100;
import com.yiping.order.model.Order101;
import com.yiping.order.service.OrderService;
import com.yiping.repositories.OderRepository;
import com.yiping.repositories.SysStatusRepositroy;
import com.yiping.stock.model.Stock100;
import com.yiping.stock.model.Stock101;
@Service("OrderService")
public class OrderSerivceImpl implements OrderService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SysStatusRepositroy sysStatusRepositroy;
	
	@Autowired
	private OderRepository oderRepository;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	public void addOrder(Order100 order100) {
		oderRepository.save(order100);
	}

	@Override
	public void modifyOrder(Order100 order100) {
		oderRepository.save(order100);
	}

	@Override
	public void deletetOrderById(Integer order100Id) {
		oderRepository.delete(order100Id);
	}

	@Override
	public Order100 findOrderById(Integer order100Id) {
		return oderRepository.findOne(order100Id);
	}

	@Override
	public List<Order100> findOrderByMultiCondition(Order100 whereCondition) {
		return genericDao.findEntityByMultiCondition(whereCondition);
	}

	@Override
	public List<Order100> findOrderByCustomerId(Integer customer100Id) {
		return oderRepository.findByCustomerId(customer100Id);
	}

	@Override
	public List<Order100> findOrderAll() {
		return oderRepository.findAll();
	}

	@Override
	public List<Order100> findOrderByCustomerPhone(String cusPhoneNum) {
		return oderRepository.findByCusPhoneNum(cusPhoneNum);
	}

	@Override
	public List<Order100> findOrderByCustomerName(String cusName) {
		return oderRepository.findByCusName(cusName);
	}

	@Override
	public void modifyOrderStatusToShipping(Integer order100Id) {
		Order100 order100 = oderRepository.getOne(order100Id);
		order100.setOrderStatus(sysStatusRepositroy.findByStatusName("SHIPPING"));
		oderRepository.save(order100);
		
		Stock100 stock100 = new Stock100();
		stock100.setCreateTime(new Date());
		stock100.setStockType(sysStatusRepositroy.findByStatusName("EXPORT"));
		stock100.setOrder100(order100);
		for(Order101 order101 : order100.getOrder101s()){
			Stock101 stock101 = new Stock101();
			stock101.setProduct100(order101.getProduct100());
			stock101.setProductNum(order101.getProductNum());
			stock100.addStockDetail(stock101);
		}
		entityManager.persist(stock100);
		
	}

	@Override
	public void modifyOrderStatusToNoShipping(Integer order100Id) {
		Order100 order100 = oderRepository.findOne(order100Id);
		order100.setOrderStatus(sysStatusRepositroy.findByStatusName("NO_SHIPPING"));
		oderRepository.save(order100);
		for(Stock100 s : order100.getStock100s()){
			entityManager.remove(s);
		}
	}

	@Override
	public List<Order100> findOrderByConditions(
			Map<String, Object> whereCondition) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order100> critieria = cb.createQuery(Order100.class);
		Root<Order100> root = critieria.from(Order100.class);
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		if(whereCondition.containsKey("cusName")){
			conditions.add(cb.equal(root.join("customer100").get("cusName"),whereCondition.get("cusName")));
		}
		
		if(whereCondition.containsKey("cusPhoneNum")){
			conditions.add(cb.equal(root.join("customer100").get("cusPhoneNum"),whereCondition.get("cusPhoneNum")));
		}
		
		if(whereCondition.containsKey("pickupTime")){
			conditions.add(cb.equal(root.get("pickupTime"),whereCondition.get("pickupTime")));
		}
		critieria.where(conditions.toArray(new Predicate[conditions.size()]));
		return entityManager.createQuery(critieria).getResultList();
	}

}

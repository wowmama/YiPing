package com.yiping.order.dao.impl;

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

import com.yiping.order.dao.OrderDao;
import com.yiping.order.model.Order100;
import com.yiping.order.model.Order101;
import com.yiping.stock.model.Stock100;
import com.yiping.stock.model.Stock101;
import com.yiping.sys.dao.SysStatusService;
@Service("OrderService")
public class OrderDaoImpl implements OrderDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SysStatusService sysStatusServie;
	
	@Override
	public void addOrder(Order100 order100) {
		entityManager.persist(order100);
	}

	@Override
	public void modifyOrder(Order100 order100) {
		entityManager.merge(order100);
	}

	@Override
	public void deletetOrderById(int order100Id) {
		Order100 order100 = entityManager.find(Order100.class, order100Id);
		entityManager.remove(order100);
	}

	@Override
	public Order100 findOrderById(int order100Id) {
		Order100 order100 = entityManager.find(Order100.class, order100Id);
		return order100;
	}

	@Override
	public List<Order100> findOrderByMultiCondition(Order100 whereCondition) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order100> critieria = cb.createQuery(Order100.class);
		Root<Order100> root = critieria.from(Order100.class);
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		if(whereCondition.getCustomer100() != null){
			conditions.add(cb.equal(root.get("customer100"),whereCondition.getCustomer100()));
		}
		if(whereCondition.getOrderStatus() != null){
			conditions.add(cb.equal(root.get("orderStatus"),whereCondition.getOrderStatus()));
		}
		
		if(whereCondition.getCreateTime() != null){
			conditions.add(cb.greaterThanOrEqualTo(root.get("createTime"), whereCondition.getCreateTime()));
		}
		if(whereCondition.getPickupTime() != null){
			conditions.add(cb.greaterThanOrEqualTo(root.get("pickupTime"), whereCondition.getPickupTime()));
		}
		critieria.where(conditions.toArray(new Predicate[conditions.size()]));
		return entityManager.createQuery(critieria).getResultList();
	}

	@Override
	public List<Order100> findOrderByCustomerId(int customer100Id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order100> critieria = cb.createQuery(Order100.class);
		Root<Order100> root = critieria.from(Order100.class);
		critieria.where(cb.equal(root.join("customer100").get("customer100Id"), customer100Id));
		return entityManager.createQuery(critieria).getResultList();
	}

	@Override
	public List<Order100> findOrderAll() {
		return entityManager.createQuery("select o from Order100 o").getResultList();
	}

	@Override
	public List<Order100> findOrderByCustomerPhone(String cusPhone) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order100> critieria = cb.createQuery(Order100.class);
		Root<Order100> root = critieria.from(Order100.class);
		critieria.where(cb.equal(root.join("customer100").get("cusPhoneNum"), cusPhone));
		return entityManager.createQuery(critieria).getResultList();
	}

	@Override
	public List<Order100> findOrderByCustomerName(String cusNmame) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order100> critieria = cb.createQuery(Order100.class);
		Root<Order100> root = critieria.from(Order100.class);
		critieria.where(cb.like(root.join("customer100").get("cusName"), "%" + cusNmame + "%"));
		return entityManager.createQuery(critieria).getResultList();
	}

	@Override
	public void modifyOrderStatusToShipping(int order100Id) {
		Order100 order100 = entityManager.find(Order100.class, order100Id);
		order100.setOrderStatus(sysStatusServie.findSysStatusByStatusName("SHIPPING"));
		entityManager.persist(order100);
		
		Stock100 stock100 = new Stock100();
		stock100.setCreateTime(new Date());
		stock100.setStockType(sysStatusServie.findSysStatusByStatusName("EXPORT"));
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
	public void modifyOrderStatusToNoShipping(int order100Id) {
		Order100 order100 = entityManager.find(Order100.class, order100Id);
		order100.setOrderStatus(sysStatusServie.findSysStatusByStatusName("NO_SHIPPING"));
		entityManager.persist(order100);
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

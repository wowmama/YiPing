package com.yiping.customer.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiping.customer.dao.CustomerDao;
import com.yiping.customer.model.Customer100;
import com.yiping.dao.Dao;

@Service("customerDao")
public class CustomerDaoImpl implements CustomerDao,Dao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Dao jpaDao;
	
	@Transactional
	@Override
	public void addCustomer(Customer100 customer100) {
		entityManager.persist(customer100);
	}

	@Transactional
	@Override
	public void modifyCustomer(Customer100 customer100) {
		entityManager.merge(customer100);
	}

	@Transactional
	@Override
	public void deleteCustomerById(int customer100Id) {
		Customer100 customer100 = entityManager.find(Customer100.class,
				customer100Id);
		entityManager.remove(customer100);
	}

	@Transactional
	@Override
	public Customer100 findCustomerById(int customer100Id) {
		Customer100 customer100 = entityManager.find(Customer100.class,
				customer100Id);
		return customer100;
	}

//	@Transactional
//	@Override
//	public List<Customer100> findCustomerByMultiCondition(
//			Customer100 whereCondition) {
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Customer100> criteria = cb.createQuery(Customer100.class);
//		Root<Customer100> root = criteria.from(Customer100.class);
//		List<Predicate> conditions = new ArrayList<Predicate>();
//
//		if (whereCondition.getCusName() != null
//				&& whereCondition.getCusName().trim().length() != 0) {
//			conditions.add(cb.like(root.get("cusName"),
//					"%" + whereCondition.getCusName() + "%"));
//		}
//		if (whereCondition.getCusPhoneNum() != null
//				&& whereCondition.getCusPhoneNum().trim().length() != 0) {
//			conditions.add(cb.like(root.get("cusPhoneNum"), "%"
//					+ whereCondition.getCusPhoneNum() + "%"));
//		}
//
//		if (whereCondition.getCusSex() != null) {
//			conditions.add(cb.equal(root.get("cusSex"),
//					whereCondition.getCusSex()));
//		}
//
//		criteria.where(conditions.toArray(new Predicate[conditions.size()]));
//		List<Customer100> c = entityManager.createQuery(criteria)
//				.getResultList();
//		return c;
//	}

	@Override
	public List<Customer100> findCustomerAll() {
		return entityManager.createQuery("select c from Customer100 c")
				.getResultList();
	}

	@Override
	public <E> void persist(E entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <E> void remove(E entity) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public <E> List<E> findCustomerByMultiCondition(E entity)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <E, K> E findById(K id, Class<E> cls) {
		// TODO Auto-generated method stub
		return null;
	}
}

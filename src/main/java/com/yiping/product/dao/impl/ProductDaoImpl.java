package com.yiping.product.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.yiping.product.dao.ProductDao;
import com.yiping.product.model.Product100;

@Service("ProductService")
public class ProductDaoImpl implements ProductDao {
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addProduct(Product100 product100) {
		entityManager.persist(product100);
	}

	@Override
	public void modifyProduct(Product100 product100) {
		entityManager.merge(product100);
	}

	@Override
	public void deleteProductById(int product100Id) {
		Product100 product100 = entityManager.find(Product100.class, product100Id);
		entityManager.remove(product100);
	}

	@Override
	public Product100 findProductById(int product100Id) {
		return entityManager.find(Product100.class, product100Id);
	}

	@Override
	public List<Product100> findProductAll() {
		return entityManager.createQuery("select p from Product100 p").getResultList();
	}

	@Override
	public Product100 findProductByProductName(String productName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product100> criteriaQuery = cb.createQuery(Product100.class);
		Root<Product100> root = criteriaQuery.from(Product100.class);
		criteriaQuery.where(cb.equal(root.get("productName"), productName));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}

package com.yiping.product.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiping.product.model.Product100;
import com.yiping.product.service.ProductService;
import com.yiping.repositories.ProductRepository;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Product100 product100) {
		productRepository.save(product100);
	}

	@Override
	public void modifyProduct(Product100 product100) {
		productRepository.save(product100);
	}

	@Override
	public void deleteProductById(int product100Id) {
		productRepository.delete(product100Id);
	}

	@Override
	public Product100 findProductById(int product100Id) {
		return productRepository.getOne(product100Id);
	}

	@Override
	public List<Product100> findProductAll() {
		return productRepository.findAll();
	}

	@Override
	public Product100 findProductByProductName(String productName) {
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Product100> criteriaQuery = cb.createQuery(Product100.class);
//		Root<Product100> root = criteriaQuery.from(Product100.class);
//		criteriaQuery.where(cb.equal(root.get("productName"), productName));
//		return entityManager.createQuery(criteriaQuery).getSingleResult();
		return productRepository.findByProductName(productName);
	}

}

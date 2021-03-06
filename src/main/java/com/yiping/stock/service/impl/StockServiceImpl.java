package com.yiping.stock.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiping.dao.GenericDao;
import com.yiping.repositories.StockRepository;
import com.yiping.stock.model.Stock100;
import com.yiping.stock.model.Stock101;
import com.yiping.stock.model.Stock200;
import com.yiping.stock.service.StockService;
import com.yiping.stock.service.StockService;

@Service("StockService")
public class StockServiceImpl implements StockService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private StockRepository stockRepository;
	
	
	@Override
	public void addStock(Stock100 stock100) {
		stockRepository.save(stock100);
	}

	@Override
	public void modifyStock(Stock100 stock100) {
		stockRepository.save(stock100);
	}

	@Override
	public void deleteStockById(int stock100Id) {
		stockRepository.delete(stock100Id);
	}

	@Override
	public Stock100 findStockById(int stock100Id) {
		return stockRepository.findOne(stock100Id);
	}

	@Override
	public List<Stock100> findStockByMultiCondition(Stock100 whereCondition) {
		return genericDao.findEntityByMultiCondition(whereCondition);
	}

	@Override
	public List<Stock100> findStockAll() {
		return stockRepository.findAll();
	}

	@Override
	public List<Stock101> findStockDetailByProductId(int product100Id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Stock101> criteria = cb.createQuery(Stock101.class);
		Root<Stock101> root = criteria.from(Stock101.class);
		criteria.where(cb.equal(root.get("product100"), product100Id));
		return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public List<Stock101> findStockDetailAll() {
		return entityManager.createQuery("select s from stock101 s").getResultList();
	}

	@Override
	public int getStockDatumNumByProductId(int product100Id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Stock200> criteria = cb.createQuery(Stock200.class);
		Root<Stock200> root = criteria.from(Stock200.class);
		criteria.where(cb.equal(root.get("product100"), product100Id));
		Stock200 stock200 = entityManager.createQuery(criteria).getSingleResult();
		return stock200.getStockNum();
	}
	@Override
	public int getStockNumByProductId(int product100Id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Stock200> stock200Criteria = cb.createQuery(Stock200.class);
		Root<Stock200> stock200Root = stock200Criteria.from(Stock200.class);
		stock200Criteria.where(cb.equal(stock200Root.get("product100"), product100Id));
		Stock200 stock200 = entityManager.createQuery(stock200Criteria).getSingleResult();
		
		CriteriaQuery<Stock101> stock101Criteria = cb.createQuery(Stock101.class);
		Root<Stock101> stock101Root = stock101Criteria.from(Stock101.class);
		stock101Criteria.where(
				cb.greaterThan(stock101Root.join("stock100").get("createTime"),stock200.getUpdatedate()),
				cb.equal(stock101Root.join("product100").get("product100Id"),product100Id));
		
		List<Stock101> stockList = entityManager.createQuery(stock101Criteria).getResultList();
		
		int total = 0;
		for(Stock101 stock101 : stockList){
			if("IMPORT".equals(stock101.getStock100().getStockType().getStatusName())){
				total = total + stock101.getProductNum();
			}else if ("EXPORT".equals(stock101.getStock100().getStockType().getStatusName())){
				total = total - stock101.getProductNum();
			}
		}
		
		return 0;
	}

	@Override
	public void modifyStockDatumNum(Stock200 stock200) {
		entityManager.merge(stock200);
	}


}

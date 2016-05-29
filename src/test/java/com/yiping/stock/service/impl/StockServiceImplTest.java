package com.yiping.stock.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yiping.customer.service.CustomerService;
import com.yiping.order.model.Order100;
import com.yiping.order.model.Order101;
import com.yiping.order.service.OrderService;
import com.yiping.product.service.ProductService;
import com.yiping.stock.model.Stock100;
import com.yiping.stock.model.Stock101;
import com.yiping.stock.service.StockService;
import com.yiping.sys.model.SysStatus100;
import com.yiping.sys.service.SysStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unitTest-context.xml"})
public class StockServiceImplTest{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private ProductService producService;
	@Autowired
	private StockService stockService;
	
	/*
	  	public void addStock(Stock100 stock100);
		public void modifyStock(Stock100 stock100);
		public void deleteStockById(int stock100Id);
		public Stock100 findStockById(int stock100Id);
		public List<Stock100> findStockByMultiCondition(Stock100 whereCondition);
		public List<Stock100> findStockAll();
		
		public List<Stock101> findStockDetailByProductId(int product100Id);
		public List<Stock101> findStockDetailAll();
		public int getStockDatumNumByProductId(int product100Id);
		public void modifyStockDatumNum(Stock200 stock200);
	 */
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void testAddStock(){
		Order100 order100 = orderService.findOrderById(3);
		SysStatus100 stockType = sysStatusService.findSysStatusByStatusName("EXPORT");
		
		entityManager.detach(order100);
		entityManager.detach(stockType);
		
		Stock100 stock100 = new Stock100();
		stock100.setCreateTime(new Date());
		stock100.setOrder100(order100);
		stock100.setStockType(stockType);
		
		for(Order101 order101 : order100.getOrder101s()){
			Stock101 stock101 = new Stock101();
			stock101.setProduct100(order101.getProduct100());
			stock101.setProductNum(order101.getProductNum());
			stock100.addStockDetail(stock101);
		}
		
		stockService.addStock(stock100);
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindStockDetailByProductId(){
		List<Stock101> stockDetailList = stockService.findStockDetailByProductId(2);	
		for(Stock101 stock101 : stockDetailList){
			System.out.println("Stock Type : " + stock101.getStock100().getStockType().getStatusName()
					+ ", Product Name : " + stock101.getProduct100().getProductName()
					+ ", Product Num : " + stock101.getProductNum());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetStockDatumNumByProductId(){
		System.out.println(stockService.getStockDatumNumByProductId(2));
	}
	
	
}

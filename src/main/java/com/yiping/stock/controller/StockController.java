package com.yiping.stock.controller;

import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yiping.stock.model.Stock100;
import com.yiping.stock.model.Stock101;
import com.yiping.stock.service.StockService;

@RestController
public class StockController {
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
	
	@RequestMapping(value="/stock/", method=RequestMethod.POST, produces="application/json")
	public void addStock(@RequestBody Stock100 stock100){
		stockService.addStock(stock100);
	}
	
	@RequestMapping(value="/stock/", method=RequestMethod.PUT, produces="application/json")
	public void modifyStock(@RequestBody Stock100 stock100){
		stockService.modifyStock(stock100);
	}
	
	@RequestMapping(value="/stock/{stock100Id}", method=RequestMethod.DELETE, produces="application/json")
	public void deleteStockById(@PathVariable("stock100Id") int stock100Id){
		stockService.deleteStockById(stock100Id);
	}
	
	@RequestMapping(value="/stock/{stock100Id}", method=RequestMethod.GET, produces="application/json")
	public Stock100 findStockById(@PathVariable("stock100Id") int stock100Id){
		return stockService.findStockById(stock100Id);
	}
	
	@RequestMapping(value="/stocks/", method=RequestMethod.GET, produces="application/json")
	public List<Stock100> findStockAll(){
		return stockService.findStockAll();
	}
	
	@RequestMapping(value="/stockDetails/productId:{product100Id}", method=RequestMethod.GET, produces="application/json")
	public List<Stock101> findStockDetailByProductId(@PathVariable("product100Id") int product100Id){
		return stockService.findStockDetailByProductId(product100Id);
	}
	
	@RequestMapping(value="/stockDetails/", method=RequestMethod.GET, produces="application/json")
	public List<Stock101> findStockDetailAll(){
		return stockService.findStockDetailAll();
	}
}

package com.yiping.stock.service;

import java.util.List;

import com.yiping.stock.model.Stock100;
import com.yiping.stock.model.Stock101;
import com.yiping.stock.model.Stock200;

public interface StockService {
	public void addStock(Stock100 stock100);
	public void modifyStock(Stock100 stock100);
	public void deleteStockById(int stock100Id);
	public Stock100 findStockById(int stock100Id);
	public List<Stock100> findStockByMultiCondition(Stock100 whereCondition);
	public List<Stock100> findStockAll();
	
	public List<Stock101> findStockDetailByProductId(int product100Id);
	public List<Stock101> findStockDetailAll();
	public int getStockDatumNumByProductId(int product100Id);
	public int getStockNumByProductId(int product100Id);
	public void modifyStockDatumNum(Stock200 stock200);
}

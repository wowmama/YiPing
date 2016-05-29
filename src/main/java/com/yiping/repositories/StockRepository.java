package com.yiping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiping.stock.model.Stock100;

public interface StockRepository extends JpaRepository<Stock100, Integer>{

}

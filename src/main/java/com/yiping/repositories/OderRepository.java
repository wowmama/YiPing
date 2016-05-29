package com.yiping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yiping.order.model.Order100;

public interface OderRepository extends JpaRepository<Order100, Integer>{
	@Query("SELECT o FROM Order100 o LEFT JOIN FETCH o.customer100 c WHERE c.customer100Id = :customer100Id" )
	public List<Order100> findByCustomerId(@Param("customer100Id") Integer customer100Id);
	
	@Query("SELECT o FROM Order100 o LEFT JOIN FETCH o.customer100 c WHERE c.cusPhoneNum = :cusPhoneNum" )
	public List<Order100> findByCusPhoneNum(@Param("cusPhoneNum") String cusPhoneNum);
	
	@Query("SELECT o FROM Order100 o LEFT JOIN FETCH o.customer100 c WHERE c.cusName = :cusName" )
	public List<Order100> findByCusName(@Param("cusName") String cusName);

}

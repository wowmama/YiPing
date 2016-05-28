package com.yiping.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yiping.customer.model.Customer100;

public interface Dao <K,E> {
	 public void persist(E entity);
	 public void remove(E entity);
	 public E findById(K id);
     public List<E> findCustomerByMultiCondition(E entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException ;
}

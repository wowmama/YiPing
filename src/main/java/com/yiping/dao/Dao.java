package com.yiping.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yiping.customer.model.Customer100;

public interface Dao{
     public <E> List<E> findEntityByMultiCondition(E entity) ;
}

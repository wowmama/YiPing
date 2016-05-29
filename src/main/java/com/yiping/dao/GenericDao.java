package com.yiping.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.yiping.util.StringUtil;

@Repository("genericDao")
public class GenericDao implements Dao{

	@PersistenceContext
	protected EntityManager entityManager;

	public GenericDao() {
	}	

	@Override
	public <E> List<E> findEntityByMultiCondition(E entity) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteria = (CriteriaQuery<E>) cb.createQuery(entity.getClass());
		Root<E> root = (Root<E>) criteria.from(entity.getClass());
		List<Predicate> conditions = new ArrayList<Predicate>();
		try {
			for(Method method : entity.getClass().getMethods()){
				if(method.isAnnotationPresent(Column.class)){
					Object ret;
					ret = method.invoke(entity, null);
					if(ret != null){
						if(ret instanceof String){
							conditions.add(cb.like(root.get(getAttributeName(method)),"%" + ret + "%"));
						}else{
							conditions.add(cb.equal(root.get(getAttributeName(method)),ret));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		criteria.where(conditions.toArray(new Predicate[conditions.size()]));
		List<E> c = entityManager.createQuery(criteria).getResultList();
		return c;
	}
	
	private String getAttributeName(Method method) {
		Column column = method.getAnnotation(Column.class);
		String columnName = column.name();
		return StringUtil.toCamelCase(columnName);
	}
	
}

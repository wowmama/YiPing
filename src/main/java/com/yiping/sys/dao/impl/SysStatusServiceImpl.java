package com.yiping.sys.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.yiping.sys.dao.SysStatusService;
import com.yiping.sys.model.SysStatus100;

@Service("SysStatusService")
public class SysStatusServiceImpl implements SysStatusService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public SysStatus100 findSysStatusById(int sysStatus100Id) {
		return entityManager.find(SysStatus100.class, sysStatus100Id);
	}

	@Override
	public List<SysStatus100> findSysStatusByColumnName(String clumnName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SysStatus100> criteriaQuery = cb.createQuery(SysStatus100.class);
		Root<SysStatus100> root = criteriaQuery.from(SysStatus100.class);
		criteriaQuery.where(cb.equal(root.get("columnName"), clumnName));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public SysStatus100 findSysStatusByStatusName(String statusName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SysStatus100> criteriaQuery = cb.createQuery(SysStatus100.class);
		Root<SysStatus100> root = criteriaQuery.from(SysStatus100.class);
		criteriaQuery.where(cb.equal(root.get("statusName"), statusName));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}

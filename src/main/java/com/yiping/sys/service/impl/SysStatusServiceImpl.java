package com.yiping.sys.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiping.repositories.SysStatusRepositroy;
import com.yiping.sys.model.SysStatus100;
import com.yiping.sys.service.SysStatusService;

@Service("SysStatusService")
public class SysStatusServiceImpl implements SysStatusService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SysStatusRepositroy sysStatusRepositroy;
	
	@Override
	public SysStatus100 findSysStatusById(int sysStatus100Id) {
		return sysStatusRepositroy.findOne(sysStatus100Id);
	}

	@Override
	public List<SysStatus100> findSysStatusByColumnName(String columnName) {
		return sysStatusRepositroy.findByColumnName(columnName);
	}

	@Override
	public SysStatus100 findSysStatusByStatusName(String statusName) {
		return sysStatusRepositroy.findByStatusName(statusName);
	}

}

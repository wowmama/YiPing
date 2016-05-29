package com.yiping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiping.sys.model.SysStatus100;

public interface SysStatusRepositroy extends JpaRepository<SysStatus100, Integer>{
	public List<SysStatus100> findByColumnName(String columnName);
	public SysStatus100 findByStatusName(String statusName);

}

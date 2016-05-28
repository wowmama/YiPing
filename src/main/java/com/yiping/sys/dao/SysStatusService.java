package com.yiping.sys.dao;

import java.util.List;

import com.yiping.sys.model.SysStatus100;

public interface SysStatusService {
	public SysStatus100 findSysStatusById(int sysStatus100Id);
	public SysStatus100 findSysStatusByStatusName(String statusName);
	public List<SysStatus100> findSysStatusByColumnName(String clumnName);
}

package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.SearchLog;

@Repository("searchlogdao")
public class SearchLogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveSearchLog(SearchLog searchlog){
		this.baseDao.save(searchlog);
	}
	 
	
	public List<SearchLog> getSearchLogByUserId(long userid){
		String sql = "select * from TB_SEARCHLOG where USERID=? ORDER BY SEARCHLOG_ID DESC";
		return this.baseDao.findObjListBySql(sql, SearchLog.class, userid);
	}
	 
	
}

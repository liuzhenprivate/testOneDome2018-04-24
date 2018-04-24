package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.SearchLogDaoImpl;
import com.sinontech.dao.impl.SignlogDaoImpl;
import com.sinontech.modle.SearchLog;
import com.sinontech.modle.Signlog;
import com.sinontech.service.SearchlogService;
import com.sinontech.service.SignlogService;

@Service("searchlogservice")
public class SearchlogServiceImpl implements SearchlogService{

	@Autowired
	SearchLogDaoImpl searchLogDaoImpl;

	@Override
	public void saveSearchLog(SearchLog searchlog) {
		this.searchLogDaoImpl.saveSearchLog(searchlog);
	}

	@Override
	public List<SearchLog> getSearchLogByUserId(long userid) {
		return this.searchLogDaoImpl.getSearchLogByUserId(userid);
	}
 

	  
	
}

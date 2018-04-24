package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.SearchLog;


public interface SearchlogService {
	
	public void saveSearchLog(SearchLog signlog);
	public List<SearchLog> getSearchLogByUserId(long userid);
 
	
}

package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.SearchKeyDaoImpl;
import com.sinontech.modle.SearchKey;
import com.sinontech.service.SearchkeyService;

@Service("searchkeyservice")
public class SearchkeyServiceImpl implements SearchkeyService{

	@Autowired
	SearchKeyDaoImpl searchKeyDaoImpl;

	@Override
	public List<SearchKey> getSearchKey(int pagelimit) {
		return this.searchKeyDaoImpl.getSearchKey(pagelimit);
	}

	 
 

	  
	
}

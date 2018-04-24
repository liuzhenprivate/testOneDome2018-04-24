package com.sinontech.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.SearchKey;
import com.sinontech.pub.page.Page;

@Repository("searchkeydao")
public class SearchKeyDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	public List<SearchKey> getSearchKey(int pagelimit){
//		String sql = "select * from TB_SEARCHKEY where USERID=? ORDER BY SEARCHKEY_ID DESC";
		DetachedCriteria dc = DetachedCriteria.forClass(SearchKey.class);  
		dc.addOrder(Order.desc("id"));
		Page page = this.baseDao.findByCriter(dc, true, pagelimit, 4);
		System.out.println(page.toString());
		return page.getPageList();
	}
	 
	
}

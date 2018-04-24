package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.CountMonth;

@Repository("countmonthdao")
public class CountMonthDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveCountMonth(CountMonth countMonth){
		this.baseDao.save(countMonth);
	}
	
	public void updateCountMonth(CountMonth countMonth){
		this.baseDao.update(countMonth);
	}
	
 
	public CountMonth getCountMonthByCountDate(long webchatid,String countMonth){
		String sql = "select * from TB_COUNT_MONTH where WEBCHAT_ID=? and COUNT_MONTH=? ";
		List<CountMonth> list= this.baseDao.findObjListBySql(sql, CountMonth.class,webchatid, countMonth );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	 
	
}

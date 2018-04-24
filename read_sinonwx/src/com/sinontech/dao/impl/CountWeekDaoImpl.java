package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.CountWeek;

@Repository("countweekdao")
public class CountWeekDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveCountWeek(CountWeek countWeek){
		this.baseDao.save(countWeek);
	}
	
	public void updateCountWeek(CountWeek countWeek){
		this.baseDao.update(countWeek);
	}
	
 
	public CountWeek getCountWeekByCountDate(long webchatid,String stratDate,String endDate){
		String sql = "select * from TB_COUNT_WEEK where WEBCHAT_ID=? and START_DATE=? and END_DATE=? ";
		List<CountWeek> list= this.baseDao.findObjListBySql(sql, CountWeek.class, webchatid,stratDate,endDate );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	 
	
}

package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.CountDay;

@Repository("countdaydao")
public class CountDayDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveCountDay(CountDay countDay){
		this.baseDao.save(countDay);
	}
	
	public void updateCountDay(CountDay countDay){
		this.baseDao.update(countDay);
	}
	
 
	public CountDay getCountDayByCountDate(long webchatid,String countDay){
		String sql = "select * from TB_COUNT_DAY where WEBCHAT_ID=? and COUNT_DAY=? ";
		List<CountDay> list= this.baseDao.findObjListBySql(sql, CountDay.class,webchatid, countDay );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	 
	
}

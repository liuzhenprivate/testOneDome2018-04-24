package com.sinontech.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Signlog;

@Repository("signlogdao")
public class SignlogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveSignlog(Signlog signlog){
		this.baseDao.save(signlog);
	}
	
	public void updateSignlog(Signlog signlog){
		this.baseDao.update(signlog);
	}
	
	public List<Signlog> getSignlogByUserId(long userid){
		String sql = "select * from TB_SIGN_LOG where USERID=? ORDER BY SIGN_LOG_ID DESC";
		return this.baseDao.findObjListBySql(sql, Signlog.class, userid);
	}
	public List<Signlog> getSignlogByUserIdAndDay(long userid,String day){
		String sql = "select * from TB_SIGN_LOG where USERID=? and substring(CREATE_TIME,1,10)='"+day+"' and LOG_TYPE=1";
		System.out.println(sql);
		return this.baseDao.findObjListBySql(sql, Signlog.class, userid);
	}
	//查询当月领取奖励
	public List<Signlog> getSignlogByUserIdSignmonth(long userid,String month,String logType){
		String sql = "select * from TB_SIGN_LOG where USERID=? and SIGN_MONTH=? and LOG_TYPE=? ";
		return this.baseDao.findObjListBySql(sql, Signlog.class, userid,month,logType);
	}
	//查询当月领取奖励
	public List<Signlog> getSignlogByUserIdSignmonth(long userid,String month,String logType,int times){
		String sql = "select * from TB_SIGN_LOG where USERID=? and SIGN_MONTH=? and LOG_TYPE=? and TIMES=? ";
		return this.baseDao.findObjListBySql(sql, Signlog.class, userid,month,logType,times);
	}
	
	public Long getBookCurrcenyByUserIdSignmonth(long userid,String month){
		
		String sql = "select sum(BOOK_CURRENCY) from TB_SIGN_LOG where USERID=? and SIGN_MONTH=? ";
		System.out.println(sql);
		BigDecimal count =  (BigDecimal) this.baseDao.countBySql(sql,userid,month);
		if(null!=count){
			return count.longValue();
		}else{
			return 0l;
		}
	}
	
}

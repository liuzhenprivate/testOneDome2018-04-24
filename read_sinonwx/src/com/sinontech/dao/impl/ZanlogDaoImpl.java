package com.sinontech.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ZanLog;

@Repository("zanlogdao")
public class ZanlogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveZanLog(ZanLog zanlog){
		this.baseDao.save(zanlog);
	}
	
	public List<ZanLog> getZanLogByUserId(long userid){
		String sql = "select * from TB_ZANLOG where USERID=? ORDER BY ZANLOG_ID DESC";
		return this.baseDao.findObjListBySql(sql, ZanLog.class, userid);
	}
	public List<ZanLog> getZanLogByUserIdAndDay(long userid,String day){
		String sql = "select * from TB_ZANLOG where USERID=? and substring(CREATE_TIME,1,10)='"+day+"' ";
		return this.baseDao.findObjListBySql(sql, ZanLog.class, userid);
	}
	/**
	 * 用途说明：查询用户是否已对该评论点赞
	 * @param userid
	 * @param discussId
	 * @return
	 * 2018年3月1日下午2:27:53
	 * @auther ljj
	 */
	public List<ZanLog> getZanLogByUserIdAndDiscussId(long userid,long discussId){
		String sql = "select * from TB_ZANLOG where USERID=? and DISCUSS_ID=? ";
		return this.baseDao.findObjListBySql(sql, ZanLog.class, userid,discussId);
	}
}
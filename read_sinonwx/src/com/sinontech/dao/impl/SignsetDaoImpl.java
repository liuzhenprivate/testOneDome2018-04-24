package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Signset;

@Repository("signsetdao")
public class SignsetDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public Signset getSignsetById(long id){
		String sql = "select * from TB_SIGN_SET where SIGN_SET_ID=? ";
		List<Signset> signsets = this.baseDao.findObjListBySql(sql, Signset.class, id);
		if(null!=signsets && signsets.size()>0){
			return signsets.get(0);
		}else{
			return null;
		}
	}
	public List<Signset> getSignset( ){
		String sql = "select * from TB_SIGN_SET   ";
		List<Signset> signsets = this.baseDao.findObjListBySql(sql, Signset.class);
		return signsets;
	}
	//查询奖励列表
	public List<Signset> getSignsetbonus( ){
		String sql = "select * from TB_SIGN_SET where  TIMES>0 ORDER BY TIMES ASC";
		List<Signset> signsets = this.baseDao.findObjListBySql(sql, Signset.class);
		return signsets;
	}
	
	public List<Signset> getSignsetByTimes( int times){
		String sql = "select * from TB_SIGN_SET where   TIMES=?";
		List<Signset> signsets = this.baseDao.findObjListBySql(sql, Signset.class,times);
		return signsets;
	}
	
}

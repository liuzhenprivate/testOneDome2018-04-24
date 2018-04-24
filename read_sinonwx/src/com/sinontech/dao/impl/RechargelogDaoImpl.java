package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Rechargelog;

@Repository("rechargelogdao")
public class RechargelogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveRechargelog(Rechargelog rechargelog){
		this.baseDao.save(rechargelog);
	}
	
	public void updateRechargelog(Rechargelog rechargelog){
		this.baseDao.update(rechargelog);
	}
	
	public List<Rechargelog> getRechargelogByUserId(long userid){
		String sql = "select * from TB_RECHARGE_LOG where USERID=? ORDER BY RECHARGE_LOG_ID DESC";
		return this.baseDao.findObjListBySql(sql, Rechargelog.class, userid);
	}
	
	public List<Rechargelog> getRechargelogByUserId(long userid,int state){
		String sql = "select * from TB_RECHARGE_LOG where USERID=? and STATE=? ORDER BY RECHARGE_LOG_ID DESC";
		return this.baseDao.findObjListBySql(sql, Rechargelog.class, userid,state);
	}
	/**
	 * 用途说明：
	 * @param tradeno
	 * @return
	 * 2018年3月2日下午2:49:37
	 * @auther ljj
	 */
	public Rechargelog getRechargelogByTradeno(String tradeno){
		String sql = "select * from TB_RECHARGE_LOG where TRADE_NO=?  ";
		List<Rechargelog> list = this.baseDao.findObjListBySql(sql, Rechargelog.class, tradeno);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Rechargelog getRechargelogById(long id){
		String sql = "select * from TB_RECHARGE_LOG where RECHARGE_LOG_ID=?  ";
		List<Rechargelog> list = this.baseDao.findObjListBySql(sql, Rechargelog.class, id);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}

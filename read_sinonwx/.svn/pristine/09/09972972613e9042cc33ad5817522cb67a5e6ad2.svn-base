package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Rechargeset;

@Repository("rechargesetdao")
public class RechargesetDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/**
	 * 用途说明：获取普通充值列表
	 * @return
	 * 2018年2月26日下午1:54:39
	 * @auther ljj
	 */
	public List<Rechargeset> getRechargesetComon( ){
		String sql = "select * from TB_RECHARGE_SET where RECHARGE_TYPE=2 and STATE=0 order by SORT_NUM desc ";
		List<Rechargeset> signsets = this.baseDao.findObjListBySql(sql, Rechargeset.class );
		return signsets;
	}
	/**
	 * 用途说明：获取VIP充值列表
	 * @return
	 * 2018年2月26日下午1:56:13
	 * @auther ljj
	 */
	public List<Rechargeset> getRechargesetVip( ){
		String sql = "select * from TB_RECHARGE_SET where (RECHARGE_TYPE=0 or RECHARGE_TYPE=1) and STATE=0 order by SORT_NUM desc ";
		List<Rechargeset> signsets = this.baseDao.findObjListBySql(sql, Rechargeset.class );
		return signsets;
	}
	
	public Rechargeset getSignsetByID(long id){
		String sql = "select * from TB_RECHARGE_SET where RECHARGE_SET_ID=?  ";
		List<Rechargeset> list = this.baseDao.findObjListBySql(sql, Rechargeset.class, id);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}

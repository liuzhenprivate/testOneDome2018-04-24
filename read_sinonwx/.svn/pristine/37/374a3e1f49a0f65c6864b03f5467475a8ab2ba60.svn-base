package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.RechargelogDaoImpl;
import com.sinontech.modle.Rechargelog;
import com.sinontech.service.RechargelogService;

@Service("rechargelogservice")
public class RechargelogServiceImpl implements RechargelogService{

	@Autowired
	RechargelogDaoImpl rechargelogDaoImpl;

	public List<Rechargelog> getRechargelogByUserId(long userid) {
		return this.rechargelogDaoImpl.getRechargelogByUserId(userid);
	}

	public void saveRechargelog(Rechargelog rechargelog) {
		this.rechargelogDaoImpl.saveRechargelog(rechargelog);
	}

	public void updateRechargelog(Rechargelog rechargelog) {
		this.rechargelogDaoImpl.updateRechargelog(rechargelog);
	}

	//通过订单号查询充值记录
	public Rechargelog getRechargelogByTradeno(String tradeno){
		return this.rechargelogDaoImpl.getRechargelogByTradeno(tradeno);
	}

	public Rechargelog getRechargelogById(long id){
		return this.rechargelogDaoImpl.getRechargelogById(id);
	}

	 
	
}

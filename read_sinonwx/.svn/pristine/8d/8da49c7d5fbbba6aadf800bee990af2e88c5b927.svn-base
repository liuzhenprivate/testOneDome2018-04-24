package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.RechargesetDaoImpl;
import com.sinontech.modle.Rechargeset;
import com.sinontech.service.RechargesetService;

@Service("rechargesetservice")
public class RechargesetServiceImpl implements RechargesetService{

	@Autowired
	RechargesetDaoImpl rechargesetDaoImpl;

	public List<Rechargeset> getRechargesetComon() {
		return this.rechargesetDaoImpl.getRechargesetComon();
	}
	
	public List<Rechargeset> getRechargesetVip(){
		return this.rechargesetDaoImpl.getRechargesetVip();
	}
	
	public Rechargeset getRechargesetByID(long id) {
		return this.rechargesetDaoImpl.getSignsetByID(id);
	}
}

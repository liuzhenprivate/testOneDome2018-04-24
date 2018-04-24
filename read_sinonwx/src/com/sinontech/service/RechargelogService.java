package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.Rechargelog;


public interface RechargelogService {
	
	public void saveRechargelog(Rechargelog rechargelog);
	public void updateRechargelog(Rechargelog rechargelog);
	public List<Rechargelog> getRechargelogByUserId(long userid);
	public Rechargelog getRechargelogByTradeno(String tradeno);
	public Rechargelog getRechargelogById(long id);
}

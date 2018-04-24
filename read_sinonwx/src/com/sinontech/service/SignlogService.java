package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.Signlog;


public interface SignlogService {
	
	public void saveSignlog(Signlog signlog);
	public void updateSignlog(Signlog signlog);
	public List<Signlog> getSignlogByUserId(long userid);
	public List<Signlog> getSignlogByUserIdAndDay(long userid,String day);
	//查询当月领取奖励
	public List<Signlog> getSignlogByUserIdSignmonth(long userid,String month,String logType,int times);
	
	public List<Signlog> getSignlogByUserIdSignmonth(long userid,String month,String logType);
	
	//查询当月获取的书币
	public Long getBookCurrcenyByUserIdSignmonth(long userid,String month);
	
}

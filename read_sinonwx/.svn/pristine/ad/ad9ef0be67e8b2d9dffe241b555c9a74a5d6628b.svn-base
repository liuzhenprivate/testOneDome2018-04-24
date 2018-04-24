package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.SignlogDaoImpl;
import com.sinontech.modle.Signlog;
import com.sinontech.service.SignlogService;

@Service("signlogservice")
public class SignlogServiceImpl implements SignlogService{

	@Autowired
	SignlogDaoImpl signlogDaoImpl;

	public List<Signlog> getSignlogByUserId(long userid) {
		return this.signlogDaoImpl.getSignlogByUserId(userid);
	}

	public void saveSignlog(Signlog signlog) {
		this.signlogDaoImpl.saveSignlog(signlog);
	}

	public void updateSignlog(Signlog signlog) {
		this.signlogDaoImpl.updateSignlog(signlog);
	}

	public List<Signlog> getSignlogByUserIdAndDay(long userid,String day){
		return this.signlogDaoImpl.getSignlogByUserIdAndDay(userid, day);
	}

	//查询当月领取奖励
	public List<Signlog> getSignlogByUserIdSignmonth(long userid,String month,String logType,int times){
		return this.signlogDaoImpl.getSignlogByUserIdSignmonth(userid,month, logType,times);
	}
	public List<Signlog> getSignlogByUserIdSignmonth(long userid,String month,String logType){
		return this.signlogDaoImpl.getSignlogByUserIdSignmonth(userid,month, logType);
	}
	public Long getBookCurrcenyByUserIdSignmonth(long userid, String month) {
		return this.signlogDaoImpl.getBookCurrcenyByUserIdSignmonth(userid, month);
	}

	  
	
	
}

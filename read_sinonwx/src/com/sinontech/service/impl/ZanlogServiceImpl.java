package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ZanlogDaoImpl;
import com.sinontech.modle.ZanLog;
import com.sinontech.service.ZanlogService;
/**
 * 
 * @author ljj
 *
 */
@Service("zanlogservice")
public class ZanlogServiceImpl implements ZanlogService{

	@Autowired
	ZanlogDaoImpl zanLogDaoImpl;

	@Override
	public void saveZanLog(ZanLog zanlog) {
		this.zanLogDaoImpl.saveZanLog(zanlog);
	}

	@Override
	public List<ZanLog> getZanLogByUserId(long userid) {
		return this.zanLogDaoImpl.getZanLogByUserId(userid);
	}

	@Override
	public List<ZanLog> getZanLogByUserIdAndDay(long userid, String day) {
		return this.zanLogDaoImpl.getZanLogByUserIdAndDay(userid, day);
	}

	@Override
	public List<ZanLog> getZanLogByUserIdAndDiscussId(long userid, long discussId) {
		return this.zanLogDaoImpl.getZanLogByUserIdAndDiscussId(userid, discussId);
	}

	  
	
}

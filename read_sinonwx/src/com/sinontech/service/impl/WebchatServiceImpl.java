package com.sinontech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.WebchatDaoImpl;
import com.sinontech.modle.Webchat;
import com.sinontech.service.WebchatService;

@Service("webchatservice")
public class WebchatServiceImpl implements WebchatService{

	@Autowired
	WebchatDaoImpl webchatDaoImpl;

	public Webchat getWebchatById(long id) {
		return this.webchatDaoImpl.getWebchatById(id);
	}


	public void updateWebchat(Webchat webchat) {
		this.webchatDaoImpl.updateWebchat(webchat);
	}


	public Webchat selectFindByChannelId(long user_id) {
		return this.webchatDaoImpl.selectFindByChannelId(user_id);
	}
 
 

	  
	
	
}

package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleLogDaoImpl;
import com.sinontech.dao.impl.WebchatReplyDaoImpl;
import com.sinontech.modle.ArticleLog;
import com.sinontech.modle.WebchatReply;
import com.sinontech.service.ArticleLogService;
import com.sinontech.service.WebchatReplyService;

@Service("webchatReplyservice")
public class WebchatReplyServiceImpl implements WebchatReplyService{

	@Autowired
	WebchatReplyDaoImpl webchatReplyDaoImpl;

	@Override
	public List<WebchatReply> getWebchatReply(long webchatId, int type) {
		return this.webchatReplyDaoImpl.getWebchatReply(webchatId, type);
	}

	@Override
	public List<WebchatReply> getWebchatReplyByKey(long webchatId,String key) {
		return this.webchatReplyDaoImpl.getWebchatReplyByKey(webchatId, key);
	}

	  
	
	
}
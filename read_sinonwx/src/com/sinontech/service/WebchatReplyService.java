package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.Article;
import com.sinontech.modle.WebchatReply;

public interface WebchatReplyService {
	
	
	public List<WebchatReply> getWebchatReply(long webchatId,int type);
	public List<WebchatReply> getWebchatReplyByKey(long webchatId, String key);
}
package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ConsumeLogDaoImpl;
import com.sinontech.modle.ConsumeLog;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.ConsumeLogService;

@Service("consumeLogservice")
public class ConsumeLogServiceImpl implements ConsumeLogService{

	@Autowired
	ConsumeLogDaoImpl consumeLogDaoImpl;

	@Override
	public void saveConsumeLog(ConsumeLog articleLog) {
		this.consumeLogDaoImpl.saveConsumeLog(articleLog);
		
	}

	@Override
	public List<ConsumeLog> getConsumeLogByUserId(long userid) {
		return this.consumeLogDaoImpl.getConsumeLogByUserId(userid);
	}
	@Override
	public void updateConsumeLog(ConsumeLog consumeLog) {
		this.consumeLogDaoImpl.updateConsumeLog(consumeLog);
	}
	@Override
	public ConsumeLog selectIsNotNull(long userId, long articlechapterid) {
		return this.consumeLogDaoImpl.selectIsNotNull(userId,articlechapterid);
	}

	@Override
	public List<ConsumeLog> getConsumeLogByUserIdAndArticleId(long userid,
			long articleid) {
		return this.consumeLogDaoImpl.getConsumeLogByUserIdAndArticleId(userid, articleid);
	}
	@Override
	public ConsumeLog seachWhetherReading(long userId, long articleId) {
		return this.consumeLogDaoImpl.seachWhetherReading(userId,articleId);
	}

	
}
package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleLogDaoImpl;
import com.sinontech.modle.ArticleLog;
import com.sinontech.service.ArticleLogService;

@Service("articleLogservice")
public class ArticleLogServiceImpl implements ArticleLogService{

	@Autowired
	ArticleLogDaoImpl articleLogDaoImpl;

	@Override
	public void saveArticleLog(ArticleLog articleLog) {
		this.articleLogDaoImpl.saveArticleLog(articleLog);
		
	}

	@Override
	public List<ArticleLog> getArticleLogByUserId(long userid) {
		return this.articleLogDaoImpl.getArticleLogByUserId(userid);
	}

	@Override
	public void updateArticleLog(ArticleLog articleLog) {
		this.articleLogDaoImpl.updateArticleLog(articleLog);
	}
	
	/**
	 * 查询用户是否已阅读过该书籍
	 */
	@Override
	public ArticleLog getArticleLogByUIdAndAid(long userid, long articleid) {
		return this.articleLogDaoImpl.getArticleLogByUIdAndAid(userid, articleid);
	}

	@Override
	public List<ArticleLog> getConsumArticleLogByUserId(long userid) {
		return this.articleLogDaoImpl.getConsumArticleLogByUserId(userid);
	}
	@Override
	public ArticleLog firstArticleLog(long userid) {
		return this.articleLogDaoImpl.firstArticleLog(userid);
	}
	 
	
	
}
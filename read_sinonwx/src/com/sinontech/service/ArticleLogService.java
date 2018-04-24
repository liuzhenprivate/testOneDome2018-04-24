package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.ArticleLog;


public interface ArticleLogService {
	public ArticleLog getArticleLogByUIdAndAid(long userid,long articleid );
	public void updateArticleLog(ArticleLog articleLog);
	public void saveArticleLog(ArticleLog articleLog);
	public List<ArticleLog> getArticleLogByUserId(long userid );
	public List<ArticleLog> getConsumArticleLogByUserId(long userid );
	public ArticleLog firstArticleLog(long userid);
}

package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleChapterLogDaoImpl;
import com.sinontech.modle.ArticleChapterLog;
import com.sinontech.service.ArticleChapterLogService;
/**
 * 
 * @author ljj
 *
 */
@Service("articleChapterLogservice")
public class ArticleChapterLogServiceImpl implements ArticleChapterLogService{

	@Autowired
	ArticleChapterLogDaoImpl articleChapterLogDaoImpl;

	@Override
	public void saveArticleChapterLog(ArticleChapterLog articleChapterLog) {
		this.articleChapterLogDaoImpl.saveArticleChapterLog(articleChapterLog);
		
	}

	@Override
	public List<ArticleChapterLog> getArticleChapterLogByUserId(long userid) {
		return this.articleChapterLogDaoImpl.getArticleChapterLogByUserId(userid);
	}
	 
	@Override
	public List<ArticleChapterLog> getArticleChapterLogByUserIdAndArticleid(long userid,long articleid ){
		return this.articleChapterLogDaoImpl.getArticleChapterLogByUserIdAndArticleid(userid,articleid);
	}

	@Override
	public ArticleChapterLog getNewArticleChapterLogByUserIdAndArticleid(long userid, long articleid) {
		return this.articleChapterLogDaoImpl.getNewArticleChapterLogByUserIdAndArticleid(userid, articleid);
	}
	@Override
	public void updateArticleChapterLog(ArticleChapterLog articleChapterLog) {
		this.articleChapterLogDaoImpl.updateArticleChapterLog(articleChapterLog);
	}
	@Override
	public void delArticleChapterLog(ArticleChapterLog articleChapterLog) {
		this.articleChapterLogDaoImpl.delArticleChapterLog(articleChapterLog);
	}

	@Override
	public ArticleChapterLog getNewArticleChapterLogByUserId(long userid) {
		return this.articleChapterLogDaoImpl.getNewArticleChapterLogByUserId(userid);
	}
	
}
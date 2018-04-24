package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.ArticleChapter;
import com.sinontech.modle.ArticleChapterLog;


public interface ArticleChapterLogService {

	public void saveArticleChapterLog(ArticleChapterLog articleChapterLog);
	public List<ArticleChapterLog> getArticleChapterLogByUserId(long userid );
	public List<ArticleChapterLog> getArticleChapterLogByUserIdAndArticleid(long userid,long articleid );
	public ArticleChapterLog getNewArticleChapterLogByUserIdAndArticleid(long userid,long articleid );
	public void updateArticleChapterLog(ArticleChapterLog articleChapterLog);
	public void delArticleChapterLog(ArticleChapterLog articleChapterLog);
	public  ArticleChapterLog  getNewArticleChapterLogByUserId(long userid );
}

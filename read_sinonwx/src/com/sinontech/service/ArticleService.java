package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.Article;

public interface ArticleService {
	
	
	public Article getArticleByArticleId(long articleid);
	
	public List<Article> boardList(long boardId);
	
	public List<Article> getArticleByKey(String key);
	
	public void updatepayConsums(long articleid,int consums);
	
	public void updateReaders(long articleid);
	
	public int updateColletion(long articleid);
	
	public List<Article> getArticleByArticleCategoryId(long articleCategoryId,String sort,String labelId,String feeType,String serialState);
	
	public void updateRecharges(long articleId, long money);
	
	public void updateScore(long articleId,double score);
	
	public void updateArticleFeePoples(long articleId);
}
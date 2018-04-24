package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleDaoImpl;
import com.sinontech.modle.Article;
import com.sinontech.service.ArticleService;

@Service("articleservice")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleDaoImpl articleDaoImpl;
	
	@Override
	public Article getArticleByArticleId(long articleId) {
		return this.articleDaoImpl.getArticleByArticleId(articleId);
	}
	
	@Override
	public List<Article> boardList(long boardId) {
		return this.articleDaoImpl.boardList(boardId);
	}
	
	@Override
	public List<Article> getArticleByKey(String key){
		return this.articleDaoImpl.getArticleByKey(key);
	}
	
	@Override
	public void updatepayConsums(long articleId,int consums){
		this.articleDaoImpl.updatepayConsums(articleId,consums);
	}
	
	@Override
	public void updateReaders(long articleId){
		this.articleDaoImpl.updateReaders(articleId);
	}
	
	@Override
	public int updateColletion(long articleId){
		return this.articleDaoImpl.updateColletion(articleId);
	}

	@Override
	public List<Article> getArticleByArticleCategoryId(long articleCategoryId,String sort,String labelId,String feeType,String serialState) {
		return this.articleDaoImpl.getArticleByArticleCategoryId(articleCategoryId,sort,labelId,feeType,serialState);
	}
	@Override
	public void updateRecharges(long articleId, long money) {
		this.articleDaoImpl.updateRecharges(articleId,money);
	}
	@Override
	public void updateScore(long articleId,double score) {
		this.articleDaoImpl.updateScore(articleId,score);
	}
	@Override
	public void updateArticleFeePoples(long articleId) {
		this.articleDaoImpl.updateArticleFeePoples(articleId);
	}
}

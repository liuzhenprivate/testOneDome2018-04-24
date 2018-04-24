package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleCategoryDaoImpl;
import com.sinontech.dao.impl.ArticleLogDaoImpl;
import com.sinontech.modle.ArticleCategory;
import com.sinontech.modle.ArticleLog;
import com.sinontech.service.ArticleCategoryService;
import com.sinontech.service.ArticleLogService;

@Service("articlecategoryservice")
public class ArticleCategoryServiceImpl implements ArticleCategoryService{

	@Autowired
	ArticleCategoryDaoImpl articleCategoryDaoImpl;

	@Override
	public ArticleCategory getArticleCategoryById(long articleCategoryId) {
		return this.articleCategoryDaoImpl.getArticleCategoryById(articleCategoryId);
	}

	@Override
	public List<ArticleCategory> getList() {
		return this.articleCategoryDaoImpl.getList();
	}

	@Override
	public List<ArticleCategory> getListByCategoryType(int ctype) {
		return this.articleCategoryDaoImpl.getListByCategoryType(ctype);
	}

	 
	 
	
	
}

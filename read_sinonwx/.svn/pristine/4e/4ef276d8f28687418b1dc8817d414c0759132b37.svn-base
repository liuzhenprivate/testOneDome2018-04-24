package com.sinontech.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ArticleLabel;
import com.sinontech.modle.Label;

@Repository("articleLabeldao")
public class ArticleLabelDaoImpl {
	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 查询指定书籍id的标签
	 */
	public List<ArticleLabel> varListLabel(long articleId) {
		String sql = "select * from TB_ARTICLE_LABEL where ARTICLE_ID = ?";
		return this.baseDao.findObjListBySql(sql, ArticleLabel.class,articleId  );
	}
}

package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Article;
import com.sinontech.modle.ArticleCategory;
import com.sinontech.modle.BookShelf;

@Repository("articlecategorydao")
public class ArticleCategoryDaoImpl {
	
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public ArticleCategory getArticleCategoryById(long articleCategoryId) {
		String sql = "select * from TB_ARTICLE_CATEGORY where  ARTICLE_CATEGORY_ID = ? ";
		List<ArticleCategory> list= this.baseDao.findObjListBySql(sql, ArticleCategory.class,articleCategoryId  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 用途说明：查询书籍分类
	 * @return
	 * 2018年3月7日下午2:28:47
	 * @auther ljj
	 */
	public List<ArticleCategory> getList() {
		String sql = "select * from TB_ARTICLE_CATEGORY  order by SORT asc ";
		return this.baseDao.findObjListBySql(sql, ArticleCategory.class  );
	}
	/**
	 * 用途说明：通过类目类型查询书籍
	 * @param ctype
	 * @return
	 * 2018年3月7日下午4:01:52
	 * @auther ljj
	 */
	public List<ArticleCategory> getListByCategoryType(int ctype) {
		String sql = "select * from TB_ARTICLE_CATEGORY where CATEGORY_TYPE=? order by SORT asc ";
		return this.baseDao.findObjListBySql(sql, ArticleCategory.class,ctype  );
	}
			
}

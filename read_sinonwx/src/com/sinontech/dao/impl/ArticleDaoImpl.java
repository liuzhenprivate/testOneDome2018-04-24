package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Article;
import com.sinontech.modle.BookShelf;

@Repository("articledao")
public class ArticleDaoImpl {
	
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public Article getArticleByArticleId(long articleId) {
		String sql = "select * from TB_ARTICLE where  ARTICLE_ID = ? AND STATE = 1";
		List<Article> list= this.baseDao.findObjListBySql(sql, Article.class,articleId  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 用途说明：通过书籍分类查询书籍
	 * @param articleCategoryId
	 * @return
	 * 2018年3月7日下午2:45:44
	 * @auther ljj
	 */
	public List<Article> getArticleByArticleCategoryId(long articleCategoryId,String sort,String labelId,String feeType,String serialState) {
		String sql = "select * from TB_ARTICLE a,TB_ARTICLE_CATEGORY b " +
				"where a.ARTICLE_CATEGORY_ID=b.ARTICLE_CATEGORY_ID " +
				"and   a.STATE = 1 " +
				"and a.ARTICLE_CATEGORY_ID=? ";
		if(!"".equals(labelId)){
			long labelid = Long.parseLong(labelId);
			sql += "and a.ARTICLE_ID in(select ARTICLE_ID from tb_article_label where LABEL_ID = "+labelid+")  ";
		}
		if(!"".equals(feeType)&&null!=feeType){
			int feetype = Integer.parseInt(feeType);
			sql += "and  a.FEE_TYPE =  "+feetype+" ";
		}
		if(!"".equals(serialState)&&null!=serialState){
			int serialstate = Integer.parseInt(serialState);
			sql += "and  a.SERIAL_STATE = "+serialstate+" ";
		}
		if("1".equals(sort)){
			sql += "ORDER BY a.CREATE_TIME DESC";
		}else if("2".equals(sort)){
			sql += "ORDER BY a.SCORE DESC";
		}else{
			sql += "ORDER BY a.DISPLAY_READERS DESC";
		}
		List<Article> list= this.baseDao.findObjListBySql(sql, Article.class,articleCategoryId  );
		return list;
	}
	
	/**
	 * 用途说明：根据书籍名称和读者关键字模糊查询
	 * @param key
	 * @return
	 * 2018年2月28日上午10:17:24
	 * @auther ljj
	 */
	public List<Article> getArticleByKey(String key) {
		key = "%"+key+"%";
		String sql = "select * from TB_ARTICLE where  (ARTICLE_NAME like ? or AUTHOR like ?) and  STATE = 1 ";
		List<Article> list= this.baseDao.findObjListBySql(sql, Article.class,key,key  );
		return list;
	}
	
	/*
	 * 主编推荐榜预览
	 */
	public List<Article> boardList(long boardId) {
		String sql = "select ta.* from tb_board_detail tbd " +
				"LEFT JOIN tb_article ta " +
				"ON tbd.ARTICLE_ID = ta.ARTICLE_ID  " +
				"where tbd.BOARD_TYPE = 0 " +
				"AND ta.STATE = 1 " +
				"and tbd.BOARD_ID = ? " +
				"ORDER BY tbd.SORT limit 0,3";
		return this.baseDao.findObjListBySql(sql, Article.class,boardId  );
	}
	
	/*
	 * 更新书籍累计阅读币
	 */
	public void updatepayConsums(long articleId,int consums) {
		String sql = "select * from TB_ARTICLE where ARTICLE_ID = ? AND	STATE = 1";
		List<Article> list = this.baseDao.findObjListBySql(sql ,Article.class ,articleId);
		if(null!=list && list.size()>0){
			Article article = list.get(0);
			article.setPayConsumes(article.getPayConsumes() + consums);
			article.setId(articleId);
			this.baseDao.update(article);
		}
	}
	
	/*
	 * 更新书籍阅读人数
	 */
	public void updateReaders(long articleId) {
		String sql = "select * from TB_ARTICLE where ARTICLE_ID = ? AND	STATE = 1";
		List<Article> list = this.baseDao.findObjListBySql(sql ,Article.class ,articleId);
		if(null!=list && list.size()>0){
			Article article = list.get(0);
			article.setReaders(article.getReaders() + 1);
			article.setDisplayReaders(article.getReaders()+1);
			article.setId(articleId);
			this.baseDao.update(article);
		}
	}
	
	/*
	 * 更新书籍收藏人数
	 */
	public int updateColletion(long articleId) {
		String sql = "select * from TB_ARTICLE where ARTICLE_ID = ? AND	STATE = 1";
		List<Article> list = this.baseDao.findObjListBySql(sql ,Article.class ,articleId);
		if(null!=list && list.size()>0){
			Article article = list.get(0);
			article.setCollection(article.getCollection() + 1);
			article.setId(articleId);
			this.baseDao.update(article);
			return article.getCollection();
		}else{
			return 0;
		}
			
	}
	/**
	 * 
	 * @purpose：更新书籍总充值额
	 * @param articleId
	 * @param money
	 * @return void
	 * @author liuzhen
	 * @Time：2018-3-23 下午3:21:08
	 */
	public void updateRecharges(long articleId, long money) {
		String sql = "select * from TB_ARTICLE where ARTICLE_ID = ? AND	STATE = 1";
		List<Article> list = this.baseDao.findObjListBySql(sql ,Article.class ,articleId);
		if(null!=list && list.size()>0){
			Article article = list.get(0);
			article.setRrecharges(article.getRrecharges()+money);
			article.setId(articleId);
			this.baseDao.update(article);
		}
	}
	/**
	 * 
	 * @purpose：更新书籍评分
	 * @param article
	 * @return void
	 * @author liuzhen
	 * @Time：2018-4-16 下午3:06:36
	 * @param score 
	 */
	public void updateScore(long articleId, double score) {
		String sql = "select * from TB_ARTICLE where ARTICLE_ID = ? AND	STATE = 1";
		List<Article> list = this.baseDao.findObjListBySql(sql ,Article.class ,articleId);
		if(null!=list && list.size()>0){
			Article article = list.get(0);
			article.setId(articleId);
			article.setScore(score);
			this.baseDao.update(article);
		}
	}
	/**
	 * 
	 * @purpose：更新书籍付费人数  +1
	 * @param articleId
	 * @return void
	 * @author liuzhen
	 * @Time：2018-4-18 下午2:36:31
	 */
	public void updateArticleFeePoples(long articleId) {
		String sql = "select * from TB_ARTICLE where ARTICLE_ID = ? AND	STATE = 1";
		List<Article> list = this.baseDao.findObjListBySql(sql ,Article.class ,articleId);
		if(null!=list && list.size()>0){
			Article article = list.get(0);
			article.setId(articleId);
			article.setFeePoples(article.getFeePoples()+1);
			this.baseDao.update(article);
		}
	}
}
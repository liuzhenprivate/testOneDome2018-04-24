package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ArticleChapter;
import com.sinontech.modle.ArticleChapterLog;

@Repository("articleChapterdao")
public class ArticleChapterDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void updateArticleChapter(ArticleChapter articleChapter){
		this.baseDao.update(articleChapter);
	}
 
	/**
	 * 	用途说明：查询书籍的章节
	 * @param articleid
	 * @return
	 * 2018年2月27日下午1:59:45
	 * @auther ljj
	 */
	public List<ArticleChapter> getArticleChapterByarticleId(long articleid ){
		String sql = "select a.* from TB_ARTICLE_CHAPTER a,TB_ARTICLE b where a.ARTICLE_ID =b.ARTICLE_ID and a.ARTICLE_ID =? and b.STATE=1 and a.CHAPTER_STATE = 0 order by a.CHAPTER_NO asc  ";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleid  );
		return list;
	}
	
	/**
	 * 用途说明：获取书籍的第一个章节
	 * @param articleid
	 * @return
	 * 2018年3月1日上午10:24:26
	 * @auther ljj
	 */
	public ArticleChapter getArticleChapterFirstByarticleId(long articleid ){
		String sql = "select a.* from TB_ARTICLE_CHAPTER a,TB_ARTICLE b where a.ARTICLE_ID =b.ARTICLE_ID and a.ARTICLE_ID =? and b.STATE=1 and a.CHAPTER_STATE = 0 order by a.CHAPTER_NO asc  ";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleid  );
		if(null!=list&& list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * 用途：查询书籍章节
	 * @param @param articlechapterid
	 * @param @return
	 * @return ArticleChapter
	 * @author liuzhen
	 * 时间 2018-3-2 下午2:42:46
	 */
	public ArticleChapter getArticleChapterById(long articlechapterid) {
		String sql = "select tac.* from TB_ARTICLE_CHAPTER tac LEFT JOIN tb_article ta ON tac.ARTICLE_ID = ta.ARTICLE_ID where ta.STATE = 1 AND tac.ARTICLE_CHAPTER_ID = ? and  tac.CHAPTER_STATE = 0 ";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articlechapterid  );
		if(null!=list&& list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @purpose：查询书籍下一章
	 * @param articleId
	 * @param articleChapterId
	 * @return
	 * @return ArticleChapter
	 * @author liuzhen
	 * @Time：2018-3-21 下午12:30:41
	 */
	public ArticleChapter readNextArticleChapter(long articleId,long chapterNo) {
		String sql = "select tac.* from TB_ARTICLE_CHAPTER tac, tb_article ta " +
				"where ta.STATE = 1 " +
				"AND tac.ARTICLE_ID = ta.ARTICLE_ID " +
				"AND tac.CHAPTER_STATE = 0 " +
				"AND ta.ARTICLE_ID = ? " +
				"AND tac.CHAPTER_NO > ? " +
				"ORDER BY tac.CHAPTER_NO LIMIT 1 ";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleId,chapterNo  );
		if(null!=list&& list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 
	 * @purpose：查询书籍上一章
	 * @param articleId
	 * @param articleChapterId
	 * @return
	 * @return ArticleChapter
	 * @author liuzhen
	 * @Time：2018-3-21 下午12:31:15
	 */
	public ArticleChapter readUpperArticleChapter(long articleId,long chapterNo) {
		String sql = "select tac.* from TB_ARTICLE_CHAPTER tac, tb_article ta " +
				"where ta.STATE = 1 " +
				"AND tac.ARTICLE_ID = ta.ARTICLE_ID " +
				"AND tac.CHAPTER_STATE = 0 " +
				"AND ta.ARTICLE_ID = ? " +
				"AND tac.CHAPTER_NO < ? " +
				"ORDER BY tac.CHAPTER_NO desc LIMIT 1 ";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleId,chapterNo  );
		if(null!=list&& list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * 
	 * @purpose：查询分享链接过来的章节
	 * @param articleId
	 * @param articleChpaters
	 * @return
	 * @return ArticleChapter
	 * @author liuzhen
	 * @Time：2018-3-17 下午5:39:47
	 */
	public List<ArticleChapter> visitSeachChapter(long articleId, int articleChpaters) {
		String sql = "select * from TB_ARTICLE_CHAPTER  " +
				"where CHAPTER_STATE =  0 " +
				"and ARTICLE_ID = ? " +
				"ORDER BY CHAPTER_NO asc " +
				"LIMIT 0,?";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleId,articleChpaters  );
		return list;
	}
	
	/**
	 * 
	 * @purpose：通过分享链接进入详细章节阅读
	 * @param articleId
	 * @param articleChapterId
	 * @return
	 * @return ArticleChapter
	 * @author liuzhen
	 * @Time：2018-3-19 下午2:27:25
	 */
	public ArticleChapter seachNextChapter(long articleId, long articleChapterId) {
		String sql = "select * from tb_article_chapter where ARTICLE_ID = ? AND ARTICLE_CHAPTER_ID > ? AND CHAPTER_STATE = 0";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleId,articleChapterId  );
		if(null!=list&& list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 
	 * @purpose：查询是否到强制关注章节
	 * @param articleId
	 * @param articleChapterId
	 * @return
	 * @return ArticleChapter
	 * @author liuzhen
	 * @Time：2018-3-19 下午2:38:38
	 */
	public int countDownChapter(long articleId, long articleChapterId) {
		String sql = "	select * from tb_article_chapter where ARTICLE_ID = ? AND ARTICLE_CHAPTER_ID < ? and  CHAPTER_STATE = 0 ";
		List<ArticleChapter> list= this.baseDao.findObjListBySql(sql, ArticleChapter.class,articleId,articleChapterId  );
		return list.size();
	}
	
}
package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ArticleChapterLog;

@Repository("articleChapterLogdao")
public class ArticleChapterLogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveArticleChapterLog(ArticleChapterLog articleChapterLog){
		this.baseDao.save(articleChapterLog);
	}
	
	/**
	 * 用途说明：获取最新的一条阅读记录
	 * @param userid
	 * @return
	 * 2018年3月13日上午9:48:42
	 * @auther ljj
	 */
	public  ArticleChapterLog  getNewArticleChapterLogByUserId(long userid ){
		String sql = "select * from TB_ARTICLE_CHAPTERLOG a,TB_ARTICLE b  where a.ARTICLE_ID=b.ARTICLE_ID  and b.STATE=1 and a.USERID=? order by a.ARTICLE_CHAPTERLOG_ID desc limit 1  ";
		List<ArticleChapterLog> list= this.baseDao.findObjListBySql(sql, ArticleChapterLog.class,userid  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 	用途说明：查询用户的章节阅读记录
	 * @param userid
	 * @return
	 * 2018年2月27日下午1:59:45
	 * @auther ljj
	 */
	public List<ArticleChapterLog> getArticleChapterLogByUserId(long userid ){
		String sql = "select * from TB_ARTICLE_CHAPTERLOG a,TB_ARTICLE b  where a.ARTICLE_ID=b.ARTICLE_ID  and b.STATE=1 and a.USERID=? order by a.ARTICLE_CHAPTERLOG_ID desc  ";
		List<ArticleChapterLog> list= this.baseDao.findObjListBySql(sql, ArticleChapterLog.class,userid  );
		return list;
	}
	
	/**
	 * 用途说明：查询用户具体书籍下的章节记录
	 * @param userid
	 * @param articleid
	 * @return
	 * 2018年2月27日下午1:59:59
	 * @auther ljj
	 */
	public List<ArticleChapterLog> getArticleChapterLogByUserIdAndArticleid(long userid,long articleid ){
		String sql = "select * from TB_ARTICLE_CHAPTERLOG a  where   a.USERID=? and a.ARTICLE_ID=? order by a.ARTICLE_CHAPTERLOG_ID desc  ";
		List<ArticleChapterLog> list= this.baseDao.findObjListBySql(sql, ArticleChapterLog.class,userid,articleid  );
		return list;
	}
	/**
	 * 用途说明：查询用户阅读书籍的最新章节
	 * @param userid
	 * @param articleid
	 * @return
	 * 2018年3月1日上午9:34:00
	 * @auther ljj
	 */
	public ArticleChapterLog getNewArticleChapterLogByUserIdAndArticleid(long userid,long articleid ){
		String sql = "select * from TB_ARTICLE_CHAPTERLOG a, TB_ARTICLE_CHAPTER c where   a.ARTICLE_CHAPTER_ID=c.ARTICLE_CHAPTER_ID and a.USERID=? and a.ARTICLE_ID=? order by 	a.CREATE_TIME desc  ";
		List<ArticleChapterLog> list= this.baseDao.findObjListBySql(sql, ArticleChapterLog.class,userid,articleid  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @purpose：更新书籍章节阅读记录
	 * @param articleChapterLog
	 * @return void
	 * @author liuzhen
	 * @Time：2018-3-11 上午11:03:57
	 */
	public void updateArticleChapterLog(ArticleChapterLog articleChapterLog) {
		this.baseDao.update(articleChapterLog);
	}
	
	/**
	 * 
	 * @purpose：删除历史阅读记录
	 * @param articleChapterLog
	 * @return void
	 * @author liuzhen
	 * @Time：2018-3-12 下午4:34:01
	 */
	public void delArticleChapterLog(ArticleChapterLog articleChapterLog) {
		this.baseDao.delete(articleChapterLog);
	}
	
}
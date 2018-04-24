package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ArticleLabel;
import com.sinontech.modle.ArticleLog;

@Repository("articleLogdao")
public class ArticleLogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void updateArticleLog(ArticleLog articleLog){
		this.baseDao.update(articleLog);
	}
	
	public void saveArticleLog(ArticleLog articleLog){
		this.baseDao.save(articleLog);
	}
 
	public List<ArticleLog> getArticleLogByUserId(long userid ){
		String sql = "select * from TB_ARTICLELOG a,TB_ARTICLE b  where a.ARTICLE_ID=b.ARTICLE_ID   and a.USERID=?  order by  a.ARTICLELOG_ID desc";
		List<ArticleLog> list= this.baseDao.findObjListBySql(sql, ArticleLog.class,userid  );
		return list ;
	}
	
	/**
	 * 用途说明：获取有消费阅读币的书籍阅读记录
	 * @param userid
	 * @return
	 * 2018年3月21日上午10:15:37
	 * @auther ljj
	 */
	public List<ArticleLog> getConsumArticleLogByUserId(long userid ){
		String sql = "select * from TB_ARTICLELOG a,TB_ARTICLE b  where a.ARTICLE_ID=b.ARTICLE_ID   and a.USERID=? and a.FEE>0 order by  a.ARTICLELOG_ID desc";
		List<ArticleLog> list= this.baseDao.findObjListBySql(sql, ArticleLog.class,userid  );
		return list ;
	}
	public ArticleLog getArticleLogByUIdAndAid(long userid,long articleid ){
		String sql = "select * from TB_ARTICLELOG a,TB_ARTICLE b  where a.ARTICLE_ID=b.ARTICLE_ID  and a.USERID=? and a.ARTICLE_ID=? ";
		List<ArticleLog> list= this.baseDao.findObjListBySql(sql, ArticleLog.class,userid,articleid  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @purpose：查询最新一条书籍阅读记录
	 * @param userid
	 * @return
	 * @return ArticleLog
	 * @author liuzhen
	 * @Time：2018-3-23 下午3:12:25
	 */
	public ArticleLog firstArticleLog(long userid) {
		String sql = "SELECT * from tb_articlelog where USERID = ? ORDER BY UPDATE_TIME DESC LIMIT 1";
		List<ArticleLog> list= this.baseDao.findObjListBySql(sql, ArticleLog.class,userid  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}

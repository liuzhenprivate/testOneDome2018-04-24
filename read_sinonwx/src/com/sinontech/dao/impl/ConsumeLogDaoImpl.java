package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ConsumeLog;

@Repository("consumeLogdao")
public class ConsumeLogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveConsumeLog(ConsumeLog consumeLog){
		this.baseDao.save(consumeLog);
	}
 
	public List<ConsumeLog> getConsumeLogByUserId(long userid ){
		String sql = "select * from TB_CONSUMELOG where USERID=?   ";
		List<ConsumeLog> list= this.baseDao.findObjListBySql(sql, ConsumeLog.class,userid  );
		return list;
	}
	
	public List<ConsumeLog> getConsumeLogByUserIdAndArticleId(long userid,long articleid){
		String sql = "select * from TB_CONSUMELOG where USERID=?  and ARTICLE_ID=? order by CONSUMELOG_ID desc ";
		List<ConsumeLog> list= this.baseDao.findObjListBySql(sql, ConsumeLog.class,userid ,articleid );
		return list;
	}
	
	/**
	 * 
	 * @purpose：更新阅读消费记录表
	 * @param consumeLog
	 * @return void
	 * @author liuzhen
	 * @Time：2018-3-11 上午11:07:30
	 */
	public void updateConsumeLog(ConsumeLog consumeLog) {
		this.baseDao.update(consumeLog);
	}
	/**
	 * 
	 * @purpose：查询本章节是否有消费过
	 * @param userId
	 * @param articlechapterid
	 * @return
	 * @return ConsumeLog
	 * @author liuzhen
	 * @Time：2018-3-20 下午5:29:26
	 */
	public ConsumeLog selectIsNotNull(long userId, long articlechapterid) {
		String sql = "select * from TB_CONSUMELOG where USERID=?  and ARTICLE_CHAPTER_ID = ? ";
		List<ConsumeLog> list= this.baseDao.findObjListBySql(sql, ConsumeLog.class,userId ,articlechapterid );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 
	 * @purpose：查询是否有消费过本书
	 * @param userId
	 * @param articleId
	 * @return
	 * @return ConsumeLog
	 * @author liuzhen
	 * @Time：2018-4-18 下午2:30:03
	 */
	public ConsumeLog seachWhetherReading(long userId, long articleId) {
		String sql = "select * from TB_CONSUMELOG where USERID=?  and ARTICLE_ID = ? ";
		List<ConsumeLog> list= this.baseDao.findObjListBySql(sql, ConsumeLog.class,userId ,articleId );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}
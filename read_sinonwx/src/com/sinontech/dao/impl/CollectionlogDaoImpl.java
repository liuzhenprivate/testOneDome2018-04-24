package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Collectionlog;
/**
 * 
 * @author ljj
 *
 */
@Repository("collectionlogdao")
public class CollectionlogDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	 
	/**
	 * 用途说明：通过用户和书籍ID
	 * @param userid
	 * @param articleid
	 * @return
	 * 2018年3月23日下午6:10:19
	 * @auther ljj
	 */
	public Collectionlog getCollectionlogByUIdAndAid(long userid,long articleid){
		String sql = "select * from TB_COLLECTIONLOG where USERID=? and ARTICLE_ID=? ";
		List<Collectionlog> logs = this.baseDao.findObjListBySql(sql, Collectionlog.class, userid,articleid);
		if(null!=logs && logs.size()>0){
			return logs.get(0);
		}else{
			return null;
		}
	}
	
	public void saveCollectionlog(Collectionlog log){
		this.baseDao.save(log);
	}
 
}

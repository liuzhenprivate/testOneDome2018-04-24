package com.sinontech.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Discuss;
import com.sinontech.pub.page.Page;
/**
 * 
 * @author ljj
 *
 */
@Repository("discussdao")
public class DiscussDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public void updateDiscuss(Discuss discuss) {
		this.baseDao.update(discuss);
	}
	public Discuss getDiscussById(long id){
		String sql = "select * from TB_DISCUSS where DISCUSS_ID=? ";
		List<Discuss> discusss = this.baseDao.findObjListBySql(sql, Discuss.class, id);
		if(null!=discusss && discusss.size()>0){
			return discusss.get(0);
		}else{
			return null;
		}
	}
	public void saveDiscuss(Discuss discuss){
		this.baseDao.save(discuss);
	}
 
	public List<Discuss> getDiscussByArticleId(long articleId ){
		String sql = "select * from TB_DISCUSS where ARTICLE_ID=?  order by DISCUSS_ID desc ";
		List<Discuss> list= this.baseDao.findObjListBySql(sql, Discuss.class,articleId  );
		return list;
	}
	/**
	 * 用途说明：分页的方式获取
	 * @param articleId
	 * @param curpageno
	 * @param pagesize
	 * @return
	 * 2018年3月1日下午2:04:46
	 * @auther ljj
	 */
	public List<Discuss> getPageDiscussByArticleId(long articleId,int curpageno,int pagesize){
//		String sql = "select * from TB_DISCUSS where ARTICLE_ID=?  order by DISCUSS_ID desc ";
		DetachedCriteria dc = DetachedCriteria.forClass(Discuss.class,"a");
		dc.add(Restrictions.eq("a.articleId", articleId));
		dc.addOrder(Order.desc("id"));
		int startnum = (curpageno-1)*pagesize;
		int endnum = curpageno*pagesize;
		Page page = this.baseDao.findByCriter(dc, true,startnum,endnum);
		List<Discuss> list= page.getPageList();
//		List<Discuss> list= this.baseDao.findObjListBySql(sql, Discuss.class,articleId  );
		return list;
	}
	/**
	 * 用途说明：统计书籍的评论总数
	 * @param articleId
	 * @return
	 * 2018年3月1日上午11:03:01
	 * @auther ljj
	 */
	public long getDiscussSumByArticleId(long articleId ){
		String sql = "select count(*) from TB_DISCUSS where ARTICLE_ID=? ";
		BigInteger count =  (BigInteger) this.baseDao.countBySql(sql,articleId);
		if(null!=count){
			return count.longValue();
		}else{
			return 0l;
		}
	} 
	/**
	 * 用途说明：统计书籍的评论人数
	 * @param articleId
	 * @return
	 * 2018年3月1日上午11:07:37
	 * @auther ljj
	 */
	public long getDiscussSumPeopleByArticleId(long articleId ){
		String sql = "select count(distinct(USERID)) from TB_DISCUSS where ARTICLE_ID=? ";
		BigInteger count =  (BigInteger) this.baseDao.countBySql(sql,articleId);
		if(null!=count){
			return count.longValue();
		}else{
			return 0l;
		}
	} 
	
}

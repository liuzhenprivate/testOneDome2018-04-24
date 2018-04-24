package com.sinontech.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinontech.pub.page.Page;

@Repository("basedao")
public class BaseDao extends HibernateDaoSupport {
	@Autowired
	public HibernateTemplate hibernateTemplate;

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 返回obj
	 * @param sql
	 * @param objects
	 * @return
	 */
	public Object countBySql(String sql, Object... objects) {
		Query query = createSQLQuery(sql);
		if(objects != null){
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.uniqueResult();
	}

	/**
	 * 查询sql
	 * 
	 * @param sql
	 * @return
	 */
	public SQLQuery createSQLQuery(final String sql) {
		SQLQuery query = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		return query;
	}

	public <T> List findObjListBySql(String sql, Class clazz, Object... objects) {
		Query query = createSQLQuery(sql).addEntity(clazz);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}

	public Page findByCriter(DetachedCriteria dc, boolean ispage,
			Integer pagelimit, Integer pagesize) {
		if (ispage) {
			Criteria criteria = dc.getExecutableCriteria(hibernateTemplate
					.getSessionFactory().getCurrentSession());
			Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			// 清空查询总数条件
			criteria.setProjection(null);
			List list = hibernateTemplate.findByCriteria(dc, pagelimit,pagesize);
			return new Page(list, totalCount);
		} else {
			List list = hibernateTemplate.findByCriteria(dc);
			return new Page(list);
		}
		
		
	}

	 
	public List findByCriter(DetachedCriteria dc) {
		return hibernateTemplate.findByCriteria(dc);
	}

	@Transactional
	public <T> void save(T clazz) {
		hibernateTemplate.save(clazz);
		System.out.println("插入成功");
	}

	@Transactional
	public <T> void saveOrUpdate(T clazz) {
		hibernateTemplate.saveOrUpdate(clazz);
	}

	@Transactional
	public <T> void update(T clazz) {
		hibernateTemplate.update(clazz);
	}

	@Transactional
	public <T> void delete(T clazz) {
		hibernateTemplate.delete(clazz);
	}
}

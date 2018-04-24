package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.DiscussDaoImpl;
import com.sinontech.modle.Discuss;
import com.sinontech.service.DiscussService;

@Service("discussservice")
public class DiscussServiceImpl implements DiscussService{

	@Autowired
	DiscussDaoImpl discussDaoImpl;

	@Override
	public void saveDiscuss(Discuss discuss) {
		this.discussDaoImpl.saveDiscuss(discuss);
	}

	@Override
	public List<Discuss> getDiscussByArticleId(long articleId) {
		return this.discussDaoImpl.getDiscussByArticleId(articleId);
	}

	@Override
	public Discuss getDiscussById(long id) {
		return this.discussDaoImpl.getDiscussById(id);
	}

	@Override
	public void updateDiscuss(Discuss discuss) {
		this.discussDaoImpl.updateDiscuss(discuss);
	}

	@Override
	public long getDiscussSumByArticleId(long articleId) {
		return this.discussDaoImpl.getDiscussSumByArticleId(articleId);
	}

	@Override
	public long getDiscussSumPeopleByArticleId(long articleId) {
		return this.discussDaoImpl.getDiscussSumPeopleByArticleId(articleId);
	}

	@Override
	public List<Discuss> getPageDiscussByArticleId(long articleId,
			int curpageno, int pagesize) {
		return this.discussDaoImpl.getPageDiscussByArticleId(articleId, curpageno, pagesize);
	}

	 

	
}

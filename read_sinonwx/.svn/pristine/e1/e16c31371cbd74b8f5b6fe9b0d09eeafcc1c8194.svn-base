package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.CollectionlogDaoImpl;
import com.sinontech.dao.impl.ZanlogDaoImpl;
import com.sinontech.modle.Collectionlog;
import com.sinontech.modle.ZanLog;
import com.sinontech.service.CollectionlogService;
import com.sinontech.service.ZanlogService;
/**
 * 
 * @author ljj
 *
 */
@Service("collectionlogservice")
public class CollectionlogServiceImpl implements CollectionlogService{

	@Autowired
	CollectionlogDaoImpl collectionlogDaoImpl;

	@Override
	public void saveCollectionlog(Collectionlog log) {
		this.collectionlogDaoImpl.saveCollectionlog(log);
	}

	@Override
	public Collectionlog getCollectionlogByUIdAndAid(long userid, long articleid) {
		return this.collectionlogDaoImpl.getCollectionlogByUIdAndAid(userid, articleid);
	}

	 
}

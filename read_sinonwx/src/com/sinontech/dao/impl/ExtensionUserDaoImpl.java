package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ExtensionUser;

@Repository("extensionuserdao")
public class ExtensionUserDaoImpl {
	
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveExtensionUser(ExtensionUser euser){
		this.baseDao.save(euser);
	}
	
	public void updateExtensionUser(ExtensionUser euser){
		this.baseDao.update(euser);
	}
	
	public ExtensionUser getExtensionUserByTIdandUid(long tid,long uid) {
		String sql = "select * from TB_EXTENSION_USER where  EXTENSION_CONTENT_ID = ? AND USER_ID = ?";
		List<ExtensionUser> list= this.baseDao.findObjListBySql(sql, ExtensionUser.class,tid,uid  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	  
}

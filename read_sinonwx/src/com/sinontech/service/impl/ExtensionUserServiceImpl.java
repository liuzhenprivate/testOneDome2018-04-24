package com.sinontech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ExtensionUserDaoImpl;
import com.sinontech.modle.ExtensionUser;
import com.sinontech.service.ExtensionUserService;

@Service("extensionUserservice")
public class ExtensionUserServiceImpl implements ExtensionUserService{
	@Autowired
	ExtensionUserDaoImpl extensionUserDaoImpl;
	
	@Override
	public void saveExtensionUser(ExtensionUser euser) {
		this.extensionUserDaoImpl.saveExtensionUser(euser);
	}

	@Override
	public ExtensionUser getExtensionUserByTIdandUid(long tid, long uid) {
		return this.extensionUserDaoImpl.getExtensionUserByTIdandUid(tid, uid);
	}

	@Override
	public void updateExtensionUser(ExtensionUser euser) {
		this.extensionUserDaoImpl.updateExtensionUser(euser);
	}

}

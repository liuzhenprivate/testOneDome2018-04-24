package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.UserInfoDaoImpl;
import com.sinontech.modle.UserInfo;
import com.sinontech.service.UserInfoService;

@Service("userfinoservice")
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	UserInfoDaoImpl userinfoDaoImpl;

	public UserInfoDaoImpl getUserinfoDaoImpl() {
		return userinfoDaoImpl;
	}

	public void setUserinfoDaoImpl(UserInfoDaoImpl userinfoDaoImpl) {
		this.userinfoDaoImpl = userinfoDaoImpl;
	}

	public UserInfo getUserInfoByOpenId(String openid) {
		return this.userinfoDaoImpl.getUserInfoByOpenId(openid);
	}

	public UserInfo getUserInfoById(long userid) {
		return this.userinfoDaoImpl.getUserInfoById(userid);
	}

	public void saveUserInfo(UserInfo userinfo) {
		this.userinfoDaoImpl.saveUserInfo(userinfo);
	}

	public void updateUserInfo(UserInfo userinfo) {
		this.userinfoDaoImpl.updateUserInfo(userinfo);
	}

	public UserInfo getUserInfoById(long webchatid,long userid){
		return this.userinfoDaoImpl.getUserInfoById(webchatid, userid);
	}

	  
	
	
}

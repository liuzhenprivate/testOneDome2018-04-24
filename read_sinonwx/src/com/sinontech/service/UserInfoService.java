package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.UserInfo;

public interface UserInfoService {

	public void saveUserInfo(UserInfo userinfo);
	public void updateUserInfo(UserInfo userinfo);
	public UserInfo getUserInfoByOpenId(String openid);
	public UserInfo getUserInfoById(long userid);
	public UserInfo getUserInfoById(long webchatid,long userid);
}

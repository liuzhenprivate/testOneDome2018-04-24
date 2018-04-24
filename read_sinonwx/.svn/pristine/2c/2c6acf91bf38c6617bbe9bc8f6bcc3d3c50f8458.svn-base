package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.UserInfo;

@Repository("userinfodao")
public class UserInfoDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void saveUserInfo(UserInfo userinfo){
		this.baseDao.save(userinfo);
	}
	
	public void updateUserInfo(UserInfo userinfo){
		this.baseDao.update(userinfo);
	}
	
	public UserInfo getUserInfoByOpenId(String openid){
		String sql = "select * from TB_USER where OPENID=?";
		List<UserInfo> userinfos = this.baseDao.findObjListBySql(sql, UserInfo.class, openid);
		if(null!=userinfos && userinfos.size()>0){
			return userinfos.get(0);
		}else{
			return null;
		}
	}
	
	public UserInfo getUserInfoById(long userid){
		String sql = "select * from TB_USER where USERID=?  ";
		List<UserInfo> userinfos = this.baseDao.findObjListBySql(sql, UserInfo.class, userid);
		if(null!=userinfos && userinfos.size()>0){
			return userinfos.get(0);
		}else{
			return null;
		}
			
	}
	public UserInfo getUserInfoById(long webchatid,long userid){
		String sql = "select * from TB_USER where WEBCHAT_ID=? and USERID=?  ";
		List<UserInfo> userinfos = this.baseDao.findObjListBySql(sql, UserInfo.class,webchatid, userid);
		if(null!=userinfos && userinfos.size()>0){
			return userinfos.get(0);
		}else{
			return null;
		}
			
	}
	
}

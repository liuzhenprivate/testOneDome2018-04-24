package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Webchat;

@Repository("webchatdao")
public class WebchatDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	 
	
	public void updateWebchat(Webchat webchat){
		this.baseDao.update(webchat);
	}
	
	public Webchat getWebchatById(long id){
		String sql = "select * from TB_WEBCHAT where WEBCHAT_ID=?";
		List<Webchat> webchats = this.baseDao.findObjListBySql(sql, Webchat.class, id);
		if(null!=webchats && webchats.size()>0){
			return webchats.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 
	 * @purpose：根据渠道id查询微信
	 * @param user_id
	 * @return
	 * @return Webchat
	 * @author liuzhen
	 * @Time：2018-3-22 下午12:34:32
	 */
	public Webchat selectFindByChannelId(long user_id) {
		String sql = "select * from TB_WEBCHAT where USER_ID=?";
		List<Webchat> webchats = this.baseDao.findObjListBySql(sql, Webchat.class,user_id);
		if(null!=webchats && webchats.size()>0){
			return webchats.get(0);
		}else{
			return null;
		}
	}
	
	 
	
}
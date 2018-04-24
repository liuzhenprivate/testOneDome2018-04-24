package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.WebchatReply;
import com.sinontech.pub.utils.DateUtil;

@Repository("webchatReplydao")
public class WebchatReplyDaoImpl {
	
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/**
	 * 用途说明：根据自动回复类型查询
	 * @param webchatId
	 * @param type
	 * @return
	 * 2018年3月23日下午1:07:12
	 * @auther ljj
	 */
	public List<WebchatReply> getWebchatReply(long webchatId,int type) {
		String sql = "select * from TB_WEBCHATREPLY where  WEBCHAT_ID = ? AND TYPE = ? order by LOSE_TIME desc ";
		List<WebchatReply> list= this.baseDao.findObjListBySql(sql, WebchatReply.class,webchatId,type  );
		return list;
	}
	
	 
	
	/**
	 * 用途说明：根据关键字查询回复内容
	 * @param webchatId
	 * @param type
	 * @param key
	 * @return
	 * 2018年3月23日下午1:09:15
	 * @auther ljj
	 */
	public List<WebchatReply> getWebchatReplyByKey(long webchatId, String key) {
		key = "%"+key+"%";
		//当前时间
		String time = DateUtil.getStringNow();
		String sql = "select * from TB_WEBCHATREPLY where KEYWORDS like ? and WEBCHAT_ID = ? "
				+ " AND TYPE = 0 and (LOSE_TIME>? or LOSE_TIME is null) order by LOSE_TIME desc  ";
		List<WebchatReply> list= this.baseDao.findObjListBySql(sql, WebchatReply.class,key,webchatId,time  );
		return list;
	}
	
	 
}

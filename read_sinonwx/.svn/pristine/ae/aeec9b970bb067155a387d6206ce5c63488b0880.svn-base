package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.HtmlModleDetail;

@Repository("htmlmodledetaildao")
public class HtmlModleDetailDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
 
	public List<HtmlModleDetail> gethtmlModleDetailByHtmlmodleId(long htmlmodleId ){
		String sql = "select * from TB_HTMLMODLE_DETAIL where HTMLMODLE_ID=? order by SORT DESC  ";
		List<HtmlModleDetail> list= this.baseDao.findObjListBySql(sql, HtmlModleDetail.class,htmlmodleId  );
		return list;
	}
	
	 
	
}

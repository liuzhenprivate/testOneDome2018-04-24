package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Label;

@Repository("labeldao")
public class LabelDaoImpl {
	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/**
	 * 
	 * @purpose：查询所有标签
	 * @return
	 * @return List<Label>
	 * @author liuzhen
	 * @Time：2018-4-13 上午10:00:48
	 */
	public List<Label> getLabelList() {
		String sql = "select * from TB_LABEL";
		List<Label> list = this.baseDao.findObjListBySql(sql, Label.class);
		return list;
	}
	
}

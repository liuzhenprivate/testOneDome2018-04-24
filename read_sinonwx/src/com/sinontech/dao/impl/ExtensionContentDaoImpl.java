package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.ExtensionContent;

@Repository("extensioncontentdao")
public class ExtensionContentDaoImpl {
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void updateExtensionContent(ExtensionContent extensionContent){
		this.baseDao.update(extensionContent);
	}
	
	/**
	 * 
	 * @purpose：返回渠道推荐列表详情
	 * @param EXTENSION_CONTENT_ID
	 * @return
	 * @return ExtensionContent
	 * @author liuzhen
	 * @Time：2018-3-17 下午2:32:26
	 */
	public ExtensionContent selectFindById(long EXTENSION_CONTENT_ID) {
		String sql = "select * from TB_EXTENSION_CONTENT where  EXTENSION_CONTENT_ID = ?";
		List<ExtensionContent> list= this.baseDao.findObjListBySql(sql, ExtensionContent.class,EXTENSION_CONTENT_ID  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
}
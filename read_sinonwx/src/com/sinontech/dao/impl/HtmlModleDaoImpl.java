package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Html;
import com.sinontech.modle.HtmlModle;

@Repository("htmlmodledao")
public class HtmlModleDaoImpl {

	@Autowired
	BaseDao baseDao;

	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public Html findHtmlByPlacType(int placType){
		String sql="select * from TB_HTML where PLACE_TYPE=? and STATE=1 ";
		List<Html> list= this.baseDao.findObjListBySql(sql, Html.class, placType);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public Html findHtmlById(long id){
		String sql="select * from TB_HTML where HTML_ID=? and STATE=1 ";
		List<Html> list= this.baseDao.findObjListBySql(sql, Html.class, id);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public HtmlModle findHtmlDetailById(long id){
		String sql="select * from TB_HTMLMODLE where HTMLMODLE_ID=?";
		List<HtmlModle> list= this.baseDao.findObjListBySql(sql, HtmlModle.class, id);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public void updateHtml(Html html){
		this.baseDao.update(html);
	}
	/**
	 * 用途说明：通过位置类型查下到配置模板信息
	 * @param placeType
	 * @return
	 * 2018年3月6日上午9:15:38
	 * @auther ljj
	 */
	public List<HtmlModle> getHtmlModleByPlaceType(int placeType ){
		String sql = "select * from TB_HTMLMODLE a,TB_HTML b where a.HTML_ID=b.HTML_ID and  b.PLACE_TYPE=? order by a.PLACE asc  ";
		List<HtmlModle> list= this.baseDao.findObjListBySql(sql, HtmlModle.class,placeType  );
		return list;
	}
	
	public List<HtmlModle> getHtmlModleByHtmlId(long htmlId ){
		String sql = "select * from TB_HTMLMODLE a where a.HTML_ID=?   order by a.PLACE asc  ";
		List<HtmlModle> list= this.baseDao.findObjListBySql(sql, HtmlModle.class,htmlId  );
		return list;
	}
	
}

package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Article;
import com.sinontech.modle.BoardInfo;

@Repository("boarddetaildao")
public class BoardDetailDaoImpl {
	
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 榜单详情列表
	 */
	public List<Article> listBoardDetail(long boardId,int boardType) {
		String sql = "SELECT ta.* FROM tb_board_detail tbd " +
				"LEFT JOIN tb_article ta " +
				"ON tbd.ARTICLE_ID = ta.ARTICLE_ID " +
				"WHERE tbd.BOARD_TYPE = ? " +
				"AND tbd.BOARD_ID = ? " +
				"AND ta.STATE = 1 " +
				"ORDER BY tbd.SORT ";
		return this.baseDao.findObjListBySql(sql ,Article.class, boardType ,boardId);
	}
	
	/*
	 * 榜单（总榜） 第一本书籍
	 */
	public Article seachBoardIfolist(long boardId) {
		String sql = "SELECT ta.* FROM tb_board_detail tbd " +
				"LEFT JOIN tb_article ta ON tbd.ARTICLE_ID = ta.ARTICLE_ID  " +
				"where tbd.BOARD_ID = ? " +
				"AND tbd.BOARD_TYPE = 0 " +
				"AND ta.STATE = 1 " +
				"ORDER BY tbd.SORT ";
		List<Article> list= this.baseDao.findObjListBySql(sql, Article.class,boardId  );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}

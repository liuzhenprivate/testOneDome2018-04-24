package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Article;
import com.sinontech.modle.Board;

@Repository("boarddao")
public class BoardDaoImpl {
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 榜单详情 listBoard(), listdowd(), listeDitor()
	 */
	public List<Board> listBoard() {
		String sql = "select * from TB_BOARD ORDER BY SORT limit 0,3";
		return  this.baseDao.findObjListBySql(sql ,Board.class );
	}
	public Board listdowd() {
		String sql = "select * from TB_BOARD ORDER BY SORT limit 3,4  ";
		List<Board> list = this.baseDao.findObjListBySql(sql ,Board.class );
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public List<Board> listeDitor() {
		String sql = "select * from TB_BOARD  where SORT > 4 ORDER BY SORT";
		return  this.baseDao.findObjListBySql(sql ,Board.class );
	}
	
	
	/*
	 * 点击排行榜时榜单点击量自动+1
	 */
	public void increase(long boardId) {
		String sql = "select * from TB_BOARD where BOARD_ID = ?";
		List<Board> list = this.baseDao.findObjListBySql(sql ,Board.class,boardId );
		if(null!=list && list.size()>0){
			Board board = list.get(0);
			board.setClickNumber(board.getClickNumber()+1);
			this.baseDao.update(board);
		}
	}
	
}

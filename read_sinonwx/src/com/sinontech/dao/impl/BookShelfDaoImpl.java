package com.sinontech.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinontech.dao.BaseDao;
import com.sinontech.modle.Board;
import com.sinontech.modle.BookShelf;

@Repository("bookShelfdao")
public class BookShelfDaoImpl {
	@Autowired
	BaseDao baseDao;
	
	public void setBaseBo(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 查询用户书架列表
	 */
	public List<BookShelf> bookShelfList(long userid) {
		String sql = "select * from tb_bookshelf tbs,tb_article ta where USERID = ? " +
				"AND tbs.ARTICLE_ID = ta.ARTICLE_ID " +
				"AND  ta.STATE = 1 " +
				"ORDER BY tbs.BOOKSHELF_ID DESC";
		return  this.baseDao.findObjListBySql(sql ,BookShelf.class ,userid);
	}
	/**
	 * 用途说明：通过用户和书架ID查询是否已收藏或者已订阅
	 * @param userid
	 * @param bookShelfId
	 * @return
	 * 2018年2月28日下午5:24:30
	 * @auther ljj
	 */
	public BookShelf findBookShelf(long userid,long articleId){
		String sql = "select * from TB_BOOKSHELF where USERID = ? AND ARTICLE_ID = ?";
		List<BookShelf> list = this.baseDao.findObjListBySql(sql ,BookShelf.class ,userid,articleId);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/*
	 * 删除书架书籍
	 */
	public void del(long bookShelfId) {
			BookShelf bookShelf = new BookShelf();
			bookShelf.setId(bookShelfId);
			this.baseDao.delete(bookShelf);
	}
	
	/*
	 * 书架新增书籍
	 */
	public void add(BookShelf bookShelf) {
		this.baseDao.save(bookShelf);
	}
	
	/*
	 * 修改书架
	 */
	public void update(BookShelf bookShelf) {
		this.baseDao.update(bookShelf);
	}
	
}

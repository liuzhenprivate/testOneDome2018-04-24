package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.BookShelfDaoImpl;
import com.sinontech.modle.BookShelf;
import com.sinontech.service.BookShelfService;

@Service("bookShelfservice")
public class BookShelfServiceImpl implements BookShelfService {
	@Autowired
	BookShelfDaoImpl bookShelfDaoImpl;
	
	/*
	 * 查询用户书架列表
	 */
	@Override
	public List<BookShelf> bookShelfList(long userid) {
		return this.bookShelfDaoImpl.bookShelfList(userid);
	}
	/*
	 * 删除书架书籍
	 */
	@Override
	public void del(long bookShelfId) {
		this.bookShelfDaoImpl.del(bookShelfId);
	}
	
	/*
	 * 查询用户书架里是否有指定articleId的书籍
	 */
	@Override
	public BookShelf findBookShelf(long userid, long articleId) {
		return this.bookShelfDaoImpl.findBookShelf(userid, articleId);
	}
	
	/*
	 * 新增书架书籍
	 */
	public void add(BookShelf bookShelf){
		this.bookShelfDaoImpl.add(bookShelf);
	}
	
	/*
	 * 修改书架
	 */
	public void update(BookShelf bookShelf){
		this.bookShelfDaoImpl.update(bookShelf);
		
	}
}

package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.BookShelf;

public interface BookShelfService {
	public List<BookShelf> bookShelfList(long userid);
	
	public void del(long bookShelfId) ;
	
	public BookShelf findBookShelf (long userid,long articleId);
	
	public void add(BookShelf bookShelf);
	
	public void update(BookShelf bookShelf);
}

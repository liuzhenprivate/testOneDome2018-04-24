package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.Article;
import com.sinontech.modle.BoardInfo;

public interface BoardDetailService {
	public List<Article> listBoardDetail(long boardId,int boardType);
	
	public Article seachBoardIfolist(long boardId);
}
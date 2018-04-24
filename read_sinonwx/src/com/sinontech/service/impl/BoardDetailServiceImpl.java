package com.sinontech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.BoardDetailDaoImpl;
import com.sinontech.modle.Article;
import com.sinontech.modle.BoardInfo;
import com.sinontech.service.BoardDetailService;
import java.util.List;

@Service("boardDetailservice")
public class BoardDetailServiceImpl implements BoardDetailService {
	@Autowired
	BoardDetailDaoImpl boardDetailDaoImpl;
	
	@Override
	public List<Article> listBoardDetail(long boardId,int boardType) {
		return this.boardDetailDaoImpl.listBoardDetail(boardId,boardType);
	}
	@Override
	public Article seachBoardIfolist(long boardId) {
		return this.boardDetailDaoImpl.seachBoardIfolist(boardId);
	}

}

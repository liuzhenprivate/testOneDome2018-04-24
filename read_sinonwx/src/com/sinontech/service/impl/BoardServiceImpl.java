package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.BoardDaoImpl;
import com.sinontech.modle.Board;
import com.sinontech.service.BoardService;

@Service("boardservice")
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDaoImpl boardDaoImpl;
	
	@Override
	public List<Board> listBoard() {
		return this.boardDaoImpl.listBoard();
	}
	@Override
	public Board listdowd() {
		return this.boardDaoImpl.listdowd();
	}
	@Override
	public List<Board> listeDitor() {
		return this.boardDaoImpl.listeDitor();
	}
	@Override
	public void increase(long boardId) {
		this.boardDaoImpl.increase(boardId);
	}
	
}

package com.app.service.board;

import java.util.List;

import com.app.dto.board.Board;

public interface BoardService {
	  void addBoard(Board board);
	  
	  List<Board> getAllBoards(); // 목록 조회 기능

	void deleteBoard(int id);
}

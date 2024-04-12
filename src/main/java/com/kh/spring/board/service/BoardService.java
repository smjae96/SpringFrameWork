package com.kh.spring.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

public interface BoardService {
	
	// * 게시글 목록 조회(+페이징 처리)	-R
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
	
	// * 게시글 상세 조회				-R
	int increaseCount(int boardNo);
	Board selectBoard(int boardNo);
	
	// * 게시글 작성					-C
	int insertBoard(Board b);
	
	// * 게시글 수정					-U
	int updateBoard(Board b);
	
	// * 게시글 삭제					-U/D
	int deleteBoard(int boardNo);
	
	// * 댓글 목록 조회 (ajax)			-R
	ArrayList<Reply> selectReplyList(int boardNo);
	
	// * 댓글 작성 (ajax)				-C
	int insertReply(Reply reply);

	
	
	

	
	
	// 게시글 검색
	int selectSearchCount(HashMap<String, String> map);
	
	// 검색한 게시글 조회
	ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi);
}

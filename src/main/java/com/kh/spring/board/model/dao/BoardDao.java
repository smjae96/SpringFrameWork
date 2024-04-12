package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;

@Repository
public class BoardDao {

	public int selectListCount(SqlSessionTemplate sqlSession) {
		int result = sqlSession.selectOne("boardMapper.selectListCount");
		return result;
		
	}

	public ArrayList<Board> selectList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		// - 현재 페이지 번호 : currentPage
		// - 표시할 게시글 수 : boardLimit
		/*
		 * offset : DB에서 가지고 온 리스트에서 몇 번째부터 조회할 것인지에 대한 값
		 * 			ex) 6번 게시글부터 조회할거라면 offset = 5 이다.
		 * 
		 * limit : offset으로부터 몇 개를 조회할 것인지에 대한 값 ( 한 페이지에 몇 개의 게시글이 표시되는지)
		 */
		
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rBounds);
	}

}

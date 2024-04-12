package com.kh.spring.board.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.service.BoardService;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;



@Controller
public class BoardController {

	@Autowired
	private BoardService bService;
	
	/*
	 * - 메뉴에서 "자유게시판" 클릭 시 		/list.bo => 기본적으로 첫번째 페이지 요청
	 * - "자유게시판" 페이지에서 페이징바 클릭 시 /list.bo?cpage=x
	 */
	@RequestMapping("list.bo")
	public ModelAndView selectList(@RequestParam(value="cpage" , defaultValue="1") int currentPage, ModelAndView mv) {
		
		// 전체 게시글 수 조회
		int listCount = bService.selectListCount();
		// PageInfo 객체 생성
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		
		// 게시글 목록 조회 => service
		ArrayList<Board> list = bService.selectList(pi);
		// 페이징바 정보, 게시글 목록 정보 request 영역에 담아 자유게시판 페이지 포워딩
		
		// mv.addObject("pi", pi);
		// mv.addObject("list", list);
		// mv.setViewName("board/boardListView");
		
		/* ModelAndView 객체의 경우 메소드 체이닝 가능!*/
		mv.addObject("pi", pi).addObject("list", list).setViewName("board/boardListView");
		return mv;
		

	}
}

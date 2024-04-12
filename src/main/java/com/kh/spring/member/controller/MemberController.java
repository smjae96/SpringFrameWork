package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.vo.Member;
import com.kh.spring.member.service.MemberService;

@Controller // Controller 타입의 어노테이션 작성 시 빈 스캐닝을 통해 자동으로 빈 등록이 됨.
public class MemberController {

	// private MemberService mService = new MemberServiceImpl();
	@Autowired // DI(의존성 주입) 특징
	private MemberService mService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	// 로그인 관련 메소드
	/*
	 * @RequestMapping(value="login.me") // RequestMapping 타입의 어노테이션 작성 시
	 * HandlerMapping 등록 public void loginMember() {
	 * 
	 * }
	 * 
	 * // 회원가입 관련 메소드
	 * 
	 * @RequestMapping(value="insert.me") public void insertMember() {
	 * 
	 * }
	 */
	/*
	 * * 요청 시 전달되는 데이터에 대한 처리 방법
	 * 
	 * 1) HttpServletRequest 이용 (기존 jsp/servlet 방식) : 해당 메소드의 매개변수로
	 * HttpServletRequest를 작성하면 스프링 컨테이너가 해당 메소드 호출 시 자동으로 해당 객체를 생성해서 전달해줌
	 */
	/*
	 * @RequestMapping(value="login.me") 
	 * public String loginMember(HttpServletRequest request) { 
	 * String userId = request.getParameter("id"); String userPwd = request.getParameter("pwd");
	 * 
	 * System.out.println("ID---->" + userId); System.out.println("PWD---->" +
	 * userPwd);
	 * 
	 * return "main"; }
	 */
	/*
	 * 2) @RequestParam 어노테이션 이용 => request.getParameter("키값") : 밸류 // 이 작업을 대신 해주는
	 * 역할
	 */
//	@RequestMapping("login.me")		// value 속성 생략 가능. (url mapping 값만 작성시)
//	public String loginMember(@RequestParam(value="id", defaultValue="xx") String userId,
//								@RequestParam("pwd") String userPwd) {
//		System.out.println("ID----->" + userId);
//		System.out.println("PWD---->" + userPwd);
//		
//		return "main";
//	}

	/*
	 * 3) @RequestParam 어노테이션 생략 => 주의!! 매개변수명을 name값(요청 시 데이터의 키값)과 동일하게 작성해야 자동으로
	 * 값이 주입됨
	 */
	/*
	 * @RequestMapping("login.me") 
	 * public String loginMember(String id, String pwd) { 
	 *  	System.out.println("ID---->"+id);
	 *  	System.out.println("PWD--->"+pwd); 
	 * 		return "main"; 
	 * }
	 */

	/*
	 * 4) 커멘드 객체 방식 요청 시 전달되는 데이터를 vo클래스 타입(ex. Member)으로 받고자 하는 경우 매개변수로 해당
	 * vo클래스타입을 작성한 후 전달되는 데이터의 키값을(name 속성) 받고자하는 vo객체의 필드명과 일치해줘야 함
	 * 
	 * 스프링 컨테이너가 해당 객체를 기본생성자로 생성 후 setter 메소드를 사용하여 요청 시 전달되는 값을 해당 필드에 저장
	 * 
	 * 주의!! 반드시 name속성(키값)값과 필드명이 동일해야함!
	 */

	/*
	 * * 요청 처리가 끝난 후 응답페이지로 포워딩 또는 url재요청, 응답 데이터를 담는 방법
	 * 
	 * 1) 스프링에서 제공하는 Model 객체를 사용 포워딩할 페이지로 전달하고자 하는 데이터를 맵 형식(key/value)으로 담을 수 있는
	 * 영역 Model 객체 => requestScope * setAttribute 메소드가 아닌 addAttribute 메소드 이용
	 */
//	@RequestMapping("login.me")
//	public String loginMember(Member m, HttpSession session, Model model ) {
//		
//		System.out.println(("ID---->"+m.getUserId()));
//		System.out.println(("PWD--->"+m.getUserPwd()));
//		
//		Member loginUser = mService.loginMember(m);

//		if(loginUser == null) {		// 로그인 실패 => 에러메시지 (request)담아 에러페이지 응답
//			model.addAttribute("errorMsg", "로그인 실패!");
//			return "common/errorPage";
//		} else {		// 로그인 성공 => 로그인 정보를 (session)에 담아 메인페이지로 이동
//			session.setAttribute("loginUser", loginUser);
//			return "redirect:/";
//		}

	/*
	 * 2) 스프링에서 제공하는 ModelAndView 객체 이용
	 * 
	 * Model : 데이터를 key/value 형태로 담을 수 있는 공간 (단독 사용 가능) View : 응답뷰에 대한 정보를 담을 수 있는
	 * 공간 (단독 사용 불가능) => ModelAndView 사용 * addAttribute 메소드가 아닌 addObject 메소드 이용
	 */
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {

		/**
		 * 암호화 전 방식 Member loginUser = mService.loginMember(m);
		 * 
		 * if(loginUser == null) { // 로그인 실패 => 에러메시지 (request)담아 에러페이지 응답
		 * mv.addObject("errorMsg", "로그인 실패!"); mv.setViewName("common/errorPage"); }
		 * else { // 로그인 성공 => 로그인 정보를 (session)에 담아 메인페이지로 이동
		 * session.setAttribute("loginUser", loginUser); mv.setViewName("redirect:/"); }
		 * return mv;
		 **/

		// 암호화 처리 후 방식
		// - Member m 객체 userId : 사용자가 입력한 아이디
		// userPwd : 사용자가 입력한 비밀번호 (평문)
		Member loginUser = mService.loginMember(m);
		// - Member loginUser (아이디로 조회한 정보)
		// userPwd : DB에 저장된 비밀번호 (암호문)
		if (loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			// 로그인 성공 => 로그인 정보를 (session)담아 메인페이지로 이동
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} else {
			// 로그인 실패 => 에러메시지 (request)담아 에러페이지 응답
			mv.addObject("errorMsg", "로그인 실패!");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping("logout.me")
	public ModelAndView logoutMember(HttpSession session, ModelAndView mv) {

		session.setAttribute("alertMsg", "로그아웃 성공");
		session.removeAttribute("loginUser");
		mv.setViewName("redirect:/");
		return mv;
	}

	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}

	@RequestMapping("insert.me")
	public ModelAndView insertMember(Member m, HttpSession session, ModelAndView mv) {

		// * 발생한 문제
		// 1) 한글 깨짐 => 인코딩필터 등록 (web.xml)
		// 2) 나이를 입력하지 않을 경우 빈 문자열("")이 전달되면서
		// int형 필드에 담을 수 없음 => 400 에러 (Member 클래스의 age필드 타입 변경: int -> String)
		// 3) 비밀번호 값이 사용자가 입력한 값 그대로(평문)
		// => Bcrypt 방식의 암호화를 사용하여 암호문으로 변경
		// [1] 스프링 시큐리티 모듈에서 제공 (pom.xml 라이브러리 등록 필요)
		// [2] BcryptPassWordEncoder 클래스 빈으로 등록 (xml파일)
		// [3] web.xml 파일에 spring-security.xml 파일을 프리로딩(pre-loading) 할 수 있도록 설정

		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		m.setUserPwd(encPwd);

		int result = mService.insertMember(m);
		if (result != 0) {
			session.setAttribute("alertMsg", "회원가입 성공");
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("errorMsg", "회원가입 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping("myPage.me")
	public String myPage() {
		return "member/myPage";
	}

	@RequestMapping("update.me")
	public ModelAndView updateMember(Member m, HttpSession session, ModelAndView mv) {

		int result = mService.updateMember(m);
		if (result != 0) {
			session.setAttribute("loginUser", mService.loginMember(m));
			session.setAttribute("alertMsg", "회원정보 수정 성공");
			mv.setViewName("member/myPage");
		} else {
			mv.addObject("errorMsg", "회원정보 수정 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping("delete.me")
	public ModelAndView deleteMember(Member m, HttpSession session, ModelAndView mv) {
		/* session 사용하지 않고 jsp에 hidden 타입 input 태그로 id 받아오는 방법 */
//		Member checkMember = mService.loginMember(m);
//		if (checkMember != null && bcryptPasswordEncoder.matches(m.getUserPwd(), checkMember.getUserPwd())) {
//			int result = mService.deleteMember(m);
//
//			if (result != 0) {
//				session.setAttribute("alertMsg", "회원 탈퇴되었습니다.");
//				session.removeAttribute("loginUser");
//				mv.setViewName("redirect:/");
//			} else {
//				mv.addObject("errorMsg", "회원 탈퇴 실패!");
//				mv.setViewName("common/errorPage");
//			}
//		} else {
//			mv.addObject("errorMsg", "회원 탈퇴 실패!");
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
		
		/* session 사용해서 로그인 된 유저의 id정보와 pwd 정보 받아오는 방법 */
		Member loginUser = (Member)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		String userPwd = loginUser.getUserPwd();
		
		m.setUserId(userId);
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), userPwd)) {
			int result = mService.deleteMember(m);
			if (result != 0) {
				session.setAttribute("alertMsg", "회원 탈퇴되었습니다.");
				session.removeAttribute("loginUser");
				mv.setViewName("redirect:/");
			} else {
				mv.addObject("errorMsg", "회원 탈퇴 실패!");
				mv.setViewName("common/errorPage");
			}
		} else {
			mv.addObject("alertMsg", "잘못된 비밀번호입니다! 다시 입력해주세요.");
			mv.setViewName("member/myPage");
		}
		return mv;
	}
}

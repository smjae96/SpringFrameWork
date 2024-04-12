package com.kh.spring.member.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	// 로그인 서비스 => SELECT
	Member loginMember(Member m);
	
	// 회원가입 서비스 => INSERT
	int insertMember(Member m);
	
	// 회원 정보 수정 서비스 => UPDATE
	int updateMember(Member m);
	
	// 회원 탈퇴 서비스 => UPDATE
	int deleteMember(Member m);
	
	// 아이디 중복체크 서비스 => SELECT
	int checkUserId(String userId);
}

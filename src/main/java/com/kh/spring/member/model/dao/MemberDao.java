package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository		// DB와 직접적인 접근을 하는 클래스에 부여
public class MemberDao {

	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		Member loginUser = sqlSession.selectOne("memberMapper.loginMember", m);
		return loginUser;
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		int result = sqlSession.insert("memberMapper.insertMember", m);
		return result;
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		int result = sqlSession.update("memberMapper.updateMember", m);
		return result;
	}

	public int deleteMember(SqlSessionTemplate sqlSession, Member m) {
		int result = sqlSession.update("memberMapper.deleteMember", m);
		return result;
	}

}

package com.kh.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

// @Component 만 추가해도 빈 등록 가능. @Service는 좀 더 구체화하는 것
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao mDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member loginMember(Member m) {
		// * 기존 방식
		// SqlSession session = getSession();
		// new MemberDao().loginMember(session, m);
		Member loginUser = mDao.loginMember(sqlSession, m);
		return loginUser;
		
	}

	@Override
	public int insertMember(Member m) {
		
		int result = mDao.insertMember(sqlSession, m);
		return result;
	}

	@Override
	public int updateMember(Member m) {
		
		int result = mDao.updateMember(sqlSession, m);
		return result;
	}

	@Override
	public int deleteMember(Member m) {
		int result = mDao.deleteMember(sqlSession, m);
		return result;
	}

	@Override
	public int checkUserId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

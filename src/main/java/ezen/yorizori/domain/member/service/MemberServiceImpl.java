package ezen.yorizori.domain.member.service;

import java.sql.SQLException;
import java.util.List;

import ezen.yorizori.domain.common.factory.DaoFactory;
import ezen.yorizori.domain.member.dao.MemberDao;
import ezen.yorizori.domain.member.dto.Member;

/**
 * 회원 관련 비즈니스 메소드 구현, 트랜잭션 관리 RDB, Open API 활용
 * 
 * @Author 김재훈
 * @Date 2023. 3. 13.
 */
public class MemberServiceImpl implements MemberService {
	// RDB 정보 조회
	private MemberDao memberDao = DaoFactory.getInstance().getMemberDao();
	// Open API 정보 조회
//	private MemberDao memberDao = new APIMemberDao();

	@Override
	public void registerMember(Member member) throws Exception {
		memberDao.create(member);
	}

	@Override
	public List<Member> getMembers() throws Exception {
		return memberDao.findAll();
	}

	@Override
	public Member isMember(String id, String password) throws Exception {
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);

		member = memberDao.isMember(member);
		return member;
	}
}

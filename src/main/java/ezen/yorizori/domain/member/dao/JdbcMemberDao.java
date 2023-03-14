package ezen.yorizori.domain.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import ezen.yorizori.domain.common.factory.DaoFactory;
import ezen.yorizori.domain.member.dto.Member;

public class JdbcMemberDao implements MemberDao {

	private DataSource dataSource;
	
	public JdbcMemberDao(DataSource dataSource){
		this.dataSource = dataSource; 
	}

	@Override
	public void create(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO member").append(" VALUES(?, ?, ?, ?, ?, SYSDATE)");
		try {
			// 커넥션을 풀링하고 있는 커넥션 팩토리로부터 사용하지 않고 있는 커넥션 획득
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getAge());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e; // 호출한 쪽에 그대로 던져준다. 휙~
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				// 사용 후 커넥션 반납 (닫는 것 아님. 반납임!)
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public Member isMember(String id, String password) throws SQLException {
		Member loginMember = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT member_id, name, email, age, regdate")
		.append(" FROM member")
		.append(" WHERE member_id=? AND password =?");
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				loginMember = makeMember(rs);
			}

		} /*
			 * catch (SQLException e) { e.printStackTrace(); } 캐치는 생략 가능
			 */ finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return loginMember;
	}

	@Override
	public List<Member> findAll() throws SQLException {
		List<Member> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT member_id, name, email, age, regdate")
		.append(" FROM member")
		.append(" ORDER BY regdate DESC");

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = makeMember(rs);
				list.add(member);
			}

		} /*
			 * catch (SQLException e) { e.printStackTrace(); }
			 */ finally { // finally 쓰는 이유는 얘네들 닫아주기 위해서.
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 회원 상세 정보
	public Member getMember(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT member_id, name, email, age, regdate")
		.append(" FROM member")
		.append(" WHERE member_id=?");
		
		
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(sb.toString());
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		
		String id = null;
		String name = null;
		String email = null;
		int age = 0;
		Date regDate = null;
		
		if (rs.next()) {
			id = rs.getString("member_id");
			name = rs.getString("name");
			email = rs.getString("email");
			age = rs.getInt("age");
			regDate = rs.getDate("regdate");
		}
		
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setEmail(email);
		member.setAge(age);
		member.setRegdate(regDate);
		
		return member;
		
	}

	// 헬퍼 메소드
	private Member makeMember(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getString("member_id"));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString("email"));
		member.setAge(rs.getInt("age"));
		member.setRegdate(rs.getDate("regdate"));
		return member;
	}
	
	// 테스트를 위한 임시 main
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		MemberRepository repository = new JdbcMemberRepository();
		MemberDao dao = DaoFactory.getInstance().getMemberDao();
		Member loginMember = dao.isMember("tester", "1111");
		// 비회원인 경우
		if (loginMember == null) {
			System.out.println("회원이 아닙니다.");
		} else {
			System.out.println(loginMember.toString());
		}

		// 전체목록 조회
		List<Member> list = dao.findAll();
		System.out.println(list);
		
		// 특정 회원 정보 조회
		Member member = ((JdbcMemberDao) dao).getMember("bangry");
		System.out.println(member);

	}

}

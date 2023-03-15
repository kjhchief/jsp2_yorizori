package ezen.yorizori.domain.cookbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ezen.yorizori.domain.cookbook.dto.Cookbook;

public class JdbcCookbookDao implements CookbookDao {
	
	private DataSource dataSource;
	
	public JdbcCookbookDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(Cookbook cookbook) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO cookbook (book_id, name, describe, author_id)")
		.append(" VALUES(cookbook_seq.NEXTVAL, ?, ?, ?)");
		
		try {
			// 커넥션을 풀링하고 있는 커넥션 팩토리로부터 사용하지 않고 있는 커넥션 획득
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, cookbook.getName());
			pstmt.setString(2, cookbook.getDescribe());
			pstmt.setString(3, cookbook.getAuthorId());
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

}

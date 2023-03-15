package ezen.yorizori.domain.cookbook.dao;

import java.sql.SQLException;

import ezen.yorizori.domain.cookbook.dto.Cookbook;

public interface CookbookDao {
	// 요리책 등록
	public void create(Cookbook cookbook) throws SQLException;
	
	
	
}

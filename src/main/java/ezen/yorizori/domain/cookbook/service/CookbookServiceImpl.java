package ezen.yorizori.domain.cookbook.service;

import java.sql.SQLException;

import ezen.yorizori.domain.common.factory.DaoFactory;
import ezen.yorizori.domain.cookbook.dao.CookbookDao;
import ezen.yorizori.domain.cookbook.dto.Cookbook;

public class CookbookServiceImpl implements CookbookService {
	
	private CookbookDao cookbookDao = DaoFactory.getInstance().getCookbookDao();

	@Override
	public void registerCookbook(Cookbook cookbook) throws RuntimeException {
		try {
			cookbookDao.create(cookbook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}

	}

}

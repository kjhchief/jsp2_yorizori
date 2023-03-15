package ezen.yorizori.domain.cookbook.service;

import ezen.yorizori.domain.cookbook.dto.Cookbook;
import ezen.yorizori.domain.member.dto.Member;

public interface CookbookService {
	// 요리책 등록
	public void registerCookbook(Cookbook cookbook) throws RuntimeException;
}

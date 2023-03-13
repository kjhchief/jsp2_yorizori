package ezen.yorizori.web.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.yorizori.domain.member.dto.Member;
import ezen.yorizori.domain.member.service.MemberService;
import ezen.yorizori.domain.member.service.MemberServiceImpl;

/**
 * 로그인 화면과 로그인 처리 컨트롤러
 */
@WebServlet(value={"/member/loginup.do"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 비즈니스 객체 사용
	// private MemberService memberService = new MemberServiceImpl();// 실제 동작은 MemberServiceImpl이 함

	/**
	 * 로그인 화면 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 실제 로그인 기능
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

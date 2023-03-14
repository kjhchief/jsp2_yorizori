package ezen.yorizori.web.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ezen.yorizori.domain.member.dto.Member;
import ezen.yorizori.domain.member.service.MemberService;
import ezen.yorizori.domain.member.service.MemberServiceImpl;

/**
 * 홈페이지 메인화면 요청시 처리 컨트롤러
 */
@WebServlet("/index.do")
public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// 비즈니스 객체 사용
//	private MemberService memberService = new MemberServiceImpl();// 실제 동작은 MemberServiceImpl이 함

	/**
	 * 홈페이지 화면 처리
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 다양한 서비스 객체 사용.
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		rd.forward(request, response);
	}

}

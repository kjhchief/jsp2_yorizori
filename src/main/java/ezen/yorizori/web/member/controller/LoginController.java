package ezen.yorizori.web.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ezen.yorizori.domain.member.dto.Member;
import ezen.yorizori.domain.member.service.MemberService;
import ezen.yorizori.domain.member.service.MemberServiceImpl;
import ezen.yorizori.web.common.YZRuntimeException;

/**
 * 로그인 화면과 로그인 처리 컨트롤러
 */
@WebServlet(value={"/member/login.do", "/member/logout.do"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 비즈니스 객체 사용
	private MemberService memberService = new MemberServiceImpl();// 실제 동작은 MemberServiceImpl이 함
	
	private String referer;

	/**
	 * 로그인 화면 / 로그아웃 처리
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referer = request.getHeader("referer");
		
		// member/login.do
		String requestURI= request.getRequestURI();
		// login.do
		String uri = requestURI.substring(requestURI.lastIndexOf("/") + 1);
		RequestDispatcher rd = null;
		// 로그인 화면 요청시
		if(uri.equals("login.do")) {
			rd = getServletContext().getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp");
			rd.forward(request, response);
		}else { // 로그아웃 요청시
			request.getSession().invalidate();
			response.sendRedirect("/");
		}
	}
	
	/**
	 * 실제 로그인 기능
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("id");
		String loginPw = request.getParameter("password");
		String saveId = request.getParameter("saveid");
		
		Member loginMember = memberService.isMember(loginId, loginPw);
		// 회원인 경우
		if(loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember); //키, 밸류 쌍. 세션에 멤버 객체를 로그인 멤버로 저장. 세션에 정보를 저장한 것	
			if(saveId != null) {
				Cookie cookie = new Cookie("saveId", loginId);
				cookie.setMaxAge(60*60*24*100); //100일 저장
				cookie.setPath("/"); // 어느 위치에서든 쿠키 사용
				response.addCookie(cookie);
			}
//			response.sendRedirect("/");
			response.sendRedirect(referer);
		}else { // 회원이 아닌 경우
			// 비즈니스 로직 예외 강제 발생
			throw new YZRuntimeException("사용자 로그인에 실패하였습니다. <br>로그인 정보를 확인해주세요.");
		}
	}

}

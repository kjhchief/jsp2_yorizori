package ezen.yorizori.web.cookbook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ezen.yorizori.domain.cookbook.dto.Cookbook;
import ezen.yorizori.domain.cookbook.service.CookbookService;
import ezen.yorizori.domain.cookbook.service.CookbookServiceImpl;
import ezen.yorizori.domain.member.dto.Member;
import ezen.yorizori.domain.member.service.MemberService;
import ezen.yorizori.domain.member.service.MemberServiceImpl;
import ezen.yorizori.web.common.YZRuntimeException;

/**
 * Servlet implementation class RegistController
 */
@WebServlet("/cookbook/regist.do")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CookbookService cookbookService = new CookbookServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	
	// 요리책 등록 화면만 보여주는 기능
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		if(member != null) {
		// 요리책 등록 화면으로 포워드
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/cookbook/cookbookForm.jsp");
		rd.forward(request, response);
		} else {
			throw new YZRuntimeException("요리책 등록은 로그인된 사용자만 접근할 수 있습니다.", "/cookbook/regist.do");
		}
	}
	
	// 실질적인 등록 기능
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session= request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		
		if(member != null) {
			String name = request.getParameter("name");
			String authorId = request.getParameter("authorid");
			String describe = request.getParameter("describe");
			
			Cookbook cookbook = new Cookbook();
			cookbook.setName(name);
			cookbook.setAuthorId(authorId);
			cookbook.setDescribe(describe);
			
			cookbookService.registerCookbook(cookbook);
//			response.sendRedirect("/");
			
			System.out.println(cookbook.toString());
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/cookbook/cookbookForm.jsp");
			rd.forward(request, response);	
		}else {
			throw new YZRuntimeException("요리책 등록은 로그인된 사용자만 접근할 수 있습니다.", "/cookbook/regist.do");
		}
		
		
		
		
		
		
	}

}

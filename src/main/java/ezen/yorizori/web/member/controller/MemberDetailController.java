package ezen.yorizori.web.member.controller;

import java.io.IOException;
import java.util.List;

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
import ezen.yorizori.web.common.YZRuntimeException;

/**
 * 회원 목록 요청 처리
 */
@WebServlet("/member/detail.do")
public class MemberDetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 비즈니스 객체 사용
	private MemberService memberService = new MemberServiceImpl();// 실제 동작은 MemberServiceImpl이 함

	/**
	 * 회원 목록 처리
	 */
	// 회원 상세
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		String id = req.getParameter("id");
		
		HttpSession session= req.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		
		if(member != null) {
			member = memberService.getMember(id);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp");
			rd.forward(req, resp);	
		}else {
			throw new YZRuntimeException("회원상세는 로그인된 사용자만 접근할 수 있습니다.", "/member/detail.do");
		}
	}

}

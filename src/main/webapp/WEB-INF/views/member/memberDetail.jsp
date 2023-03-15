<%@page import="ezen.yorizori.domain.member.dto.Member"%>
<%@page import="ezen.yorizori.domain.common.factory.DaoFactory"%>
<%@page import="ezen.yorizori.domain.member.dao.MemberDao"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="java.util.List"%>

<%
MemberDao repository = DaoFactory.getInstance().getMemberDao();
String idd = request.getParameter("id");
Member member = repository.getMember(idd);
pageContext.setAttribute("member", member); 
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>회원 상세</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
  rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
<style>
@media ( min-width : 1200px) {
	.container {
		max-width: 970px;
	}
}
</style>
</head>

<body>
  <div class="container">
    <div class="py-5 text-center">
      <h2>회원 상세</h2>
    </div>
    <div>
      <label for="id">아이디</label> <input type="text" id="id"
        name="id" class="form-control" value='${member.id }' readonly>
        
    </div>
    <div>
      <label for="name">이름</label> <input type="text" id="name"
        name="name" class="form-control" value='${member.name }' readonly>
    </div>
    <div>
      <label for="email">이메일</label> <input type="text" id="email"
        name="email" class="form-control" value='${member.email }' readonly>
    </div>
    <div>
      <label for="age">나이</label> <input type="text" id="age"
        name="age" class="form-control" value='${member.age }' readonly>
    </div>
    <div>
      <label for="regdate">가입일자</label> <input type="text" id="regdate"
        name="regdate" class="form-control" value='${member.regdate }' readonly>
    </div>
    <hr class="my-4">
    <div class="row">
      <div class="col">
        <button class="w-100 btn btn-primary btn-lg"
          onclick="#" type="button">회원정보 수정</button>
      </div>
      <div class="col">
        <button class="w-100 btn btn-secondary btn-lg"
          onclick="" type="button">회원 목록</button>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JS-->
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="/js/scripts.js"></script>
</body>
</html>
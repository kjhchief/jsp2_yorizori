<%@page import="ezen.yorizori.domain.member.dto.Member"%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
Member member = (Member) session.getAttribute("loginMember");
pageContext.setAttribute("member", member); 
%>
<html lang="ko">

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<title>Yorizori Cookbook</title>
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Bootstrap icons-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="../css/styles.css" rel="stylesheet" />
</head>

<body>
	<!-- Navigation-->
	<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder text-warning">요리조리 요리책 서비스</h1>
				<p class="lead fw-normal text-white-50 mb-0">세상의 모든 레시피가 여기에</p>
			</div>
		</div>
	</header>
  
	<!-- Section-->
	<section class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">

					<div class="col-auto h2">요리책 등록</div>
					<!-- cookbook Form -->
					<form action="/cookbook/regist.do" method="post">
						<div class="row mb-3">
							<label for="book_name" class="col-sm-2 col-form-label">요리책명</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book_name" name="name">
							</div>
						</div>
						<div class="row mb-3">
							<label for="author" class="col-sm-2 col-form-label">등록자</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book_author" value="${member.id}" readonly name="authorid">
                <!-- placeholder="${member.id}" disabled -->
							</div>
						</div>

						<div class="row mb-3">
							<label for="book_desc" class="col-sm-2 col-form-label">설명</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="book_desc" rows="5" name="describe"></textarea>
							</div>
						</div>

						<div class="row mb-3">
							<div class="col-4">
								<a href="#" class="btn btn-warning">요리책 목록</a>
							</div>

							<div class="col-md-4 offset-md-4" style="text-align: right;">
								<button type="submit" class="btn btn-primary">등 록</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
  
	<!-- Footer-->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />

	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>

</html>
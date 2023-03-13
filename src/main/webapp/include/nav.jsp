<%@ page contentType="text/html; charset=utf-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container px-4 px-lg-5">
    <a class="navbar-brand" href="#!">Yorizori Cookbook</a>
    <button class="navbar-toggler" type="button"
      data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false"
      aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
        <li class="nav-item"><a class="nav-link active"
          aria-current="page" href="#">HOME</a></li>
        <li class="nav-item"><a class="nav-link" href="">요리책 목록</a></li>
        <li class="nav-item"><a class="nav-link" href="#">요리책
            등록</a></li>
      </ul>
      <form class="row row-cols-lg-auto g-3 align-items-center"
        method="post" action="#">
        <div class="col-12">
          <input type="text" size="8" maxlength="8" class="form-control"
            id="id" placeholder="아이디">
        </div>
        <div class="col-12">
          <input type="password" size="8" maxlength="8"
            class="form-control" id="passwd" placeholder="비밀번호">
        </div>
        <div class="col-12">
          <div class="form-check">
            <input class="form-check-input" id="saveid" type="checkbox">
            <label class="form-check-label" for="saveid">아이디 저장</label>
          </div>
        </div>
        <div class="col-12">
          <button type="submit" class="btn btn-primary">로그인</button>
        </div>
        <div class="col-12">
          <a href="/member/signup.do" class="btn btn-warning">회원가입</a>
        </div>
      </form>
    </div>
  </div>
</nav>
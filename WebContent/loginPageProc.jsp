<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>

<div class="panel-lite">
  <div class="thumbur">
    <div class="icon-lock"></div>
  </div>
  <h4>Login</h4>
  <div class="form-group">
    <input class="form-control" required="required"/>
    <label class="form-label">Id</label>
  </div>
  <div class="form-group">
    <input class="form-control" type="password" required="required"/>
    <label class="form-label">Password</label>
  </div><a href="joinPage.jsp">Are you not a member yet ?  </a>
  <button class="floating-btn"><i class="icon-arrow"></i></button>
</div>
</body>
</html>

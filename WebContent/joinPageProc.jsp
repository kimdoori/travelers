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
  <h4>Join</h4>
  <div class="form-group">
    <input class="form-control" required="required"/>
    <label class="form-label">Id    </label>
  </div>
  <div class="form-group">
    <input class="form-control" type="password" required="required"/>
    <label class="form-label">Password</label>
  </div>
  <div class="form-group">
    <input class="form-control" type="text" required="required"/>
    <label class="form-label">Name</label>
  </div>
  <div class="form-group">
    <input class="form-control" type="text" required="required"/>
    <label class="form-label">NickName</label>
  </div>
   <div class="form-group">
    <input class="form-control" type="text" required="required" placeholder="           2000-02-28" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/>
    <label class="form-label">Birth</label>
  </div>
   <div class="form-group">
    <input class="form-control" type="tel" required="required" placeholder="              010-0000-1111" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}"/>
    <label class="form-label">Phone</label>
  </div>
  <a href="accountPage.jsp">Are you already a member ?  </a>
  <button class="floating-btn"><i class="icon-arrow"></i></button>
</div>
</body>
</html>

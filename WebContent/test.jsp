<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.PopCornDAO" %>
<%@ page import="model.Corn" %>
<%@ page import="model.Pop" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		PopCornDAO dao = PopCornDAO.getInstance();

		//insert 하기

		Corn corn = new Corn();

		corn.setId("doori");
		corn.setPw("doori0228");
		corn.setName("김두리");
		corn.setNickname("둘둘리");
		corn.setBirth("2000-02-28");
		corn.setPhone("010-8635-2026");
		corn.setLike_num(0);
		
		
		dao.insertCorn(corn);
 %>
</body>
</html>

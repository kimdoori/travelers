<%@page import="database.PopCornDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String corn_id = request.getParameter("corn_id");

		PopCornDAO dao = PopCornDAO.getInstance();

		dao.deleteAllPop(corn_id);

		response.sendRedirect("accountPage.jsp");
	%>

</body>
</html>

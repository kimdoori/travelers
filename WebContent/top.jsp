<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/top.css">

</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String user_id = (String)session.getAttribute("user_id");
	%>

	<div class="navbar">
		<div class="left">
			<a href="index.jsp">MAIN</a>
			<a href="listPop.jsp">POP</a> 
			<a href="listCorn.jsp">CORN</a>
		</div>
		<div class="right">
		<% 
		if(user_id != null)
			out.println("<a href='logout.jsp'>LOGOUT</a>");
		%>
			 <a href="accountPage.jsp">ACCOUNT</a>
		</div>
	</div>


</body>
</html>

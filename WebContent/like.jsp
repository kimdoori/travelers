<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.PopCornDAO"%>
<%@ page import="model.Corn"%>
<%@ page import="model.Pop"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");

		String user_id = request.getParameter("corn_id");

		if (user_id == null || user_id.equals("")) {
	%>
	<jsp:forward page="framePage.jsp">
		<jsp:param name="CONTENTPAGE" value="loginPageProc.jsp" />
	</jsp:forward>
	<%
		}else{
			PopCornDAO dao = PopCornDAO.getInstance();

			int pop_id = Integer.parseInt(request.getParameter("pop_id"));
			boolean like = Boolean.parseBoolean(request.getParameter("like"));
			
			Pop pop = dao.selectOnePop(pop_id);
			Corn corn = dao.selectOneCorn(user_id);
			
			int status_code = -1;
			if(like)
				status_code = dao.deleteLike(pop, corn);
			else
				status_code = dao.insertLike(pop, corn);
			
			String[] status = { "네트워크를 확인해주세요." };

			if (status_code == 0) {//성공	
				
				out.println("<script> location.href='commentPage.jsp?id="+pop_id+"'</script>");

			} else {
				out.println("<script>alert('" + status[status_code - 1] + "');</script>");
				//response.sendRedirect("loginPage.jsp");
				out.println("<script> window.history.back();</script>");

			}

		}
		




		
	%>

</body>
</html>

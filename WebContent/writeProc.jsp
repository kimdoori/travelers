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

	<% 	request.setCharacterEncoding("utf-8");
	
		String title = request.getParameter("title");
		String location = request.getParameter("location");
		String content = request.getParameter("content");
		String photo1 = request.getParameter("photo1");
		photo1 = photo1 == null ? "" : "image/"+photo1;
		String photo2 = request.getParameter("photo2");
		photo2 = photo2 == null ? "" : "image/"+photo2;
		String photo3 = request.getParameter("photo3");
		photo3 = photo3 == null ? "" : "image/"+photo3;

		
		Pop pop = new Pop(title,location,content,0,photo1,photo2,photo3);
		
		System.out.println(pop);
		
		PopCornDAO dao = PopCornDAO.getInstance();
		
		int status_code = dao.insertPop(pop,(String)session.getAttribute("user_id"));
		
		String[] status = {"네트워크를 확인해주세요."};
		
		if(status_code==0){//성공	
			out.println("pop을 등록하였습니다.");
	        //response.sendRedirect("accountPage.jsp");
	        out.println("<script>alert('pop을 등록하였습니다.');</script>");
			out.println("<script> location.href='accountPage.jsp'</script>");

		}else{
			out.println("<script>alert('"+status[status_code-1]+"');</script>");
	        //response.sendRedirect("loginPage.jsp");
			out.println("<script> window.history.back();</script>");

		}

	%>

</body>
</html>

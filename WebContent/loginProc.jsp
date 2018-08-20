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
	
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pw");
		
		Corn corn = new Corn(user_id,user_pw);
		System.out.println(corn);
		
		PopCornDAO dao = PopCornDAO.getInstance();
		
		int status_code = dao.checkCorn(corn);
		
		String[] status = {"비밀번호가 틀렸습니다.","존재하지 않는 회원입니다.","네트워크를 확인해주세요."};
		
		if(status_code==0){//성공	
			out.println("로그인 성공");
			corn = dao.selectOneCorn(user_id);

		
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_name", corn.getName());
			
	        //response.sendRedirect("accountPage.jsp");
			out.println("<script> location.href='accountPage.jsp'</script>");

		}else{
			out.println("<script>alert('"+status[status_code-1]+"');</script>");
	        //response.sendRedirect("loginPage.jsp");
			out.println("<script> window.history.back();</script>");

		}

	%>

</body>
</html>

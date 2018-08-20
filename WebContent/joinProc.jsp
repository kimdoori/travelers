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
		String user_name = request.getParameter("name");
		String user_nickname = request.getParameter("nickname");
		String user_birth = request.getParameter("birth");
		String user_phone = request.getParameter("phone");
		
		
		Corn corn = new Corn(user_id,user_pw,user_name,user_nickname,user_birth,user_phone,0);
		System.out.println(corn.toString());
		
		//TODO 회원가입처리
		PopCornDAO dao = PopCornDAO.getInstance();
		
		int status_code = dao.insertCorn(corn);
		
		String[] status = {"이미 존재하는 아이디입니다.","네트워크를 확인해주세요."};
		
		if(status_code==0){//성공	
			out.println("회원가입 성공");
	        //response.sendRedirect("accountPage.jsp");
	        out.println("<script>alert('회원가입에 성공했습니다.');</script>");
			out.println("<script> location.href='accountPage.jsp'</script>");

		}else{
			out.println("<script>alert('"+status[status_code-1]+"');</script>");
	        //response.sendRedirect("loginPage.jsp");
			out.println("<script> window.history.back();</script>");

		}

	%>

</body>
</html>

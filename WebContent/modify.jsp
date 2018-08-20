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
	
		String user_id = (String) session.getAttribute("user_id");
		String user_pw = request.getParameter("pw");
		String user_name = request.getParameter("name");
		String user_nickname = request.getParameter("nickname");
		String user_birth = request.getParameter("birth");
		String user_phone = request.getParameter("phone");
		
		
		Corn corn = new Corn(user_id,user_pw,user_name,user_nickname,user_birth,user_phone,0);
		
		System.out.println("정보수정 "+corn);
		PopCornDAO dao = PopCornDAO.getInstance();
		
		int status_code = dao.updateCorn(corn);
		
		String[] status = {"네트워크를 확인해주세요."};
		
		if(status_code==0){//성공	
			out.println("업데이트 성공 ");
	        //response.sendRedirect("accountPage.jsp");
	        out.println("<script>alert('정보 수정에 성공했습니다.');</script>");
			out.println("<script> location.href='accountPage.jsp'</script>");

		}else{
			out.println("<script>alert('"+status[status_code-1]+"');</script>");
	        //response.sendRedirect("loginPage.jsp");
			out.println("<script> window.history.back();</script>");

		}

	%>

</body>
</html>

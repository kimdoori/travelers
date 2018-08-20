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
<title>pop 리스트</title>
</head>
<body>
	<%
		PopCornDAO dao = PopCornDAO.getInstance();
		List<Pop> list = dao.selectAllPop();
		if(list.isEmpty()){
			out.println("게시물이 없습니다.");
		}else{
			for(Pop pop : list){
				out.println("<div>"+pop.getTitle()+"</div>");
			}
		}

 %>
</body>
</html>

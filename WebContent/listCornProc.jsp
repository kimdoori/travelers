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
<title>corn 리스트</title>
</head>
<body>
	<%
		PopCornDAO dao = PopCornDAO.getInstance();
		List<Corn> list = dao.selectAllCorn();
		if(list.isEmpty()){
			out.println("회원이 없습니다.");
		}else{
			for(Corn corn : list){
				out.println("<div>"+corn.getName()+"</div>");
			}
		}

 %>
</body>
</html>

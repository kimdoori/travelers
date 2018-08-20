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
<link rel="stylesheet" href="css/corn_list.css">
</head>
<body>
	<%
		PopCornDAO dao = PopCornDAO.getInstance();
		List<Corn> list = dao.selectAllCorn();
		if (list.isEmpty()) {
			out.println("회원이 없습니다.");
		} else {
			for (Corn corn : list) {
	%>

	<div class="container">
		<div class="service-details">
			<img src="image/none.png" alt="profile">
			<div class="service-hover-text">
				<h3><%=corn.getName()%></h3>
				<h4>Developer</h4>
				<p>
				<table class="info-table">
					<tr>
						<td>LIKE</td>
						<td>POP</td>
					</tr>
					<tr>
						<td><%=corn.getLike_num()%></td>
						<td><%=corn.getPop_num()%></td>
					</tr>
				</table>
				</p>
			</div>
			<div class="service-white service-text">
				<p><%=corn.getName()%></p>
				<a href="#">@<%=corn.getNickname()%></a>
			</div>
		</div>
	</div>

	<%
		}
		}
	%>


</body>
</html>

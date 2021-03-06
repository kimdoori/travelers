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
<link rel="stylesheet" href="css/list.css">
<link rel="stylesheet" href="css/comment.css">


</head>
<body>
	<%
		String corn_id = request.getParameter("corn_id");
		String type = request.getParameter("type");
	%>

	<div class="content-profile-page">
		<div class="profile-user-page card">

			<%
				PopCornDAO dao = PopCornDAO.getInstance();
				List<Corn> list = dao.selectAllCorn(type, corn_id);
				if (list.isEmpty()) {
					out.println("<div class='pop' style='text-align:center;'>해당하는 CORN회원이  없습니다.</div>");
				} else {
					for (Corn corn : list) {
			%>
			<div class="user-profile-data">

				<div class="container">
					<div class="service-details">
						<img src="<%=corn.getProfile() %>" alt="profile">
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
							<a href="cornPage.jsp?id=<%=corn.getId()%>">@<%=corn.getNickname()%></a>
						</div>
					</div>
				</div>
			</div>



			<%
				}
				}
			%>
		</div>

	</div>

</body>
</html>

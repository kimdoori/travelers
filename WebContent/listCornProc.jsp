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
		String user_id = (String) session.getAttribute("user_id");
		PopCornDAO dao = PopCornDAO.getInstance();
		List<Corn> list = dao.selectAllCorn();
		if (list.isEmpty()) {
			out.println("<div class='pop'>등록된 CORN 회원이 없습니다.</div>");

		} else {
	%>

	<div class="content-profile-page">
		<div class="profile-user-page card">

			<%
			boolean isVaild = false;
				for (Corn corn : list) {
						if (user_id != null && user_id.equals(corn.getId())) {
							//out.println("다른 CORN 회원이 없습니다.");
							continue;
						}
						isVaild=true;
			%>
			<div class="user-profile-data">

				<div class="container">
					<div class="service-details">
						<img src="<%=corn.getProfile() %>" alt="profile">
						<div class="service-hover-text">
							<h3><%=corn.getName()%></h3>
							<h4><%=corn.getBirth()%></h4>
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
				
				if(!isVaild)
					out.println("<div class='pop'>등록된 다른 CORN 회원이 없습니다.</div>");

				}
		
		
			%>
		</div>

	</div>

</body>
</html>

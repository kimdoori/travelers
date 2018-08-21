<%@page import="model.Pop"%>
<%@page import="java.util.List"%>
<%@page import="database.PopCornDAO"%>
<%@page import="model.Corn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pop</title>
<link rel="stylesheet" href="css/account.css">
<script src="js/account.js"></script>

</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");

		String user_id = (String) session.getAttribute("user_id");

		PopCornDAO dao = PopCornDAO.getInstance();

		boolean isLogin = false;
		if (user_id != null)
			isLogin = true;
	%>


	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">

				<section>
					<%
						List<Pop> list = dao.selectAllPop();
						if (list.isEmpty()) {
							out.println("게시된 POP이 없습니다.");
						} else {
							for (Pop pop : list) {
					%>

					<div class="pop">

						<%
							if (isLogin && user_id.equals(pop.getCorn_id())) {
						%>
						<p class="extra">
							<a href="modifyPopPage.jsp?id=<%=pop.getId()%>">수정하기</a><a
								href="deletePop.jsp?id=<%=pop.getId()%>">&times;</a>
						</p>
						<%
							}
						%>
						<h6><a href="cornPage.jsp?id=<%=pop.getCorn_id()%>"><%=pop.getCorn_name()%></a>님의 POP입니다. ---
							<%=pop.getLocation()%>
							여행
						</h6>
						<h3>
							<%=pop.getTitle()%>
						</h3>
						<p>
							<%=pop.getReg_Date()%></p>
						<h5>
							<%=pop.getContent()%></h5>

						<p>
							♡ &nbsp;<%=pop.getLike_num()%></p>
						<a href="commentPage.jsp?id=<%=pop.getId()%>" id="write">더
							보기..</a>


					</div>
					<%
						}
						}
					%>

				</section>
			</div>

		</div>
	</div>


</body>
</html>

<%@page import="model.Pop"%>
<%@page import="database.PopCornDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/account.css">
<link rel="stylesheet" href="css/card.css">


</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	
		String search = request.getParameter("search");
		System.out.println(search);

		String user_id = (String) session.getAttribute("user_id");

		boolean isLogin = false;
		if (user_id != null)
			isLogin = true;

		PopCornDAO dao = PopCornDAO.getInstance();
	%>


	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">
				<h3>먹거리 POP!</h3>
				<div class="container">


					<%
						List<Pop> foodList = dao.searchPop(search,"먹거리");
						if (foodList.isEmpty()) {
							out.println("<div class='pop'>먹거리 POP이 없습니다.</div>");
						} else {
							for (Pop pop : foodList) {
					%>

					<div class="column">
						<a href="commentPage.jsp?id=<%=pop.getId()%>">

							<div class="post-module">
								<!-- Thumbnail-->
								<div class="thumbnail">
									<div class="date">
										<div class="day"><%=pop.getLike_num()%></div>
										<div class="month">♥</div>
									</div>
									<%
										String[] photo = pop.getPhoto();
												String photo_location = "image/none.png";
												for (int j = 0; j < photo.length; j++) {
													if (photo[j] != null) {
												
														photo_location = photo[j];
														break;
													}
												}
									%>
									<img src="<%=photo_location%>" />
								</div>
								<!-- Post Content-->
								<div class="post-content">
									<div class="category"><%=pop.getLocation()%></div>
									<h1 class="title"><%=pop.getTitle()%></h1>
									<h2 class="sub_title"><%=pop.getCorn_name()%></h2>
									<p><%=pop.getTag() %>여행</p>

									<div class="post-meta">
										<span class="timestamp"><i class="fa fa-clock-">o</i><%=pop.getReg_Date()%></span><br>
										<span class="comments"><i class="fa fa-comments"></i> <%=pop.getComment_num()%>
											comments</span>
									</div>
								</div>
							</div>
						</a>
					</div>


					<%
						}
						}
					%>
				</div>
			</div>
		</div>
	</div>

<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">
				<h3>볼거리 POP!</h3>
				<div class="container">


					<%
						List<Pop> seeList = dao.searchPop(search,"볼거리");
						if (seeList.isEmpty()) {
							out.println("<div class='pop'>볼거리 POP이 없습니다.</div>");
						} else {
							for (Pop pop : seeList) {
					%>

					<div class="column">
						<a href="commentPage.jsp?id=<%=pop.getId()%>">

							<div class="post-module">
								<!-- Thumbnail-->
								<div class="thumbnail">
									<div class="date">
										<div class="day"><%=pop.getLike_num()%></div>
										<div class="month">♥</div>
									</div>
									<%
										String[] photo = pop.getPhoto();
												String photo_location = "image/none.png";
												for (int j = 0; j < photo.length; j++) {
													if (photo[j] != null) {
														photo_location = photo[j];
														break;
													}
												}
									%>
									<img src="<%=photo_location%>" />
								</div>
								<!-- Post Content-->
								<div class="post-content">
									<div class="category"><%=pop.getLocation()%></div>
									<h1 class="title"><%=pop.getTitle()%></h1>
									<h2 class="sub_title"><%=pop.getCorn_name()%></h2>
									<p><%=pop.getTag() %>여행</p>

									<div class="post-meta">
										<span class="timestamp"><i class="fa fa-clock-">o</i><%=pop.getReg_Date()%></span><br>
										<span class="comments"><i class="fa fa-comments"></i> <%=pop.getComment_num()%>
											comments</span>
									</div>
								</div>
							</div>
						</a>
					</div>


					<%
						}
						}
					%>
				</div>
			</div>
		</div>
	</div>

	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">


				<%
					List<Pop> list = dao.searchPop(search);
					if (list.isEmpty()) {
						out.println("<div class='pop'>해당하는 POP이 없습니다.</div>");
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
					String[] photo = pop.getPhoto();
					for(int i=0;i<photo.length;i++){
						if(photo[i]!=null){
							out.println("<img src='"+photo[i]+"' class='pre-photo'>");
						}
					}
					%>
					<h6>
						<a href="cornPage.jsp?id=<%=pop.getCorn_id()%>"><%=pop.getCorn_name()%></a>님의
						POP입니다. --
						<%=pop.getLocation()%>
						여행 &nbsp;&nbsp;&nbsp; #<%=pop.getTag() %>
					</h6>
					<h3>
						<%=pop.getTitle()%>
					</h3>
					<p>
						<%=pop.getReg_Date()%></p>
					<h5>
						"<%=pop.getReason()%>" 이래서 추천합니다.</h5>

					<p>
						♡ &nbsp;<%=pop.getLike_num()%></p>
					<a href="commentPage.jsp?id=<%=pop.getId()%>" id="write">더 보기..</a>


				</div>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>

</body>
</html>

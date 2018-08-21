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
<title>계정 페이지</title>
<link rel="stylesheet" href="css/account.css">
<link rel="stylesheet" href="css/comment.css">
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");

		String corn_id = request.getParameter("id");

		String user_id = (String) session.getAttribute("user_id");
	
		boolean isLogin = false;
		boolean like = false;
		if (user_id != null)
			isLogin = true;

		PopCornDAO dao = PopCornDAO.getInstance();

		Corn corn = dao.selectOneCorn(corn_id);

		if (corn == null) {//실패 
			out.println("<script>alert('네트워크를 확인해주세요.');</script>");
			out.println("<script> window.history.back();</script>");

		} else {//성공
			
			like = dao.isLike(corn.getId(),user_id);

	%>
	
	
	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="img-user-profile">
				<div class="card hovercard">
					<div class="card-background">
						<img class="card-bkimg" alt="" src="image/none.png">
					</div>
					<div class="useravatar">
						<img alt="" src="image/none.png">
					</div>
				</div>
			</div>

			<a href="likeCorn.jsp?like=<%=like %>&corn_id=<%=corn.getId()%>&user_id=<%=user_id%>"><button id="floating">LIKE
			
					<%
						if(isLogin && like)
							out.println("♥");
						else
							out.println("♡");

					
					%>

			</button></a>
			
			<div class="user-profile-data">
				<h1><%=corn.getName()%></h1>
				<p><%=corn.getNickname()%></p>
			</div>
			<div class="description-profile">
				<%=corn.getBirth()%>
				|
				<%=corn.getPhone()%>
			</div>
			<ul class="data-user">
				<li><a href="likePage.jsp?type=like_corn_id&corn_id=<%=corn.getId()%>"><strong><%=corn.getLike_num()%></strong><span>LIKE ME</span></a></li>
				<li><a href="likePage.jsp?type=corn_id&corn_id=<%=corn.getId()%>"><strong><%=corn.getMylike_num()%></strong><span>MY LIKE</span></a></li>
				<li><a><strong><%=corn.getPop_num()%></strong><span>POP</span></a></li>
			</ul>
		</div>
	</div>

	
	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">
				
				<section>
					<%
						List<Pop> list = dao.selectAllPop(corn_id);
							if (list.isEmpty()) {
								out.println("<div class='pop' style='text-align:center;'>게시한 POP이 없습니다.</div>");
							} else {
								for (Pop pop : list) {
					%>

					<div class="pop">
						<h6><%=pop.getCorn_name()%>님의 POP입니다. --
							<%=pop.getLocation()%>
							여행(<%=pop.getTag() %>)
						</h6>
						<h3>
							<%=pop.getTitle()%>
						</h3>
						<p>
							<%=pop.getReg_Date()%></p>
						<h5>
							<%=pop.getContent()%></h5>

						<%
							String[] photo = pop.getPhoto();
							for (int i = 0; i < photo.length; i++) {
								if (photo[i] != null) {
									out.println("<img src='"+photo[i]+"'class='pop-img'><br><br>");
								}
							}
						%>
						

					<p>♡ &nbsp;<%=pop.getLike_num() %></p>
					<a href="commentPage.jsp?id=<%=pop.getId() %>" id="write">댓글 작성하기</a>
					

					</div>
					<%
						}
							}
					%>

				</section>
			</div>

		</div>
	</div>


	<%
		}
	%>


</body>
</html>

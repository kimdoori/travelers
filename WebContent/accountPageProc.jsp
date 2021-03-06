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
<script src="js/account.js"></script>

</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");

		String user_id = (String) session.getAttribute("user_id");

		PopCornDAO dao = PopCornDAO.getInstance();

		Corn corn = dao.selectOneCorn(user_id);

		if (corn == null) {//실패 
			out.println("<script>alert('네트워크를 확인해주세요.');</script>");
			out.println("<script> window.history.back();</script>");

		} else {//성공
			
			boolean isLogin = false;
			boolean like = false;
			if (user_id != null)
				isLogin = true;
		
	%>
	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="img-user-profile">
				<div class="card hovercard">
					<div class="card-background">
						<img class="card-bkimg" alt="" src="<%=corn.getProfile()%>">
					</div>
					<div class="useravatar">
						<img alt="" src="<%=corn.getProfile()%>">
					</div>
				</div>
			</div>

			<a href="modifyPage.jsp"><button id="floating">MODIFY</button></a>

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
				<h3>새로운 POP을 작성하시겠어요?</h3>
				<a href="writePage.jsp" id="write">글 작성하기</a>
			</div>

		</div>
	</div>
	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">
				<section style="text-align: right;">
					<a href="deleteAll.jsp?corn_id=<%=user_id%>" id="write">전체
						삭제하기</a>
				</section>
				<section>
					<%
						List<Pop> list = dao.selectAllPop(user_id);
							if (list.isEmpty()) {
								
								out.println("<div class='pop'>게시한 POP이 없습니다.</div>");
							} else {
								for (Pop pop : list) {
									like = dao.isLike(pop.getId(), user_id);
					%>

					<div class="pop">
						<p class="extra">
							<a href="modifyPopPage.jsp?id=<%=pop.getId()%>">수정하기</a><a
								href="deletePop.jsp?id=<%=pop.getId()%>">&times;</a>
						</p>
						<h6><%=pop.getCorn_name()%>님의 POP입니다. --
							<%=pop.getLocation()%>
							여행 &nbsp;&nbsp;&nbsp; #<%=pop.getTag() %>
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
												out.println("<img src='" + photo[i] + "'class='pop-img'><br><br>");
											}
										}
						%>


						<p><a
							href="like.jsp?like=<%=like%>&pop_id=<%=pop.getId()%>&corn_id=<%=user_id%>&page=accountPage">
							<%
								if (isLogin && like)
										out.println("♥");
									else
										out.println("♡");
							%>
						</a> &nbsp;<%=pop.getLike_num()%></p>
						<a href="commentPage.jsp?id=<%=pop.getId()%>" id="write">댓글
							작성하기</a>


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

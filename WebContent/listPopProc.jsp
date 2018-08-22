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

</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");


		PopCornDAO dao = PopCornDAO.getInstance();
		String user_id = (String) session.getAttribute("user_id");

		boolean isLogin = false;
		if (user_id != null)
			isLogin = true;
		
		boolean like = false;
		
	%>


	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">

				<section>
					<%
						List<Pop> list = dao.selectAllPop();
						if (list.isEmpty()) {
							out.println("<div class='pop'>게시된 POP이 없습니다.</div>");
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
							like = dao.isLike(pop.getId(), user_id);

						%>
						
						
						<h6><a href="cornPage.jsp?id=<%=pop.getCorn_id()%>"><%=pop.getCorn_name()%></a>님의 POP입니다. --
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

						<p><a
							href="like.jsp?like=<%=like%>&pop_id=<%=pop.getId()%>&corn_id=<%=user_id%>&page=listPop">
							<%
								if (isLogin && like)
										out.println("♥");
									else
										out.println("♡");
							%>
						</a> &nbsp;<%=pop.getLike_num()%></p>
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

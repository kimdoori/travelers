<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="model.Pop"%>
<%@page import="database.PopCornDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/account.css">
<link rel="stylesheet" href="css/comment.css">


</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");

		String user_id = (String) session.getAttribute("user_id");
		boolean isLogin = false;
		boolean like = false;
		if (user_id != null)
			isLogin = true;
		int id = Integer.parseInt(request.getParameter("id"));

		PopCornDAO dao = PopCornDAO.getInstance();

		Pop pop = dao.selectOnePop(id);

		if (pop == null) {//실패 
			out.println("해당 POP은 삭제되었거나 존재하지않습니다.");
			//out.println("<script> window.history.back();</script>");

		} else {//성공
			
			like = dao.isLike(id,user_id);
	%>

	<div class="content-profile-page">
		<div class="profile-user-page card">
			<div class="user-profile-data">

				<section>


				<div class="pop">

					<%
						if(isLogin && user_id.equals(pop.getCorn_id())){
								out.println("<p class='extra'><a href='modifyPopPage.jsp?id=" + pop.getId() + "'>수정하기</a>");
								out.println("<a href='deletePop.jsp?id='" + pop.getId() + "'>&times;</a></p>");
							}
					%>

					<h6><%=pop.getCorn_name()%>님의 POP입니다. ---
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

					<%
						String[] photo = pop.getPhoto();
							for (int i = 0; i < photo.length; i++) {
								if (photo[i] != null) {
									out.println("<img src='" + photo[i] + "'class='pop-img'><br><br>");
								}
							}
					%>


					<p>
						<a href="like.jsp?like=<%=like %>&pop_id=<%=pop.getId()%>&corn_id=<%=user_id%>">
					<%
						if(isLogin && like)
							out.println("♥");
						else
							out.println("♡");

					
					%>
					</a> &nbsp;<%=pop.getLike_num()%></p>
					<form action="writeComment.jsp">
						<input type="hidden" name="pop_id" value=<%=pop.getId()%>>
						<input type="text" placeholder="댓글을 입력하세요." name="comment"
							id="comment-input" required="required">&nbsp;&nbsp;&nbsp;<input
							type="submit" value="작성" id="write"></input>
					</form>
					<%
						List<Comment> list = dao.selectAllComment(pop.getId());
							if (list.isEmpty()) {
								out.println("댓글이 없습니다.");
							} else {
								for (Comment comment : list) {
					%>
					<div class="comment">
					<%
						if(isLogin && user_id.equals(comment.getCorn_id()))
					%>
						<a href="deleteComment.jsp?pop_id=<%=pop.getId() %>&id=<%=comment.getId()%>"><span style="float: right;">&times;</span></a>
						
						<h5><%=comment.getCorn_name()%></h5>
						<p><%=comment.getReg_date()%></p>
						<h6><%=comment.getComment()%></h6>
					</div>
					<%
						}
							}
					%>

				</div>
				<%
					}
				%> </section>
			</div>

		</div>
	</div>



</body>
</html>
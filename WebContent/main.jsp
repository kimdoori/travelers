<%@page import="model.Pop"%>
<%@page import="model.Location"%>
<%@page import="java.util.List"%>
<%@page import="database.PopCornDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/account.css">
<link rel="stylesheet" href="css/card.css">

<title>Insert title here</title>
</head>
<body>
	<%
		PopCornDAO dao = PopCornDAO.getInstance();
	%>
	<section id="search-section" style="text-align: center;">
		<h2 id="logo">
			<a href="seat.jsp">TRAVELERS</a>
			<p>POP-CORN</p>
		</h2>

		<form class="search" action="search.jsp"
			style="margin: auto; max-width: 500px">
			<table style="width: 100%; height: 100%;">
				<tr>
					<td><input type="text" placeholder="검색할 내용을 입력하세요."
						name="search">
						<button type="submit">
							<i class="fa fa-search"></i>
						</button></td>
				</tr>
			</table>
		</form>

	</section>
	<section>

		<div class="content-profile-page">
			<div class="profile-user-page card">
				<div class="user-profile-data">
					<h3>여기로 여행은 어떠신가요?</h3>
					<div class='pop'>

						<h5><strong>인기있는 여행지 순위</strong></h5>
						<%
							List<Location> list = dao.selectAllLoaction();
							if (list.isEmpty()) {
								out.println("<div class='pop'>등록된 여행지가 없습니다.</div>");
							} else {
								int i = 0;

								for (Location location : list) {
						%>
						<p><%=++i%>순위. <strong><%=location.getLocation_name()%></strong>
							(<%=location.getCount()%>개의 POP)
						</p>


						<%
							}
								out.println("</div>");

								//out.println("총 " + i + "개의 여행지");
							}
							
							String user_birth = (String)session.getAttribute("user_birth");
								
							if(user_birth!=null && !user_birth.equals("")){
								String year = user_birth.substring(0,user_birth.indexOf("-"));
	//							System.out.println("year"+year);
								
						%>
						
						<div class='pop'>

						<h5><strong>내 나이대 여행지 순위</strong> <%=Integer.parseInt(year)-4 %> ~ <%=Integer.parseInt(year)+4 %>년생</h5>
						<%
							List<Location> ageList = dao.selectAllLoaction(year);
							if (ageList.isEmpty()) {
								out.println("<div class='pop'>등록된 여행지가 없습니다.</div>");
							} else {
								int i = 0;
								for (Location location : ageList) {
						%>
						<p><%=++i%>순위. <strong><%=location.getLocation_name()%></strong>
							(<%=location.getCount()%>개의 POP)
						</p>


						<%
							}
								//out.println("총 " + i + "개의 여행지");
							}
						%>
						</div>
						
						<%
						}
						%>
					</div>

				</div>
			</div>
	</section>

	<section>
		<div class="content-profile-page">
			<div class="profile-user-page card">
				<div class="user-profile-data">
					<h3>인기있는 POP!</h3>
					<div class="container">

						<%
							List<Pop> popList = dao.selectPopularPop();
							if (popList.isEmpty()) {
								out.println("<div class='pop'>인기있는 POP이 없습니다.</div>");
							} else {
								int i = 0;
								for (Pop pop : popList) {
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
										<p><%=pop.getTag()%>여행
										</p>

										<div class="post-meta">
											<span class="timestamp"><i class="fa fa-clock-">o</i><%=pop.getReg_Date()%></span><br>
											<span class="comments"><i class="fa fa-comments"></i>
												<%=pop.getComment_num()%> comments</span>
										</div>
									</div>
								</div>
							</a>
						</div>

						<%
							}
								//out.println("총 "+i+"개의 검색결과");
							}
						%>

					</div>
				</div>

			</div>
		</div>
	</section>
	<section>
		<div class="content-profile-page">
			<div class="profile-user-page card">
				<div class="user-profile-data">
					<h3>새로운 POP!</h3>
					<jsp:include page="listPopProc.jsp"></jsp:include>
				</div>

			</div>
		</div>

	</section>

</body>
</html>

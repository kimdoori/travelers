<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("user_id") == null) {
%>
		<jsp:forward page="framePage.jsp">
			<jsp:param name="CONTENTPAGE" value="loginPageProc.jsp" />
		</jsp:forward>
<%
	} else {
%>
		<jsp:forward page="framePage.jsp">
			<jsp:param name="CONTENTPAGE" value="accountPageProc.jsp" />
		</jsp:forward>
<%
	}
%>

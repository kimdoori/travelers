<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/main.css">
<title>Insert title here</title>
</head>
<body>

	<section id="search-section" style="text-align: center;">
		<h2 id="logo">
			<a href="seat.jsp">TRAVELERS</a>
		</h2>

		<form class="search" action="/action_page.php"
			style="margin: auto; max-width: 500px">
			<table style="width: 100%; height: 100%;">
				<tr>
					<td><input type="text" placeholder="traveler 이름이나 여행지를 검색하세요."
						name="search2">
						<button type="submit">
							<i class="fa fa-search"></i>
						</button></td>
				</tr>
			</table>
		</form>

	</section>

</body>
</html>

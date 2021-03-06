<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Banking Application</title>
<meta charset="utf-8">
<link rel="icon" href="../Images/bank.png">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/View/style.css">
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<p class="navbar-brand">BANKING APPLICATION</p>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li id="home"><a
				href="${pageContext.request.contextPath}/View/Home.jsp">HOME</a></li>
			<li id="register"><a
				href="${pageContext.request.contextPath}/View/Register.jsp">REGISTER</a></li>
			<li id="logout"><a
				href="${pageContext.request.contextPath}/View/Logout.jsp">LOGOUT</a></li>
		</ul>
	</div>
</nav>
</head>
<body>
	<div class="home-nav-left">
		<div class="home-nav-left-content">
			<img alt="bank-logo" src="../Images/bank.png" width="200px"
				height="200px">
			<h2>Everything at your finger tip!</h2>
		</div>
	</div>
	<div class="home-nav-right">
		<br>
		<form action="../balance" method="POST">

			<h2>
				Your Balance details is<br> just one click away,
				<%
				if (session.getAttribute("userName") != null) {
					out.println(session.getAttribute("userName"));
				}
			%>!
			</h2>
			<button type="submit"
				class="btn btn-dark btn-lg signin-signup-button">
				<i class="fa fa-sign-in" aria-hidden="true"></i> CHECK BALANCE
			</button>
		</form>
	</div>

	<div class="footer-padding">
		<div class="footer">
			<p>Copyright � 2020Bank</p>
		</div>
	</div>
</body>
</html>
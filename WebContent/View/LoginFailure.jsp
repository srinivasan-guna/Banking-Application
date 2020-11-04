<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Banking Application</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/bank.js"></script>
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
			<img alt="bank-logo"
				src="../${pageContext.request.contextPath}/Images/bank.png"
				width="200px" height="200px">
			<h2>Everything at your finger tip!</h2>
		</div>
	</div>
	<div class="home-nav-right">
		<h1>Login failed! Please try again.</h1>
		<div class="col-md-6 col-sm-12">
			<div class="login-register-form">
				<form action="../${pageContext.request.contextPath}/login"
					method="POST">
					<label for="name">Email Id</label> <input type="text" id="emailId"
						class="form-control" placeholder="Enter Your Email Id"
						name="emailId" onkeypress="return restrictSpace(event)"
						onblur="validateEmail()" maxlength="25" required>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" placeholder="Enter Password" name="password"
							maxlength="15" required>
					</div>
					<button type="submit"
						class="btn btn-dark btn-lg signin-signup-button">
						<i class="fa fa-sign-in" aria-hidden="true"></i> LOGIN
					</button>

				</form>
			</div>
		</div>
	</div>


	<div class="footer-padding">
		<div class="footer">
			<p>Copyright © 2020Bank</p>
		</div>
	</div>
</body>
</html>
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
<script src="https://use.fontawesome.com/b025966b5b.js"></script>
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
				src="../${pageContext.request.contextPath}/Images/register.png"
				width="200px" height="200px">
			<h2>Registeration failed. Try again!</h2>
		</div>
	</div>
	<div class="home-nav-right">
		<div class="col-md-6 col-sm-12">

			<div class="register-form">
				<form action="../register" method="POST">
					<div class="form-group">
						<label for="name">First Name</label> <input type="text"
							id="firstName" class="form-control"
							placeholder="Enter Your First Name" name="firstName"
							onkeypress="return restrictNumbers(event)"
							onblur="removeSpace(this.id)" maxlength="15" required>
					</div>
					<div class="form-group">
						<label for="name">Last Name</label> <input type="text"
							id="lastName" class="form-control"
							placeholder="Enter Your Last Name" name="lastName"
							onkeypress="return restrictNumbers(event)"
							onblur="removeSpace(this.id)" maxlength="15" required>
					</div>
					<div class="form-group">
						<label for="name">Email Id</label> <input type="text" id="emailId"
							class="form-control" placeholder="Enter Your Email Id"
							name="emailId" onkeypress="return restrictSpace(event)"
							onblur="validateEmail()" maxlength="25" required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							id="password" class="form-control"
							placeholder="Enter atleast 5 characters" name="password"
							onblur="validatePassword()" maxlength="15" required>
					</div>
					<div class="form-group">
						<label for="name">Date of birth</label> <input type="date"
							class="form-control" placeholder="Enter Your D.O.Bt" name="dob"
							maxlength="10" required>
					</div>
					<button type="submit"
						class="btn btn-dark btn-lg signin-signup-button">
						<i class="fa fa-user-plus" aria-hidden="true"></i> REGISTER
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="footer-padding"></div>
	<div class="footer">
		<p>Copyright © 2020Bank</p>
	</div>
	</div>
</body>
</html>
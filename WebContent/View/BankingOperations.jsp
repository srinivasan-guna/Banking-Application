<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Banking Application</title>
<meta charset="utf-8">
<link rel="icon"
	href="../${pageContext.request.contextPath}/Images/bank.png">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/b025966b5b.js"></script>
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
				src="../${pageContext.request.contextPath}/Images/selection.png"
				width="200px" height="200px">
			<h2>Everything at your finger tip!</h2>
		</div>
	</div>

	<div class="home-nav-right">
		<h2>
			Welcome
			<%
			if (session.getAttribute("userName") != null) {
				out.println(session.getAttribute("userName"));
			}
		%>
			!
		</h2>
		<button type="button" class="btn btn-dark btn-lg operations-button">
			<a href="${pageContext.request.contextPath}/View/Deposit.jsp"
				style="color: white;">DEPOSIT MONEY 
		</button>
		<br>
		<button type="button" class="btn btn-dark btn-lg operations-button">
			<a href="${pageContext.request.contextPath}/View/Withdraw.jsp"
				style="color: white;">WITHDRAW MONEY 
		</button>
		<br>
		<button type="button" class="btn btn-dark btn-lg operations-button">
			<a href="${pageContext.request.contextPath}/View/CheckBalance.jsp"
				style="color: white;">CHECK BALANCE 
		</button>
		<br>
		<button type="button" class="btn btn-dark btn-lg operations-button">
			<a href="${pageContext.request.contextPath}/View/TransferFund.jsp"
				style="color: white;">TRANSFER MONEY 
		</button>
		<br>
		<button type="button" class="btn btn-dark btn-lg operations-button">
			<a
				href="${pageContext.request.contextPath}/View/TransactionHistory.jsp"
				style="color: white;">TRANSACTION HISTORY 
		</button>
	</div>

	<div class="footer-padding">
		<div class="footer">
			<p>Copyright � 2020Bank</p>
		</div>
	</div>
</body>
</html>
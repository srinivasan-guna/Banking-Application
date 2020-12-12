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
	<%@ page import="java.util.Queue"%>
	<%@ page import="java.util.LinkedList"%>
	<%@page import="java.util.Iterator"%>
	<%@page import="controller.TransactionHistoryController"%>
	<%@page import="model.AccountGetterSetter"%>
	<div class="transactionhistorypage-nav-left">
		<div class="home-nav-left-content">
			<img alt="bank-logo"
				src="../${pageContext.request.contextPath}/Images/bank.png"
				width="200px" height="200px">
			<h2>Everything at your finger tip!</h2>
		</div>
	</div>

	<div class="transactionhistorypage-nav-right">
		<h2>
			Transaction History of
			<%
			if (session.getAttribute("userName") != null) {
				out.println(session.getAttribute("userName"));
			}
		%>:
		</h2>
		<div class="Table">
			<div class="Heading">
				<div class="Cell">
					<p>AccountNumber</p>
				</div>
				<div class="Cell">
					<p>Deposit</p>
				</div>
				<div class="Cell">
					<p>Withdrawal</p>
				</div>
				<div class="Cell">
					<p>Date</p>
				</div>
				<div class="Cell">
					<p>AccountBalance</p>
				</div>
			</div>
			<%
				if (session.getAttribute("transactions") != null) {
					Queue<AccountGetterSetter> transactions = (Queue<AccountGetterSetter>) (session
							.getAttribute("transactions"));

					for (AccountGetterSetter transaction : transactions) {
			%>

			<div class="Row">
				<div class="Cell">
					<p><%=transaction.getAccountNumber()%></p>
				</div>
				<div class="Cell">
					<p><%=transaction.getDepositAmount()%></p>
				</div>
				<div class="Cell">
					<p><%=transaction.getWithdrawalAmount()%></p>
				</div>
				<div class="Cell">
					<p><%=transaction.getDate()%></p>
				</div>
				<div class="Cell">
					<p><%=transaction.getBalance()%></p>
				</div>
			</div>

			<%
				}
				}
			%>
		</div>
		<br>
		<button type="button"
			class="btn btn-dark btn-lg transactionhistorypage-operation-button">
			<a href="${pageContext.request.contextPath}/View/Deposit.jsp"
				style="color: white;">DEPOSIT MONEY 
		</button>

		<button type="button"
			class="btn btn-dark btn-lg transactionhistorypage-operation-button">
			<a href="${pageContext.request.contextPath}/View/CheckBalance.jsp"
				style="color: white;">CHECK BALANCE 
		</button>
		<br>
		<button type="button"
			class="btn btn-dark btn-lg transactionhistorypage-operation-button">
			<a href="${pageContext.request.contextPath}/View/Withdraw.jsp"
				style="color: white;">WITHDRAW MONEY 
		</button>
		<button type="button" class="btn btn-dark btn-lg operations-button">
			<a href="${pageContext.request.contextPath}/View/TransferFund.jsp"
				style="color: white;">TRANSFER MONEY 
		</button>
	</div>


	<div class="footer-padding">
		<div class="footer">
			<p>Copyright © 2020Bank</p>
		</div>
	</div>
</body>
</html>
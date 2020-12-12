package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDAO;
import model.AccountGetterSetter;

@WebServlet("/transactionHistory")
public class TransactionHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String accountNumber;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
			accountGetterSetter.setAccountNumber((long) session.getAttribute("accountNumber"));
			Queue<AccountGetterSetter> transactions = AccountDAO.transactionHistory(accountGetterSetter);
			session.setAttribute("transactions", transactions);
			if (transactions != null) {
				//System.out.println("ACCOUNT NUMBER\tDEPOSIT AMOUNT\tWITHDRAWAL AMOUNT\t\tDATE\t\t\tBALANCE");	
				request.getRequestDispatcher("View/TransactionHistoryResult.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("View/OperationFailure.jsp").forward(request, response);
			}
		} catch (DateTimeParseException | IOException | ServletException exception) {
			exception.printStackTrace();
		}
	}
}

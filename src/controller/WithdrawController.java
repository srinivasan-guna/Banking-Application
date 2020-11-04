package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDAO;
import model.AccountGetterSetter;

@WebServlet("/withdraw")
public class WithdrawController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String accountNumber, withdrawalAmount;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
			accountGetterSetter.setAccountNumber((long) session.getAttribute("accountNumber"));
			accountGetterSetter.setWithdrawalAmount(Long.parseLong(request.getParameter("withdrawalAmount")));
			long updatedBalance = AccountDAO.debit(accountGetterSetter);
			PrintWriter out = response.getWriter();
			if(updatedBalance <= 0) {
				request.getRequestDispatcher("View/InsufficientBalance.jsp").forward(request, response);
			}
			else {
				session.setAttribute("updatedBalance", updatedBalance);
				request.getRequestDispatcher("View/WithdrawResult.jsp").forward(request, response);
			}

		} catch (DateTimeParseException | IOException | ServletException exception) {
			exception.printStackTrace();
		}

	}
}

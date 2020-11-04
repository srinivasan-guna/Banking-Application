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

@WebServlet("/balance")
public class CheckBalanceController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
			accountGetterSetter.setAccountNumber( (long) session.getAttribute("accountNumber"));
			int currentBalance = AccountDAO.balance(accountGetterSetter);
			PrintWriter out = response.getWriter();
			//HttpSession session = request.getSession();
			session.setAttribute("currentBalance", currentBalance);
			request.getRequestDispatcher("View/BalanceResult.jsp").forward(request, response);

		} catch (DateTimeParseException | IOException | ServletException exception) {
			exception.printStackTrace();
		}

	}
}

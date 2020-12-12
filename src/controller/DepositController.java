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

@WebServlet("/deposit")
public class DepositController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String accountNumber, depositAmount;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
			accountGetterSetter.setDepositAmount(Long.parseLong(request.getParameter("depositAmount")));
			accountGetterSetter.setAccountNumber((long) session.getAttribute("accountNumber"));
			PrintWriter out = response.getWriter();
			out.println(session.getAttribute("accountNumber"));
			int updatedBalance = AccountDAO.credit(accountGetterSetter);
			if( updatedBalance!=0 ) {
				session.setAttribute("updatedBalance", updatedBalance);
				request.getRequestDispatcher("View/DepositResult.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("View/DepositFailure.jsp").forward(request, response);	
			}
		} catch (DateTimeParseException | IOException | ServletException exception) {
			exception.printStackTrace();
		}

	}
}

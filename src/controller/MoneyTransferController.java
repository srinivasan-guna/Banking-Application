package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDAO;
import model.AccountGetterSetter;

@WebServlet("/MoneyTransferController")
public class MoneyTransferController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
			accountGetterSetter.setReceiverAccountNumber(Long.parseLong(request.getParameter("depositAmount")));
			accountGetterSetter.setDepositAmount(Long.parseLong(request.getParameter("depositAmount")));
			accountGetterSetter.setAccountNumber((long) session.getAttribute("accountNumber"));
			PrintWriter out = response.getWriter();
			int status = AccountDAO.transfer(accountGetterSetter);
			if (status != 0) {
				//session.setAttribute("updatedBalance", updatedBalance);
				//request.getRequestDispatcher("View/DepositResult.jsp").forward(request, response);
			}
		} catch (DateTimeParseException | IOException | ServletException exception) {
			exception.printStackTrace();
		}
	}

}

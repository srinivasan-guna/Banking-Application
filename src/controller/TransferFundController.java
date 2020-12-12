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

@WebServlet("/transferFund")
public class TransferFundController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			AccountGetterSetter accountGetterSetter = new AccountGetterSetter();

			accountGetterSetter.setReceiverAccountNumber(Long.parseLong(request.getParameter("receiverAccountNumber")));
			accountGetterSetter.setDepositAmount(Long.parseLong(request.getParameter("depositAmount")));
			accountGetterSetter.setAccountNumber((long) session.getAttribute("accountNumber"));
			PrintWriter out = response.getWriter();
			int status = AccountDAO.transfer(accountGetterSetter);
			if (status == -1) {
				request.getRequestDispatcher("View/TransferFundFailure.jsp").forward(request, response);
			}  else if (status == 1) {
				// session.setAttribute("senderBalance", senderBalance);
				request.getRequestDispatcher("View/TransferFundResult.jsp").forward(request, response);
			} else if (status == 0) {
				request.getRequestDispatcher("View/InsufficientBalance.jsp").forward(request, response);
			}
			else if (status == -2){
				request.getRequestDispatcher("View/InvalidWithdrawalAmount.jsp").forward(request, response);
			}
		} catch (DateTimeParseException | IOException | ServletException exception) {
			exception.printStackTrace();
		}
	}

}

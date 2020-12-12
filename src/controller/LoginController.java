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

import model.UserDAO;
import model.UserGetterSetter;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String  emailId, password;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) {
		doPost (request, response);
	}	
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) {
		try {
			UserGetterSetter userGetterSetter = new UserGetterSetter();
			userGetterSetter.setEmailId(request.getParameter("emailId"));
			userGetterSetter.setPassword(request.getParameter("password"));
			int status = UserDAO.authenticateSignIn(userGetterSetter);
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();  
	        long accountNumber = UserDAO.getAccountNumber(request.getParameter("emailId"));
	        String userName = UserDAO.getUserName(request.getParameter("emailId"));
	        session.setAttribute("accountNumber", accountNumber);
			if (status == 1) { 
				session.setAttribute("userName", userName);
				request.getRequestDispatcher("View/BankingOperations.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("View/LoginFailure.jsp").forward(request, response);			
			}
		}
		catch (DateTimeParseException | IOException | ServletException exception) {
      		exception.printStackTrace();
      	}
		
	}
}

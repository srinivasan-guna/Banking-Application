package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

//import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

import model.UserDAO;
import model.UserGetterSetter;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String firstName, lastName, email, dateOfBirth; 
	int age = 0;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request, response);
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dateOfBirth = request.getParameter("dob");
		try {
			LocalDate dateofBirth = LocalDate.parse(dateOfBirth);
			LocalDate currentDate = LocalDate.now();
			age = Period.between(dateofBirth, currentDate).getYears();
			UserGetterSetter userGetterSetter = new UserGetterSetter();
			userGetterSetter.setEmailId(request.getParameter("emailId"));
			userGetterSetter.setFirstName(request.getParameter("firstName"));
			userGetterSetter.setLastName(request.getParameter("lastName"));
			userGetterSetter.setPassword(request.getParameter("password"));
			userGetterSetter.setAge(age);
			long accountNumber = UserDAO.registerUser(userGetterSetter);
			String userName = UserDAO.getUserName(request.getParameter("emailId"));
			PrintWriter out = response.getWriter();
			if (accountNumber == (long) 0) { 
				request.getRequestDispatcher("View/RegisterationFailure.jsp").forward(request, response);
			}
			else {
				//request.setAttribute("accountNumber",accountNumber);
				HttpSession session=request.getSession();  
		        session.setAttribute("accountNumber",accountNumber);
		        session.setAttribute("userName", userName);
				request.getRequestDispatcher("View/BankingOperations.jsp").forward(request, response);
			}
		}
		catch (DateTimeParseException | IOException | ServletException  exception) {
			request.getRequestDispatcher("View/RegisterationFailure.jsp").forward(request, response);
      		exception.printStackTrace();
      	}
	}
}

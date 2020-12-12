package model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import java.sql.CallableStatement;
import java.util.Properties;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class UserDAO {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			Class.forName(properties.getProperty("className"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"),
					properties.getProperty("userName"), properties.getProperty("password"));
		} catch (SQLException | ClassNotFoundException | IOException exception) {
			exception.printStackTrace();
		}

		return connection;
	}
	
	public static String getUserName(String emailId) {
		String userName = null;
		Connection connection = getConnection();
		ResultSet resultSet = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getUserNameProcedure"));
			callableStatement.setString(1, emailId);
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			userName = resultSet.getString(1);
		}
		catch (SQLException | IOException exception) {
			exception.printStackTrace();
		}
		return userName;
	}

	public static long getAccountNumber(String emailId) {
		long accountNumber = 0;
		Connection connection = getConnection();
		ResultSet resultSet = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getAccountNumberProcedure"));
			callableStatement.setString(1, emailId);
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			accountNumber = resultSet.getInt(1);
		}
		catch (SQLException | IOException exception) {
			exception.printStackTrace();
		}
		return accountNumber;	
	}

	public static long registerUser(UserGetterSetter userGetterSetter) {
		Connection connection = getConnection();
		int status = 0;
		long accountNumber = 0;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("registerProcedure"));
			callableStatement.setString(1, userGetterSetter.getFirstName());
			callableStatement.setString(2, userGetterSetter.getLastName());
			callableStatement.setString(3, userGetterSetter.getEmailId());
			callableStatement.setString(4, userGetterSetter.getPassword());
			callableStatement.setInt(5, userGetterSetter.getAge());
			try {
				status = callableStatement.executeUpdate();
			} catch (MySQLIntegrityConstraintViolationException exception) {
				System.out.println("EMAIL ID ALREADY EXISTS!\n");
//				System.out.println("-------------------------");
//				//UserDetails.startBanking();
			}
			if (status != -1) {
				callableStatement = connection.prepareCall(properties.getProperty("getAccountNumberProcedure"));
				callableStatement.setString(1, userGetterSetter.getEmailId());
				ResultSet resultSet = callableStatement.executeQuery();
				System.out.println("REGISTERATION SUCCESSFUL");
				System.out.println("----------------------------------------------------");
				resultSet.next();
				accountNumber = Long.parseLong(resultSet.getString(1));
				System.out.println("\nYOUR ACCOUNT NUMBER: " + accountNumber);
				callableStatement = connection.prepareCall(properties.getProperty("addAccountProcedure"));
				callableStatement.setInt(1, (int) accountNumber);
				callableStatement.executeUpdate();
				System.out.println("REMEMBER EMAIL ID, ACCOUNT NUMBER & PASSWORD FOR LOGIN CREDENTIALS");
				System.out.println("------------------------------------------------------------------");
			} else {
				System.out.println("ACCOUNT WAS NOT EXECUTED");
			}
		} catch (SQLException | IOException exception) {
			exception.printStackTrace();
		}
		return accountNumber;

	}

	public static int authenticateSignIn(UserGetterSetter userGetterSetter) {
		Connection connection = getConnection();
		int status = 0;
		int accountNumber = 0;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection
					.prepareCall(properties.getProperty("authenticateProcedure"));
			ResultSet resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				if ((resultSet.getString(1).equals(userGetterSetter.getEmailId()))
						&& (resultSet.getString(2).equals(userGetterSetter.getPassword()))) {
					callableStatement = connection.prepareCall(properties.getProperty("getAccountNumberProcedure"));
					callableStatement.setString(1, userGetterSetter.getEmailId());
					ResultSet resultSet1 = callableStatement.executeQuery();
					resultSet1.next();
					accountNumber = Integer.parseInt(resultSet1.getString(1));
					System.out.println("\nYOUR ACCOUNT NUMBER: " + resultSet1.getString(1));
					status = 1;
				}
				if ((resultSet.getString(1).equals(userGetterSetter.getEmailId()))
						&& (!resultSet.getString(2).equals(userGetterSetter.getPassword()))) {
					status = -1;
					System.out.print("INVALID CREDENTIALS\n-------------------\n");
					// UserDetails.startBanking();
				}

			}
//			if (status != -1) {
//				callableStatement = connection.prepareCall(properties.getProperty("getAccountNumberProcedure"));
//				callableStatement.setString(1, userGetterSetter.getEmailId());
//
//				resultSet = callableStatement.executeQuery();
//				// System.out.println("LOGIN SUCCESSFUL");
//				System.out.println("----------------------------------------------------");
//			}

		} catch (SQLException | IOException exception) {
			exception.printStackTrace();
		}
		return status;
	}

}

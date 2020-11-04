package model;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.servlet.http.HttpSession;

public class AccountDAO extends UserDAO {

	public static int credit(AccountGetterSetter accountGetterSetter) {
		ResultSet resultSet = null;
		int status = 0;
		int updatedBalance = 0;
		try {
			Connection connection = getConnection();
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			callableStatement = connection.prepareCall(properties.getProperty("creditProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			callableStatement.setInt(2, (int) accountGetterSetter.getDepositAmount());
			callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			callableStatement.setInt(4, (int) (resultSet.getInt(1) + accountGetterSetter.getDepositAmount()));
			callableStatement.executeUpdate();

			callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));
			callableStatement.setInt(1, (int) (resultSet.getInt(1) + accountGetterSetter.getDepositAmount()));
			callableStatement.setInt(2, (int) accountGetterSetter.getAccountNumber());
			status = callableStatement.executeUpdate();
			updatedBalance = (int) (resultSet.getInt(1) + accountGetterSetter.getDepositAmount());
			if (status != -1)
				System.out.println("SUCCESSFUL TRANSACTION\n");
			else
				System.out.println("ERROR OCCURED\n");
		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		}
		return updatedBalance;
		// BankingOperations.operation();
	}

	public static long debit(AccountGetterSetter accountGetterSetter) {
		long currentBalance = 0;
		long updatedBalance = 0;
		ResultSet resultSet = null;
		try {
			Connection connection = getConnection();
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			currentBalance = Long.parseLong(resultSet.getString(1));
			if (currentBalance >= 500) {
				callableStatement = connection.prepareCall(properties.getProperty("debitProcedure"));
				callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
				callableStatement.setInt(2, (int) accountGetterSetter.getWithdrawalAmount());
				callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				callableStatement.setInt(4, (int) (resultSet.getInt(1) - accountGetterSetter.getWithdrawalAmount()));
				callableStatement.executeUpdate();
				callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));
				// currentBalance holds the data from Resultset of getBalanceProcedure
				// currentBalance = Long.parseLong(resultSet.getString(1));
				// if(currentBalance >= 500) {
				callableStatement.setInt(1, (int) (currentBalance - accountGetterSetter.getWithdrawalAmount()));
				callableStatement.setInt(2, (int) accountGetterSetter.getAccountNumber());
				int status = callableStatement.executeUpdate();
			} else if (currentBalance < 500) {

			}
			// System.out.println("SUCCESSFUL TRANSACTION\n");
			// }
			// else {
			System.out.println("MINIMUM BALANCE OF 500 MUST BE MAINTAINED\n\nYOUR ACCOUNT BALANCE: " + currentBalance);
//				System.out.println("YOUR ACCOUNT BALANCE: "+currentBalance);
			// }
			updatedBalance = (long) (currentBalance - accountGetterSetter.getWithdrawalAmount());
		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		}
		return updatedBalance;
	}

	public static int balance(AccountGetterSetter accountGetterSetter) {
		int currentBalance = 0;
		try {
			Connection connection = getConnection();
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			ResultSet resultSet = callableStatement.executeQuery();
			resultSet.next();
			System.out.println("\nYOUR ACCOUNT BALANCE: " + resultSet.getString(1));
			currentBalance = Integer.parseInt(resultSet.getString(1));
		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		}
		return currentBalance;
	}

	public static int transfer(AccountGetterSetter accountGetterSetter) {
		ResultSet resultSet = null;
		long currentBalance = 0;
		long updatedBalance = 0;
		int status = 0;
		try {
			Connection connection = getConnection();
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			currentBalance = Long.parseLong(resultSet.getString(1));
			
			if (currentBalance >= 500) {
				callableStatement = connection.prepareCall(properties.getProperty("transferProcedure"));
				callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
				callableStatement.setInt(2, (int) accountGetterSetter.getWithdrawalAmount());
				callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				callableStatement.setInt(4, (int) (resultSet.getInt(1) - accountGetterSetter.getWithdrawalAmount()));
				callableStatement.executeUpdate();
				callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));
				// currentBalance holds the data from Resultset of getBalanceProcedure
				// currentBalance = Long.parseLong(resultSet.getString(1));
				// if(currentBalance >= 500) {
				callableStatement.setInt(1, (int) (currentBalance - accountGetterSetter.getWithdrawalAmount()));
				callableStatement.setInt(2, (int) accountGetterSetter.getAccountNumber());
				status = callableStatement.executeUpdate();
			} else if (currentBalance < 500) {

			}
			// System.out.println("SUCCESSFUL TRANSACTION\n");
			// }
			// else {
			System.out.println("MINIMUM BALANCE OF 500 MUST BE MAINTAINED\n\nYOUR ACCOUNT BALANCE: " + currentBalance);
//				System.out.println("YOUR ACCOUNT BALANCE: "+currentBalance);
			// }
			updatedBalance = (long) (currentBalance - accountGetterSetter.getWithdrawalAmount());
		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		}
		return status;
	}

}

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
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import javax.servlet.http.HttpSession;

//import bank.AccountGetterSetter;

public class AccountDAO extends UserDAO {

	public static int credit(AccountGetterSetter accountGetterSetter) {
		ResultSet resultSet = null;
		int status = 0;
		int updatedBalance = 0;
		Connection connection = getConnection();
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			int currentBalance = (int) resultSet.getInt(1);
			int depositAmount = (int) accountGetterSetter.getDepositAmount();

			if (depositAmount != 0 && (depositAmount % 100 == 0 || depositAmount % 50 == 0)) {
				callableStatement = connection.prepareCall(properties.getProperty("creditProcedure"));
				callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
				callableStatement.setInt(2, depositAmount);
				// callableStatement.setInt(2, (int) accountGetterSetter.getDepositAmount());
				callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				callableStatement.setInt(4, currentBalance + depositAmount);
				callableStatement.executeUpdate();

				callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));
				callableStatement.setInt(1, currentBalance + depositAmount);
				callableStatement.setInt(2, (int) accountGetterSetter.getAccountNumber());
				status = callableStatement.executeUpdate();
				updatedBalance = (int) (resultSet.getInt(1) + accountGetterSetter.getDepositAmount());
			}
			if (status != -1)
				System.out.println("SUCCESSFUL TRANSACTION\n");
			else
				System.out.println("ERROR OCCURED\n");
		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return updatedBalance;
		// BankingOperations.operation();
	}

	public static long debit(AccountGetterSetter accountGetterSetter) {
		long currentBalance = 0;
		long updatedBalance = 0;
		long minimumBalance = 0;
		ResultSet resultSet = null;
		Connection connection = getConnection();
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			resultSet.next();
			currentBalance = Long.parseLong(resultSet.getString(1));
			long withdrawalAmount = accountGetterSetter.getWithdrawalAmount();

			if ((currentBalance - withdrawalAmount) > minimumBalance) {
				if (withdrawalAmount == 0 || withdrawalAmount < 100 || withdrawalAmount % 10 != 0) {
					// if any of the above conditions are met, updatedBalance will be set as 0
					// and in the controller, the above conditions will be displayed to User
					// redirected to invaild withdrawal amount jsp page
					updatedBalance = 0;
				} else {
					callableStatement = connection.prepareCall(properties.getProperty("debitProcedure"));
					callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
					callableStatement.setInt(2, (int) withdrawalAmount);
					callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
					callableStatement.setInt(4,
							(int) (resultSet.getInt(1) - accountGetterSetter.getWithdrawalAmount()));
					callableStatement.executeUpdate();
					callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));

					callableStatement.setInt(1, (int) (currentBalance - accountGetterSetter.getWithdrawalAmount()));
					callableStatement.setInt(2, (int) accountGetterSetter.getAccountNumber());
					int status = callableStatement.executeUpdate();
					updatedBalance = (long) (currentBalance - accountGetterSetter.getWithdrawalAmount());
				}
			} else if ((currentBalance - withdrawalAmount) < minimumBalance) {
				System.out.println(
						"MINIMUM BALANCE OF 500 MUST BE MAINTAINED\n\nYOUR ACCOUNT BALANCE: " + currentBalance);
				updatedBalance = currentBalance - withdrawalAmount;
			}

		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return updatedBalance;
	}

	public static int balance(AccountGetterSetter accountGetterSetter) {
		int currentBalance = 0;
		Connection connection = getConnection();
		try {
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
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return currentBalance;
	}

	public static int transfer(AccountGetterSetter accountGetterSetter) {
		ResultSet resultSet = null;
		long senderCurrentBalance = 0;
		long receiverCurrentBalance = 0;
		long minimumBalance = 500;
		int status = 0;
		Connection connection = getConnection();
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			resultSet.next();

			// currentBalance of Sender
			senderCurrentBalance = Long.parseLong(resultSet.getString(1));

			long depositAmount = (long) accountGetterSetter.getDepositAmount();

			if ((senderCurrentBalance - depositAmount) > minimumBalance) {
				// Logic for Money Receiver
				// getting the balance of money receivers
				try {
					callableStatement = connection.prepareCall(properties.getProperty("getBalanceProcedure"));
					callableStatement.setInt(1, (int) accountGetterSetter.getReceiverAccountNumber());
					resultSet = callableStatement.executeQuery();
					resultSet.next();
					receiverCurrentBalance = Long.parseLong(resultSet.getString(1));
				}
				// To catch if the user of given account number is not found.
				catch (SQLException exception) {
					// Handle errors for Class.forName
					status = -1;
					exception.printStackTrace();
				}

				if (status != -1) {
					if (depositAmount == 0 || depositAmount < 100 || depositAmount % 10 != 0) {
						status = -2;
					} else {
						System.out.println(depositAmount);
						// currentBalance of receiver
						callableStatement = connection.prepareCall(properties.getProperty("creditProcedure"));
						callableStatement.setInt(1, (int) accountGetterSetter.getReceiverAccountNumber());
						callableStatement.setInt(2, (int) depositAmount);
						callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
						callableStatement.setInt(4, (int) (receiverCurrentBalance + depositAmount));
						callableStatement.executeUpdate();

						callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));
						callableStatement.setInt(1, (int) (receiverCurrentBalance + depositAmount));
						callableStatement.setInt(2, (int) accountGetterSetter.getReceiverAccountNumber());
						status = callableStatement.executeUpdate();

						// Logic for Money Sender
						callableStatement = connection.prepareCall(properties.getProperty("debitProcedure"));
						callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
						callableStatement.setInt(2, (int) depositAmount);
						callableStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
						callableStatement.setInt(4, (int) (senderCurrentBalance - depositAmount));
						callableStatement.executeUpdate();

						callableStatement = connection.prepareCall(properties.getProperty("updateBalanceProcedure"));
						callableStatement.setInt(1, (int) (senderCurrentBalance - depositAmount));
						callableStatement.setInt(2, (int) accountGetterSetter.getAccountNumber());
						status = callableStatement.executeUpdate();
						// here, status will be equal to 1
					}
				}

			} else if ((senderCurrentBalance - depositAmount) <= minimumBalance) {
				System.out.println("Minimum balance must be maintained");
				status = 0;
			}

		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		System.out.print(status);
		return status;
	}

	public static Queue<AccountGetterSetter> transactionHistory(AccountGetterSetter accountGetterSetter) {
		ResultSet resultSet = null;
		Queue<AccountGetterSetter> transaction = new LinkedList<AccountGetterSetter>();
		Connection connection = getConnection();
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\srinivasan\\Documents\\Aspire Systems\\Banking Application\\Properties.txt"));
			CallableStatement callableStatement = connection
					.prepareCall(properties.getProperty("getTransactionHistoryProcedure"));
			callableStatement.setInt(1, (int) accountGetterSetter.getAccountNumber());
			resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				// This "new AccountGetterSetter();" creates new object everytime
				// and adds it to "Queue"
				accountGetterSetter = new AccountGetterSetter();
				accountGetterSetter.setAccountNumber(resultSet.getInt(1));
				accountGetterSetter.setDepositAmount(resultSet.getInt(2));
				accountGetterSetter.setWithdrawalAmount(resultSet.getInt(3));
				accountGetterSetter.setDate(resultSet.getTimestamp(4));
				accountGetterSetter.setBalance(resultSet.getInt(5));
				transaction.add(accountGetterSetter);
			}
		} catch (IOException | SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return transaction;
	}

}

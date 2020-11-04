package bank;

import controller.UserInput;
import model.AccountGetterSetter;
import model.AccountDAO;

public class BankingOperations {
	 //long balance, depositAmount, withdrawalAmount, rateOfInterest, amount;

	public static void operation() {
		System.out.println("-----------------------");
		System.out.println(
				"ENTER THE OPERATION TO PERFORM\n 1.DEPOSIT MONEY\n 2.WITHDRAW MONEY\n 3.CHECK BALANCE\n 4.TRANSACTION HISTORY\n 5.EXIT");
		UserInput input = new UserInput();
		int choice = input.getIntegerInput();
		switch (choice) {
		case 1:
			depositMoney();
			break;
		case 2:
			withdrawMoney();
			break;
		case 3:
			checkBalance();
			break;
		case 4:
			viewTransactionHistory();
			break;
		case 5:
			break;
		default:
			System.out.println("KINDLY ENTER ONLY VALID OPTIONS");
			operation();
		}
	}

	public static void depositMoney() {
		UserInput input = new UserInput();
		System.out.println("ENTER YOUR ACCOUNT NUMBER");
		int accountNumber = input.getIntegerInput();
		System.out.println("ENTER THE AMOUNT TO DEPOSIT");
		long depositAmount = input.getLongInput();
		AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
		accountGetterSetter.setAccountNumber(accountNumber);
		if(depositAmount > 0) {
			accountGetterSetter.setDepositAmount(depositAmount);
		}
		else {
			System.out.println("AMOUNT CAN'T BE LESS THAN ZERO");
			depositAmount = input.getLongInput();
		}
		AccountDAO.credit(accountGetterSetter);
	}

	public static void withdrawMoney() {
		UserInput input = new UserInput();
		System.out.println("ENTER YOUR ACCOUNT NUMBER");
		int accountNumber = input.getIntegerInput();
		System.out.println("ENTER THE AMOUNT TO WITHDRAW");
		long withdrawalAmount = input.getLongInput();
		AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
		accountGetterSetter.setAccountNumber(accountNumber);
		accountGetterSetter.setWithdrawalAmount(withdrawalAmount);
		AccountDAO.debit(accountGetterSetter);
	}
	
	public static void checkBalance() {
		UserInput input = new UserInput();
		System.out.println("ENTER YOUR ACCOUNT NUMBER");
		int accountNumber = input.getIntegerInput();
		AccountGetterSetter accountGetterSetter = new AccountGetterSetter();
		accountGetterSetter.setAccountNumber(accountNumber);
		AccountDAO.balance(accountGetterSetter);
	}

	public static void viewTransactionHistory() {
		System.out.println("YOUR TRANSACTION HISTORY");
	}
}

package model;

public class AccountGetterSetter {
	private long balance, depositAmount, withdrawalAmount, rateOfInterest, amount, accountNumber, receiverAccountNumber;

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public long getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(long withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public long getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(long rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(long receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	enum natureOfInterest {
		SIMPLE, COMPLEX;
	}
}

package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

	public int getIntegerInput() {
		Scanner scanner = new Scanner(System.in);
		int userInteger;
		try {
			userInteger = scanner.nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("PLEASE ENTER VALID ENTRY");
			userInteger = getIntegerInput();
		}
		return userInteger;
	}

	public long getLongInput() {
		Scanner scanner = new Scanner(System.in);
		long userLong;
		try {
			userLong = scanner.nextLong();
		} catch (InputMismatchException exception) {
			System.out.println("PLEASE ENTER VALID ENTRY");
			userLong = getIntegerInput();
		}
		return userLong;
	}

	public String getStringInput() {
		Scanner scanner = new Scanner(System.in);
		String userString;
		try {
			userString = scanner.next();
		} catch (InputMismatchException exception) {
			System.out.println("PLEASE ENTER VALID ENTRY");
			userString = getStringInput();
		}
		return userString;
	}
}

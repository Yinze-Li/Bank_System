/**
 * 
 */
package control;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import entity.*;

public class AccountControl {

	private static ArrayList<Account> accountList = new ArrayList<Account>();

	/**
	 * create a junior account
	 * @param acc the account number
	 * @param c the customer object
	 */
	public static void creatJunior(int acc, Customer c) {
		if (position(acc) != -1) {
			System.out.println("account number has existed...");
			return;
		}
		if (!c.getCredit()) {
			System.out.println("you don not have a good credit to open an account!!");
			return;
		}
		Calendar now = Calendar.getInstance();
		if ((now.get(Calendar.YEAR) - c.getDob().get(Calendar.YEAR)) < 16) {
			JuniorAccount account = new JuniorAccount(acc, c);
			accountList.add(account);
		} else {
			System.out.println("You are too old to open a junior account...");
		}
	}


	/**
	 * create a current account
	 * @param acc the account number
	 * @param c the customer object
	 * @param lim overdraft limit
	 */
	public static void creatCurrent(int acc, Customer c, double lim) {
		if (position(acc) != -1) {
			System.out.println("account number has existed...");
			return;
		}
		if (!c.getCredit()) {
			System.out.println("you don not have a good credit to open an account!!");
			return;
		}
		CurrentAccount account = new CurrentAccount(acc, c, lim);
		accountList.add(account);
	}


	/**
	 * create a saver account
	 * @param acc the account number
	 * @param c the customer object
	 */
	public static void creatSaver(int acc, Customer c) {
		if (position(acc) != -1) {
			System.out.println("account number has existed...");
			return;
		}
		if (!c.getCredit()) {
			System.out.println("you don not have a good credit to open an account!!");
			return;
		}
		SaverAccount account = new SaverAccount(acc, c);
		accountList.add(account);
	}
	
	/**
	 * query balance by account number
	 * @param acc account number
	 * @return balance of the account
	 */
	public static double balance(int acc) {
		Account account = accountList.get(position(acc));
		return account.getBalance();
	}

	/**
	 * deposit
	 * @param acc account number
	 * @param amount money
	 * @param cleared cleared or not
	 * @return true when successful, false when insufficient money
	 */
	public static boolean deposit(int acc, double amount, boolean cleared) {
		Account account = accountList.get(position(acc));
		if (account.isSuspended()) {
			System.out.println("Your account " + acc + " is suspended!!!");
			return false;
		}
		if (cleared) {
			account.deposit(amount);
		}
		Calendar cal = Calendar.getInstance();
		Deposit trans = new Deposit(acc, amount, cal, cleared);
		TransactionControl.newTrans(trans);
		return true;
	}

	/**
	 * query pin by account number
	 * @param acc account number
	 * @return pin number
	 */
	public static int getPin(int acc) {
		Account account = accountList.get(position(acc));
		return account.getPin();
	}
	
	/**
	 * deposit with out generate a transaction,
	 * which used for clearing
	 * @param acc account number
	 * @param amount
	 * @return true when successful, false when insufficient money
	 */
	public static boolean deposit(int acc, double amount) {
		Account account = accountList.get(position(acc));
		if (account.isSuspended()) {
			System.out.println("Your account " + acc + " is suspended!!!");
			return false;
		}
		account.deposit(amount);
		return true;
	}

	/**
	 * withdraw
	 * @param acc account number
	 * @param amount
	 * @return true when successful, false when insufficient money
	 */
	public static boolean withdraw(int acc, double amount) {
		Account account = accountList.get(position(acc));
		if (account.check(amount)) {
			account.withdraw(amount);
			Calendar cal = Calendar.getInstance();
			Withdrawal trans = new Withdrawal(acc, amount, cal);
			TransactionControl.newTrans(trans);
			return true;
		}
		return false;
	}

	/**
	 * suspend an account
	 * @param acc
	 */
	public static void suspend(int acc) {
		accountList.get(position(acc)).setSuspended(true);
	}

	/**
	 * reinstate an account
	 * @param acc
	 */
	public static void reinstate(int acc) {
		accountList.get(position(acc)).setSuspended(false);
	}
	
	/**
	 * check if is suspended
	 * @param acc
	 * @return true when suspended
	 */
	public static boolean isSuspended(int acc) {
		Account account = accountList.get(position(acc));
		return account.isSuspended();
	}

	/**
	 * close an account
	 * @param acc
	 * @return true when successful, false when insufficient money
	 */
	public static boolean closeAccount(int acc) {
		Account account = accountList.get(position(acc));
		double balance = account.getBalance();
		if (balance < 0) {
			System.out.println("please pay " + (-balance) + " for the debts...");
			return false;
		} else if (balance > 0) {
			System.out.println("please withdraw " + balance + " to clear the account...");
			return false;
		} else {
			if (accountList.remove(account)) {
				System.out.println("this account is closed, bye bye :(");
			}
			return true;
		}
	}

	/**
	 * check if a count existed
	 * @param acc
	 * @return true when existed
	 */
	public static boolean isExisted(int acc) {
		if(position(acc) == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if pin is correct
	 * @param acc
	 * @param pin
	 * @return true when correct
	 */
	public static boolean isCorrectPin(int acc, int pin) {
		if(pin == accountList.get(position(acc)).getPin()) {
			return true;
		}
		return false;
	}

	/**
	 * check the index of an account in arrayList
	 * @param acc
	 * @return index
	 */
	private static int position(int acc) {
		for (int i = 0; i < accountList.size(); i++) {
			if (accountList.get(i).getAccNo() == acc) {
				return i;
			}
		}
		System.out.println("no such account!!!");
		return -1;
	}

	/**
	 * load all accounts from a file 
	 */
	public static void load() {
		try {
			accountList = new ArrayList<Account>();
			System.out.println("load accounts...");
			BufferedReader reader = new BufferedReader(new FileReader("src/account.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String type = item[0];
				int acc = Integer.parseInt(item[1]);
				int pin = Integer.parseInt(item[2]);
				double balance = Double.parseDouble(item[3]);
				double lim = Double.parseDouble(item[4]);
				boolean isSuspended = Boolean.parseBoolean(item[5]);
				String ID = item[6];
				Customer c = CustomerControl.getCus(ID);
				if (type.charAt(0) == 'c') {
					CurrentAccount account = new CurrentAccount(acc, c, lim);
					account.setPin(pin);
					account.setBalance(balance);
					account.setSuspended(isSuspended);
					accountList.add(account);
					System.out.println(account.toFile());
				} else if (type.charAt(0) == 'j') {
					JuniorAccount account = new JuniorAccount(acc, c);
					account.setPin(pin);
					account.setBalance(balance);
					account.setSuspended(isSuspended);
					accountList.add(account);
					System.out.println(account.toFile());
				} else if (type.charAt(0) == 's') {
					int year = Integer.parseInt(item[7]);
					int month = Integer.parseInt(item[8]) - 1;
					int date = Integer.parseInt(item[9]);
					double noticeAmount = Double.parseDouble(item[10]);
					Calendar noticeDate = Calendar.getInstance();
					noticeDate.set(year, month, date);
					SaverAccount account = new SaverAccount(acc, c);
					account.setPin(pin);
					account.setBalance(balance);
					account.setSuspended(isSuspended);
					account.setNotice(noticeDate, noticeAmount);
					accountList.add(account);
					System.out.println(account.toFile());
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * save all account to a file
	 */
	public static void save() {
		try {
			File csv = new File("src/account.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			bw.write(
					"type,account number,pin,balance,overdraft limit,suspended,customer id,notice year,notice month,notice date,notice amount");
			bw.newLine();
			for (int i = 0; i < accountList.size(); i++) {
				bw.write(accountList.get(i).toFile());
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

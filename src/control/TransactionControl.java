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

public class TransactionControl {

	private static ArrayList<Transaction> transList = new ArrayList<Transaction>();

	/**
	 * clear all uncleared funds
	 */
	public static void clear() {
		load();
		if (transList.size() == 0) {
			System.out.println("No transaction now....");
			return;
		}
		for (int i = 0; i < transList.size(); i++) {
			if (transList.get(i).toFile().charAt(0) == 'd') {
				Deposit trans = (Deposit) transList.get(i);
				if (!trans.isCleared()) {
					trans.setCleared(AccountControl.deposit(trans.getAcc(), trans.getAmount()));
				}
			}
		}
		save();
	}

	/**
	 * write a transaction to file
	 * @param t transaction
	 */
	public static void newTrans(Transaction t) {
		transList = new ArrayList<Transaction>();
		load();
		transList.add(t);
		save();
	}

	/**
	 * load all transactions from file
	 */
	public static void load() {
		transList = new ArrayList<Transaction>();
		try {
			System.out.println("loading all trans...");
			BufferedReader reader = new BufferedReader(new FileReader("src/transaction.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String type = item[0];
				int acc = Integer.parseInt(item[1]);
				double amount = Double.parseDouble(item[2]);
				int year = Integer.parseInt(item[3]);
				int month = Integer.parseInt(item[4]);
				int date = Integer.parseInt(item[5]);
				Calendar cal = Calendar.getInstance();
				cal.set(year, month - 1, date);
				if (type.charAt(0) == 'w') {
					Withdrawal trans = new Withdrawal(acc, amount, cal);
					transList.add(trans);
					System.out.println(trans.toFile());
				} else if (type.charAt(0) == 'd') {
					boolean cleared = Boolean.parseBoolean(item[6]);
					Deposit trans = new Deposit(acc, amount, cal, cleared);
					transList.add(trans);
					System.out.println(trans.toFile());
				}
			}
			reader.close();
			System.out.println("loading finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * save all transactions to file
	 */
	public static void save() {
		try {
			File csv = new File("src/transaction.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			bw.write("type,account number,amount,year,month,date,cleared");
			bw.newLine();
			for (int i = 0; i < transList.size(); i++) {
				bw.write(transList.get(i).toFile());
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

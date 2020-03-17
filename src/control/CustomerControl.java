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

import entity.Customer;

public class CustomerControl {

	private static ArrayList<Customer> customerList = new ArrayList<Customer>();

	/**
	 * add a customer to ArrayList
	 * @param c customer
	 */
	public static void addCustomer(Customer c) {
		String ID = c.getID();
		if (isExisted(ID)) {
			System.out.println("ID has been enrolled, you can enroll with this ID again");
			return;
		}
		customerList.add(c);
	}

	/**
	 * return a customer object
	 * @param ID customer id
	 * @return customer object
	 */
	public static Customer getCus(String ID) {
		if (position(ID) == -1) {
			return null;
		}
		return customerList.get(position(ID));
	}
	
	/**
	 * get credit
	 * @param ID
	 * @return credit of a customer
	 */
	public static boolean getCredit(String ID) {
		return customerList.get(position(ID)).getCredit();
	}

	/**
	 * set credit
	 * @param ID
	 * @param credit
	 */
	public static void comfirmCreditStatus(String ID, boolean credit) {
		customerList.get(position(ID)).setCredit(credit);
	}

	/**
	 * set address
	 * @param ID
	 * @param address
	 */
	public static void setAddress(String ID, String add) {
		customerList.get(position(ID)).setAddress(add);
	}

	/**
	 * set date of birth
	 * @param ID
	 * @param dob date of birth
	 */
	public static void setDoB(String ID, Calendar dob) {
		customerList.get(position(ID)).setDob(dob);
	}

	/**
	 * set name
	 * @param ID
	 * @param name
	 */
	public static void setName(String ID, String name) {
		customerList.get(position(ID)).setName(name);
	}

	/**
	 * check if a customer existed
	 * @param ID
	 * @return true when existed
	 */
	public static boolean isExisted(String ID) {
		if (position(ID) == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * return index of a customer in arrayList
	 * @param ID
	 * @return index of a customer in arrayList
	 */
	private static int position(String ID) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getID().equals(ID)) {
				return i;
			}
		}
		System.out.println("no such customer!!!");
		return -1;
	}

	/**
	 * load all customer from file
	 */
	public static void load() {
		customerList = new ArrayList<Customer>();
		try {
			System.out.println("load customers...");
			BufferedReader reader = new BufferedReader(new FileReader("src/customer.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String ID = item[0];
				String name = item[1];
				String address = item[2];
				boolean credit = Boolean.parseBoolean(item[3]);
				int year = Integer.parseInt(item[4]);
				int month = Integer.parseInt(item[5]);
				int date = Integer.parseInt(item[6]);
				Calendar dob = Calendar.getInstance();
				dob.set(year, month - 1, date);
				Customer c = new Customer(ID, name, address, dob);
				c.setCredit(credit);
				System.out.println(c.toFile());
				customerList.add(c);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * save all customer from file
	 */
	public static void save() {
		try {
			File csv = new File("src/customer.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			bw.write("ID,name,address,credit,dob_year,dob_month,dob_date");
			bw.newLine();
			for (int i = 0; i < customerList.size(); i++) {
				bw.write(customerList.get(i).toFile());
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

/**
 * 
 */
package entity;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */

import java.util.Calendar;

public class Customer {

	private String id;
	private String name;
	private String address;
	private boolean creditStatus;
	private Calendar dob;

	public Customer(String id, String name, String address, Calendar dob) {
		this.name = name;
		this.address = address;
		this.id = id;
		creditStatus = false;
		setDob(dob);
	}

	
	public String toString() {
		return "name: " + name + "\n" + "dob: " + dob + "\n" + "address: " + address;

	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return id + "," + name + "," + address + "," + creditStatus + ","
				+ dob.get(Calendar.YEAR) + "," + (dob.get(Calendar.MONTH) + 1) + "," + dob.get(Calendar.DATE);
	}


	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @return the creditStatus
	 */
	public boolean getCredit() {
		return creditStatus;
	}


	/**
	 * @return the dob
	 */
	public Calendar getDob() {
		return dob;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @param creditStatus the creditStatus to set
	 */
	public void setCredit(boolean creditStatus) {
		this.creditStatus = creditStatus;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
}

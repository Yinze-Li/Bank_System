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

public class Transaction {
	
	protected int acc;
	protected double amount;
	protected Calendar cal;

	public Transaction(int acc, double amount, Calendar cal) {
		this.acc = acc;
		this.amount = amount;
		this.cal = cal;
	}

	/**
	 * @return the acc
	 */
	public int getAcc() {
		return acc;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the cal
	 */
	public Calendar getCal() {
		return cal;
	}

	/**
	 * @param acc the acc to set
	 */
	public void setAcc(int acc) {
		this.acc = acc;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return "";
	}
	
}

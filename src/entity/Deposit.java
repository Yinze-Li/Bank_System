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

public class Deposit extends Transaction {
	
	private boolean cleared;

	public Deposit(int acc, double amount, Calendar cal, boolean cleared) {
		super(acc, amount, cal);
		this.cleared = cleared;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cleared
	 */
	public boolean isCleared() {
		return cleared;
	}

	/**
	 * @param cleared the cleared to set
	 */
	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	
	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return "deposit" + "," + acc + "," + amount + "," + cal.get(Calendar.YEAR) + ","
				+ (cal.get(Calendar.MONTH) + 1) + "," + cal.get(Calendar.DATE) + "," + cleared;
	}

}

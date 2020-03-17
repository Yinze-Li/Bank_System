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

public class Withdrawal extends Transaction {

	public Withdrawal(int acc, double amount, Calendar cal) {
		super(acc, amount, cal);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return "withdraw" + "," + acc + "," + amount + "," + cal.get(Calendar.YEAR) + ","
				+ (cal.get(Calendar.MONTH) + 1) + "," + cal.get(Calendar.DATE);
	}

}

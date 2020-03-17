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

public class SaverAccount extends Account {

	private Calendar noticeDate;
	private double noticeAmount;

	public SaverAccount(int accNo, Customer customer) {
		super(accNo, customer);
		noticeDate = Calendar.getInstance();
		noticeDate.set(1970, 0, 1);
		noticeAmount = 0.0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * set notice date and amount
	 * @param cal notice date
	 * @param amount
	 */
	public void setNotice(Calendar cal, double amount) {
		this.noticeDate = cal;
		this.noticeAmount = amount;
	}

	/**
	 * check if amount is excess
	 * @param amount
	 * @return true when not excess
	 */
	public boolean check(double amount) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -7);
		if (now.after(noticeDate)) {
			if (amount <= balance - noticeAmount) {
				return true;
			} else {
				System.out.println("Withdraw " + amount + " unsuccessfull. Do not have enough available funds.");
				return false;
			}
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfull. Your funds are not due!");
			return false;
		}

	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return "s" + "," + accNo + "," + pin + "," + balance + "," + overDraftLim + "," + isSuspended + "," + customer.getID() + "," + noticeDate.get(Calendar.YEAR) + ","
				+ (noticeDate.get(Calendar.MONTH) + 1) + "," + noticeDate.get(Calendar.DATE) + "," + noticeAmount;
	}

	/**
	 * @return the noticeDate
	 */
	public Calendar getNoticeDate() {
		return noticeDate;
	}

	/**
	 * @return the noticeAmount
	 */
	public double getNoticeAmount() {
		return noticeAmount;
	}
}

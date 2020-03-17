/**
 * 
 */
package entity;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */

import java.util.Random;

public class Account {

	protected int accNo;
	protected int pin;
	protected double balance;
	protected double overDraftLim;
	protected boolean isSuspended;
//	protected boolean isActive;
//	protected boolean noticeNeeded;
	protected Customer customer;

	public Account(int accNo, Customer customer) {
		this.accNo = accNo;
		this.customer = customer;
		this.balance = 0.0;
//		this.isActive = true;
		genPin();
	}

	/**
	 * generate a pin
	 */
	private void genPin() {
		Random r = new Random();
		pin = (100000 + r.nextInt(900000));
	}

	/**
	 * 
	 * @return account number
	 */
	public int getAccNo() {
		return accNo;
	}

	/**
	 * 
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * deposit money
	 * @param amount
	 */
	public void deposit(double amount) {
		balance = balance + amount;
		System.out.println("Deposit " + amount + " successful");
	}

	/**
	 * withdraw
	 * @param amount
	 */
	public void withdraw(double amount) {
		if (check(amount)) {
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfull.");
		}
	}

	/**
	 * check if amount is excess
	 * @param amount
	 * @return true when not excess
	 */
	public boolean check(double amount) {
		boolean allowed = false;
		if (balance - amount >= 0) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfull. Do not have enough available funds.");
		}
		return allowed;
	}

	public String toString() {
		return "Account number: " + accNo + "\n" + "Balance: " + balance + "\n";
	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return accNo + "," + pin + "," + balance + "," + overDraftLim + "," + isSuspended + "," + customer.getID();
	}

	/**
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the overDraftLim
	 */
	public double getOverDraftLim() {
		return overDraftLim;
	}

	/**
	 * @return the isSuspended
	 */
	public boolean isSuspended() {
		return isSuspended;
	}

//	/**
//	 * @return the isActive
//	 */
//	public boolean isActive() {
//		return isActive;
//	}
//
//	/**
//	 * @return the noticeNeeded
//	 */
//	public boolean isNoticeNeeded() {
//		return noticeNeeded;
//	}

	/**
	 * @param accNo the accNo to set
	 */
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	/**
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @param overDraftLim the overDraftLim to set
	 */
	public void setOverDraftLim(double overDraftLim) {
		this.overDraftLim = overDraftLim;
	}

	/**
	 * @param isSuspended the isSuspended to set
	 */
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

//	/**
//	 * @param isActive the isActive to set
//	 */
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	/**
//	 * @param noticeNeeded the noticeNeeded to set
//	 */
//	public void setNoticeNeeded(boolean noticeNeeded) {
//		this.noticeNeeded = noticeNeeded;
//	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

/**
 * 
 */
package entity;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */

public class CurrentAccount extends Account {

	public CurrentAccount(int accNo, Customer customer, double overDraftLim) {
		super(accNo, customer);
		setOverDraft(overDraftLim);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * set overdraft limit
	 * @param lim overdraft limit
	 */
	public void setOverDraft(double lim) {
		overDraftLim = lim;
	}

	/**
	 * check if amount is excess
	 * @param amount
	 * @return true when not excess
	 */
	public boolean check(double amount) {
		boolean allowed = false;
		if (balance - amount >= -overDraftLim) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfull. Do not have enough available funds.");
		}
		return allowed;
	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return "c" + "," + accNo + "," + pin + "," + balance + "," + overDraftLim + "," + isSuspended + "," + customer.getID();
	}

}

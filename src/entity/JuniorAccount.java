/**
 * 
 */
package entity;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */
public class JuniorAccount extends Account {

	public JuniorAccount(int accNo, Customer customer) {
		super(accNo, customer);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return String which write to file
	 */
	public String toFile() {
		return "j" + "," + accNo + "," + pin + "," + balance + "," + overDraftLim + "," + isSuspended + "," + customer.getID();
	}

}

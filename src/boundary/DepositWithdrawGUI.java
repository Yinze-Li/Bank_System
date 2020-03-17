/**
 * 
 */
package boundary;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.AccountControl;

public class DepositWithdrawGUI implements ActionListener {

	private int acc;
	private JFrame frame;
	private JLabel label;
	private JTextField amount;
	private JButton unclearedDeposit;
	private JButton deposit;
	private JButton withdraw;
	private JPanel panel;

	public DepositWithdrawGUI(int acc) {
		this.acc = acc;
		startFrame();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == deposit || arg0.getSource() == unclearedDeposit || arg0.getSource() == withdraw) {
			try {
				double money = Double.parseDouble(amount.getText());
				if (arg0.getSource() == deposit) {
					AccountControl.deposit(acc, money, true);
				} else if (arg0.getSource() == unclearedDeposit) {
					AccountControl.deposit(acc, money, false);
				} else if (arg0.getSource() == withdraw) {
					if (!AccountControl.withdraw(acc, money)) {
						label.setText("you don't have enough funds!!!" + "\tBalance: " + AccountControl.balance(acc));
						amount.setText("");
						return;
					}
				}
				label.setText("Operate successfully!!!" + "\tBalance: " + AccountControl.balance(acc));
				amount.setText("");
				AccountControl.save();
			} catch (Exception e) {
				label.setText("invalid input!!!" + "\tBalance: " + AccountControl.balance(acc));
				amount.setText("");
			}
		}
	}

	/**
	 * initialize the GUI
	 */
	private void startFrame() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(800, 400);

		if (AccountControl.isSuspended(acc)) {
			label = new JLabel("Your account is suspended!!!!!!!!");
			frame.add(BorderLayout.CENTER, label);
		} else {
			label = new JLabel("enter the amount!!!" + "\tBalance: " + AccountControl.balance(acc));

			amount = new JTextField(5);

			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 3));

			unclearedDeposit = new JButton("uncleared-deposit");
			unclearedDeposit.addActionListener(this);
			deposit = new JButton("deposit");
			deposit.addActionListener(this);
			withdraw = new JButton("withdraw");
			withdraw.addActionListener(this);

			frame.add(BorderLayout.NORTH, label);
			frame.add(BorderLayout.CENTER, amount);
			panel.add(unclearedDeposit);
			panel.add(deposit);
			panel.add(withdraw);
			frame.add(BorderLayout.SOUTH, panel);
		}

	}

}

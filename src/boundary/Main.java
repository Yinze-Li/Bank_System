/**
 * 
 */
package boundary;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */


import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Main implements ActionListener {

	private JFrame frame;
	private JButton acc;
	private JButton clear;
	private JButton cus;
	private JButton log;
	private JButton open;
	private JButton credit;

	/**
	 * initialize the GUI
	 */
	private void start() {

		frame = new JFrame();
		frame.setLayout(new GridLayout(6, 0));

		acc = new JButton("account management");
		acc.addActionListener(this);
		frame.add(acc);

		clear = new JButton("clear");
		clear.addActionListener(this);
		frame.add(clear);

		cus = new JButton("customer enroll");
		cus.addActionListener(this);
		frame.add(cus);

		log = new JButton("log in to deposit & withdraw");
		log.addActionListener(this);
		frame.add(log);

		open = new JButton("open account");
		open.addActionListener(this);
		frame.add(open);

		credit = new JButton("credit status");
		credit.addActionListener(this);
		frame.add(credit);

		frame.setVisible(true);
		frame.setSize(800, 400);
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
//		LogInGUI l = new LogInGUI();
//		CustomerEnrollGUI c = new CustomerEnrollGUI();
//		CreditGUI c1 = new CreditGUI();
//		AccountManageGUI a = new AccountManageGUI();
//		OpenAccountGUI o = new OpenAccountGUI();
//		ClearGUI c2 = new ClearGUI();
		Main m = new Main();
		m.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == acc) {
			AccountManageGUI a = new AccountManageGUI();
		}
		if (e.getSource() == clear) {
			ClearGUI c2 = new ClearGUI();
		}
		if (e.getSource() == cus) {
			CustomerEnrollGUI c = new CustomerEnrollGUI();
		}
		if (e.getSource() == log) {
			LogInGUI l = new LogInGUI();
		}
		if (e.getSource() == open) {
			OpenAccountGUI o = new OpenAccountGUI();
		}
		if (e.getSource() == credit) {
			CreditGUI c1 = new CreditGUI();
		}
	}

}

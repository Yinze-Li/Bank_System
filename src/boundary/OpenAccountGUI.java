/**
 * 
 */
package boundary;

/**
 * @author Yinze Li
 * @Date 2019年5月12日下午7:39:39
 * TODO
 */


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.AccountControl;
import control.CustomerControl;
import entity.Customer;

public class OpenAccountGUI implements ActionListener {

	private JFrame frame;
	private JLabel label;
	private JLabel hint;
	private JTextField type;
	private JTextField id;
	private JTextField acc;
	private JTextField lim;
	private JButton enroll;

	public OpenAccountGUI() {
		// TODO Auto-generated constructor stub
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enroll) {
			String id = this.id.getText();
			int acc;
			Customer c;
			if (!CustomerControl.isExisted(id)) {
				label.setText("please enroll customer then open an account");
				this.id.setText("");
				return;
			}
			if (!CustomerControl.getCredit(id)) {
				label.setText("you don't have a good credit");
				this.id.setText("");
				return;
			}
			c = CustomerControl.getCus(id);
			try {
				acc = Integer.parseInt(this.acc.getText());
				if (AccountControl.isExisted(acc)) {
					label.setText("this account number is enrolled by others");
					this.acc.setText("");
					return;
				}
			} catch (Exception e1) {
				label.setText("invalid input of account number");
				this.acc.setText("");
				return;
			}
			if (type.getText().charAt(0) == 'c') {
				double lim;
				try {
					lim = Double.parseDouble(this.lim.getText());
					AccountControl.creatCurrent(acc, c, lim);
					label.setText("success!!! Pin is " + AccountControl.getPin(acc));
					AccountControl.save();
				} catch (Exception e2) {
					label.setText("invalid input of overdraft limit");
					this.lim.setText("");
					return;
				}

			} else if (type.getText().charAt(0) == 'j') {
				Calendar now = Calendar.getInstance();
				if (now.get(Calendar.YEAR) - c.getDob().get(Calendar.YEAR) >= 16) {
					label.setText("customers who are under 16 can open a junior account!!!");
					type.setText("");
					return;
				} else {
					AccountControl.creatJunior(acc, c);
					label.setText("success!!! Pin is " + AccountControl.getPin(acc));
					AccountControl.save();
				}
			} else if (type.getText().charAt(0) == 's') {
				AccountControl.creatSaver(acc, c);
				label.setText("success!!! Pin is " + AccountControl.getPin(acc));
			}
			AccountControl.save();

		}

	}

	/**
	 * initialize the GUI
	 * 
	 */
	private void startFrame() {
		CustomerControl.load();
		AccountControl.load();
		frame = new JFrame();
		frame.setLayout(new GridLayout(7, 0));

		label = new JLabel();
		hint = new JLabel(
				"enter customer id at first line, account NO at sencond line, type of account at third line, overdraft limit at fourth line if it's an current account");
		id = new JTextField();
		acc = new JTextField();
		type = new JTextField();
		lim = new JTextField();
		enroll = new JButton("enroll");
		enroll.addActionListener(this);

		frame.add(hint);
		frame.add(label);
		frame.add(id);
		frame.add(acc);
		frame.add(type);
		frame.add(lim);
		frame.add(enroll);

		frame.setVisible(true);
		frame.setSize(800, 400);
	}

}

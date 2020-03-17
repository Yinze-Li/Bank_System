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

import javax.swing.*;

import control.AccountControl;
import control.CustomerControl;

public class AccountManageGUI implements ActionListener {

	private JFrame frame;
	private JLabel label;
	private JTextField acc;
	private JButton suspend;
	private JButton reinstate;
	private JButton close;

	public AccountManageGUI() {
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() != null) {
			try {
				int acc = Integer.parseInt(this.acc.getText());
				if (!AccountControl.isExisted(acc)) {
					label.setText("no such account");
					this.acc.setText("");
					return;
				}
				if (e.getSource() == suspend) {
					AccountControl.suspend(acc);
					label.setText("success");
				}
				if (e.getSource() == reinstate) {
					AccountControl.reinstate(acc);
					label.setText("success");
				}
				if (e.getSource() == close) {
					if (!AccountControl.closeAccount(acc)) {
						label.setText("please clear the funds before close the account");
					} else {
						label.setText("success");
					}
				}
				AccountControl.save();
			} catch (Exception e1) {
				label.setText("invalid input!!");
				acc.setText("");
			}
		}

	}

	/**
	 * initialize the GUI
	 */
	private void startFrame() {
		CustomerControl.load();
		AccountControl.load();
		frame = new JFrame();
		frame.setLayout(new GridLayout(5, 0));

		label = new JLabel("input the account NO.");
		acc = new JTextField();
		suspend = new JButton("suspend");
		suspend.addActionListener(this);
		reinstate = new JButton("re-instate");
		reinstate.addActionListener(this);
		close = new JButton("close");
		close.addActionListener(this);

		frame.add(label);
		frame.add(acc);
		frame.add(suspend);
		frame.add(reinstate);
		frame.add(close);
		frame.setVisible(true);
		frame.setSize(800, 400);
	}

}

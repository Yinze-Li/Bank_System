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

import javax.swing.*;

import control.CustomerControl;
import entity.Customer;

public class CustomerEnrollGUI implements ActionListener {

	private JFrame frame;
	private JLabel label;
	private JTextField id;
	private JTextField name;
	private JTextField add;
	private JTextField dob;
	private JButton enroll;

	public CustomerEnrollGUI() {
		// TODO Auto-generated constructor stub
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enroll) {
			String id = this.id.getText();
			if(CustomerControl.isExisted(id)) {
				label.setText("id has been enrolled!!!");
				return;
			}
			String name = this.name.getText();
			String add = this.add.getText();
			String dob = this.dob.getText();
			String[] item = dob.split(",");
			Calendar cal = Calendar.getInstance();
			try {
				int year = Integer.parseInt(item[0]);
				int month = Integer.parseInt(item[1]);
				int date = Integer.parseInt(item[2]);
				cal.set(year, month - 1, date);
				Calendar now = Calendar.getInstance();
				if(cal.after(now)) {
					label.setText("invalid input of dob");
					this.dob.setText("");
					return;
				}
				Customer c = new Customer(id,name,add,cal);
				CustomerControl.addCustomer(c);
				label.setText("success!!!");
				CustomerControl.save();
			} catch (Exception e1) {
				label.setText("invalid input of dob");
				this.dob.setText("");
			}
		}

	}

	/**
	 * initialize the GUI
	 */
	private void startFrame() {

		CustomerControl.load();
		frame = new JFrame();
		frame.setLayout(new GridLayout(6, 0));

		label = new JLabel(
				"please enter your id at first line, name at second line, address at third line, dob at fourth line, ie. 1998,4,25");
		id = new JTextField();
		name = new JTextField();
		add = new JTextField();
		dob = new JTextField();

		enroll = new JButton("enroll");
		enroll.addActionListener(this);

		frame.add(label);
		frame.add(id);
		frame.add(name);
		frame.add(add);
		frame.add(dob);
		frame.add(enroll);

		frame.setVisible(true);
		frame.setSize(800, 400);
	}

}

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

import control.CustomerControl;

public class CreditGUI implements ActionListener {

	private JFrame frame;
	private JLabel label;
	private JTextField id;
	private JButton good;
	private JButton bad;

	public CreditGUI() {
		// TODO Auto-generated constructor stub
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == good || e.getSource() == bad) {
			String id = this.id.getText();
			if (!CustomerControl.isExisted(id)) {
				label.setText("nobody has this id");
				return;
			}
			label.setText("Customer " + id + " 's credit:" + CustomerControl.getCredit(id));
			if(e.getSource() == good) {
				CustomerControl.comfirmCreditStatus(id, true);
				label.setText("Customer " + id + " 's credit:" + CustomerControl.getCredit(id));
				CustomerControl.save();
			}
			if(e.getSource() == bad) {
				CustomerControl.comfirmCreditStatus(id, false);
				label.setText("Customer " + id + " 's credit:" + CustomerControl.getCredit(id));
				CustomerControl.save();
			}
		}
	}

	/**
	 * initialize the GUI
	 */
	private void startFrame() {
		CustomerControl.load();
		frame = new JFrame();
		frame.setLayout(new GridLayout(4, 0));

		label = new JLabel("enter id");
		id = new JTextField();
		good = new JButton("good credit");
		bad = new JButton("bad credit");
		good.addActionListener(this);
		bad.addActionListener(this);

		frame.add(label);
		frame.add(id);
		frame.add(good);
		frame.add(bad);

		frame.setVisible(true);
		frame.setSize(800, 400);
	}

}

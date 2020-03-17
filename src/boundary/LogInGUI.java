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

public class LogInGUI implements ActionListener {

	private JFrame frame;
	private JLabel label;
	private JTextField accText;
	private JTextField pinText;
	private JButton log;

	public LogInGUI() {
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == log) {
			try {
				int acc = Integer.parseInt(accText.getText());
				int pin = Integer.parseInt(pinText.getText());
				if(!AccountControl.isExisted(acc)) {
					label.setText("no such account!!!");
					accText.setText("");
					return;
				}
				if(!AccountControl.isCorrectPin(acc, pin)) {
					label.setText("wrong pin!!!");
					pinText.setText("");
					return;
				}
				DepositWithdrawGUI dwg = new DepositWithdrawGUI(acc);
				dwg.toString();
			}catch(Exception e) {
				label.setText("invalid input!!");
				pinText.setText("");
				accText.setText("");
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
		frame.setLayout(new GridLayout(4,0));
		
		label = new JLabel("type your acc NO at first line and pin at second line!");
		
		accText = new JTextField();
		pinText = new JTextField();
		
		log = new JButton("log");
		log.addActionListener(this);
		
		frame.add(label);
		frame.add(accText);
		frame.add(pinText);
		frame.add(log);
		frame.setVisible(true);
		frame.setSize(800, 400);
	}
	

}

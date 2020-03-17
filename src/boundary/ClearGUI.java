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

import javax.swing.*;

import control.AccountControl;
import control.CustomerControl;
import control.TransactionControl;

public class ClearGUI {
	
	private JFrame frame;
	private JTextArea area;

	public ClearGUI() {
		startFrame();
	}
	
	/**
	 * initialize the GUI
	 */
	private void startFrame() {
		CustomerControl.load();
		AccountControl.load();
		frame = new JFrame();
		frame.setLayout(new GridLayout(1, 0));
		area = new JTextArea();
		frame.add(area);
		frame.setVisible(true);
		frame.setSize(800, 400);
		clear();
	}

	/**
	 * clear the uncleared funds
	 * @param
	 * @return
	 */
	private void clear() {
		TransactionControl.clear();
		AccountControl.save();
		area.setText("clear successfully!!!");
	}

}

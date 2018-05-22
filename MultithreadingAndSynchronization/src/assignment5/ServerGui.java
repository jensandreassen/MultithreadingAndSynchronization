package assignment5;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Copy of GUIChat with a few alterations
 * @author Jens Andreassen
 *
 */

public class ServerGui {
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;				// The Main window
	private JTextArea lstMsg;			// The logger listbox

	/**
	 * Constructor
	 */
	public ServerGui(){
	}
	
	/**
	 * Starts the application
	 */
	public void start()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multi Chat Server/Client");			// Change to "Multi Chat Server" on server part and vice versa 
		initializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void initializeGUI()
	{
		
		lstMsg = new JTextArea();
		lstMsg.setEditable(false);
		JScrollPane pane = new JScrollPane(lstMsg);
		pane.setBounds(12, 51, 260, 199);
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(pane);
	}
	/**
	 * for output to the user
	 * @param str text to update
	 */
	public void updateText(String str) {
		lstMsg.append(str + "\n");
	}
}

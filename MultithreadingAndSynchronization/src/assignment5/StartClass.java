package assignment5;

import javax.swing.JOptionPane;
/**
 * Start-class for chat applikation, starts server and requested number of clients
 * @author Jens Andreassen
 *
 */
public class StartClass {
	/**
	 * ATTENTION, using this method to start the applikations, has the result of making
	 * the whole application shutdown on any of the windows closing. There is a main-method in the 
	 * class Client for trying to disconnect one client at a time.
	 * @param args
	 */
	public static void main(String[] args) {
		int clients = Integer.parseInt(JOptionPane.showInputDialog("hur många klienter?"));
		new Server(new ServerGui(), 5020);
		
		for (int i=0; i<clients; i++) {
			new Client("localhost", 5020);
		}
	}
}

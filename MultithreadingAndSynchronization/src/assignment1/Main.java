package assignment1;

import java.io.IOException;

import javax.swing.SwingUtilities;
/**
 * Class acts as a starter for the application. Starts Gui on edt thread
 * @author Jens Andreassen
 *
 */
public class Main {
		public static void main(String[] args) throws IOException {
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	Gui gui = new Gui();
					try {
						gui.Start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		}
}

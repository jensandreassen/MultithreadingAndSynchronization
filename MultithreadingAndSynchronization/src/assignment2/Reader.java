package assignment2;

import java.util.ArrayList;
import java.util.Random;
/**
 * Reader for the application
 * @author Jens Andreassen
 *
 */
public class Reader extends Thread{
	private CharBuffer buffer;
	private GUIMutex gui;
	/**
	 * Constructor
	 * @param buffer
	 * @param gui
	 */
	public Reader(CharBuffer buffer, GUIMutex gui) {
		this.buffer = buffer;
		this.gui = gui;
	}
	/**
	 * Run method for thread, reads a letter a time from the text 
	 * and updates the gui accordingly
	 */
	public void run() {
		Random r = new Random();
		ArrayList<Character> chars  = new ArrayList<Character>();
		while(true) {
			try {
				Thread.sleep(r.nextInt(2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			char c = buffer.read();
			if(c=='*') {// to know when to stop
				break;
			}
			chars.add(c);
			gui.updateReaderLog(c);
		}
		StringBuilder builder = new StringBuilder(chars.size());
	    for(Character ch: chars){
	        builder.append(ch);
	    }
		gui.updateRecieved(builder.toString());
	}
}

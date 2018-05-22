package assignment2;

import java.util.Random;
/**
 * Writer for the application
 * @author Jens Andreassen
 *
 */
public class Writer extends Thread {
	private CharBuffer buffer;
	private GUIMutex gui;
	private char[] chars;
	/**
	 * Constructor
	 * @param buffer
	 * @param gui
	 * @param text
	 */
	public Writer(CharBuffer buffer, GUIMutex gui, String text) {
		this.buffer = buffer;
		this.gui = gui;
		chars = text.toCharArray();
	}
	/**
	 * Run method for thread, writes a letter a time from the text 
	 * and updates the gui accordingly
	 */
	public void run() {
		Random r = new Random();
		for(char chr : chars) {
			try {
				Thread.sleep(r.nextInt(2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer.write(chr);
			gui.updateWriterLog(chr);
		}
		buffer.write('*');// to know when to stop
		gui.updateTransmitted(new String(chars));
	}
}

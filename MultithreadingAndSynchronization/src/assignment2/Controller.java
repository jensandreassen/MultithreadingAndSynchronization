package assignment2;
/**
 * Acts as controller for cleaner code
 * @author Jens Andreassen
 *
 */
public class Controller {
	private GUIMutex gui;
	/**
	 * Constructor
	 * @param gui
	 */
	public Controller(GUIMutex gui) {
		this.gui = gui;
	}
	/**
	 * Starts sequenze Synchronized
	 * @param text
	 */
	public void transferSync(String text) {
		CharBufferSync buffer = new CharBufferSync();
		new Writer(buffer, gui, text).start();
		new Reader(buffer, gui).start();
	}
	/**
	 * Starts async sequenze
	 * @param text
	 */
	public void transferASync(String text) {
		CharBuffer buffer = new CharBuffer();
		new Writer(buffer, gui, text).start();
		new Reader(buffer, gui).start();
	}
}

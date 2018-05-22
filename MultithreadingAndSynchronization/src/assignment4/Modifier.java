package assignment4;
/*
 * Represents a modifier-thread
 * @author Jens Andreassen
 *
 */
public class Modifier extends Thread {
	private BoundedBuffer buffer;
	/**
	 * Constructor
	 * @param buffer
	 */
	public Modifier(BoundedBuffer buffer) {
		this.buffer = buffer;
	}
	/**
	 * run method for thread, calls buffer until it returns true. 
	 */
	public void run() {
		Boolean run = false;
		while(!run) {
			run = buffer.modify();
		}
		System.out.println("Modifier done");
	}
}

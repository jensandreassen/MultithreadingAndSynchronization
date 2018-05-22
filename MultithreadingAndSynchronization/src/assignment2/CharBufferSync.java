package assignment2;
/**
 * Synchronized character buffer for the application
 * Handles characters to and from writer and readers
 * @author Jens ANdreassen
 *
 */
public class CharBufferSync extends CharBuffer {
	private Character c;
	/**
	 * Write method, takes a character and stores if no character is stored and notifys waiting
	 * readers. Otherwise waits until notified.
	 */
	public void write(char chr) {
		synchronized(this) {
			if(c==null) {
				c=chr;
				notify();
			} else {
				try {
					wait();
					c=chr;
					notify();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Read Method, returns a character and notifies waiting threads, if character is null
	 * then waits for notification.
	 */
	public char read() {
		synchronized(this) {
			if(c==null) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			char a = c;
			c=null;
			notify();
			return a;
		}
	}
}

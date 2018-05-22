package assignment2;
/**
 * Async buffer for reading and writiing to.
 * @author Jens Andreassen
 *
 */
public class CharBuffer {
	private char c;
	
	public void write(char chr) {
		c=chr;
	}

	public char read() {
		return c;
	}
}

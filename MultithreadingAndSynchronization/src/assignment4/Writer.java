package assignment4;
/*
 * Represents a writer-thread
 * @author Jens Andreassen
 *
 */
public class Writer extends Thread{
	private BoundedBuffer buffer;
	private String[] textArr;
	/**
	 * Constructor
	 * @param text text to put into buffer
	 * @param buffer
	 */
	public Writer(String text, BoundedBuffer buffer) {
		this.buffer = buffer;
		textArr = text.split("\n");
	}
	/**
	 * run-method for thread. 
	 * puts the contents of the text array passed to constructor 
	 * into the buffer until end, then puts the line *end* to signal readers and modifiers
	 */
	public void run() {
		for(int i=0;i<textArr.length;i++) {
			buffer.writeData(textArr[i]);
		}
		buffer.writeData("*end*");
		System.out.println("Writer done!");
	}
}

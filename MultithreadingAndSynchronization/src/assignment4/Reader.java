package assignment4;

import java.util.ArrayList;

import javax.swing.JTextPane;
/*
 * Represents a reader-thread
 * @author Jens Andreassen
 *
 */
public class Reader extends Thread{
	
	private ArrayList<String> text;
	private BoundedBuffer buffer;
	private JTextPane txtPane;
	/**
	 * Constructor
	 * @param buffer 
	 * @param txtPaneDest
	 */
	public Reader(BoundedBuffer buffer, JTextPane txtPaneDest) {
		this.buffer = buffer;
		this.txtPane = txtPaneDest;
		text = new ArrayList<String>();
	}
	/**
	 * run method for thread. reads from buffer until
	 * *end* is found.
	 */
	public void run() {
		String read;
		while(true) {
			read = buffer.readData();
			if(read.contains("*end*")) {
				break;
			} else {
				text.add(read);
			}
		}
		System.out.println("Reader done!");
		updateGui();
	}
	/**
	 * updates gui with all read text.
	 */
	private void updateGui() {
		StringBuilder string = new StringBuilder();
		for (int i =0; i<text.size(); i++) {
			string.append(text.get(i) + "\n");
		}
		txtPane.setText(string.toString());
	}
}

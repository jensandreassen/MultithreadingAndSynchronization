package assignment5;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JTextArea;
/**
 * Client-class for chat application
 * @author Jens Andreassen
 *
 */
public class Client {
	private JTextArea lstMsg; // för feedback till user
	private Socket socket;
	private ObjectOutputStream oos;
	private int port;
	private String host;
	private GUIChat gui;
	/**
	 * Constructor
	 * @param host ip
	 * @param port port to connect to
	 */
	public Client(String host, int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
		this.host = host;
		this.gui = new GUIChat(this);
		connect();
	}
	/**
	 * sends message to server
	 * @param text
	 */
	public void send(String text) {
		// TODO Auto-generated method stub
		try {
			oos.writeObject(text);
			oos.flush();
			gui.updateText("Sent: " + text);
		} catch (IOException e) {
			gui.updateText("SYSTEM: Error, disconnecting");
			disconnect();
		}
	}
	/**
	 * Connects to server
	 */
	public void connect() {
		// TODO Auto-generated method stub
		try {
			socket = new Socket(host ,port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			gui.updateText("SYSTEM: Connected to server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Disconnects from server
	 */
	public void disconnect() {
		try {
			if (oos != null) {
				oos.flush();
				oos.close();
			}
		} catch (IOException e) {}
		this.oos = null;
		gui.updateText("SYSTEM: Disconnected, shutting down client");
		System.exit(0);
	}
	
	/**
	 * Main-method for testing shutting down single windows
	 * @param args
	 */
	public static void main(String[] args) {
		new Client("localhost", 5020);
	}
}

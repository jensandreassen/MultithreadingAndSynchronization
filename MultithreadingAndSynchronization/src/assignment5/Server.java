package assignment5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * Server-class for chat application
 * @author Jens Andreassen
 *
 */
public class Server {
	private int port;
	private Accepter accepter;
	private ServerGui serverGui;
	private ThreadPoolExecutor executor;
	/**
	 * Constructor
	 * @param serverGui for output to user
	 * @param port port to listen to
	 */
	public Server(ServerGui serverGui, int port) {
		this.port = port;
		this.serverGui = serverGui;
		serverGui.start();
		accepter = new Accepter();
		accepter.start();
		executor =  (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}
	/**
	 * Listener for new connections, adds them to ThreadpoolExecutor
	 * @author Jens Andreassen
	 *
	 */
	private class Accepter extends Thread {
		private ServerSocket serverSocket = null;
		private int count;
		
		public void close() {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * Run-method for thread, listens for new connections and adds them
		 * to threadpool
		 */
		public void run() {
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(port);
				serverGui.updateText("SYSTEM: Listening on port: " + port);
				while (!Thread.interrupted()) {
					try {
						socket = serverSocket.accept();
						executor.execute(new Reciever(socket, ++count));
						serverGui.updateText("SYSTEM: Client " + count +" connected");
					} catch (SocketException e) {
						serverSocket = null;
						System.out.println("ServerSocket closed");
					} catch (IOException e) {
						if (socket != null) {
							socket.close();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ServerSocket error: Port already in use?");
			} finally {
				close();
			}
		}
	}
	/**
	 * Task handling the connections in the threadpool
	 * @author Jens Andreassen
	 *
	 */
	private class Reciever implements Runnable{
		private Socket socket;
		private int number;
		public Reciever(Socket socket, int number) {
			this.socket = socket;
			this.number = number;
		}
		/**
		 * run-method for thread, updates gui with new messages.
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ObjectInputStream ois;
			System.out.println("ny tråd startad");
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				while(true) {
					serverGui.updateText("Client#" + number +": " + (String)ois.readObject());
					System.out.println("meddelande mottaget");
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				serverGui.updateText("SYSTEM: Client #"+ number + " disconnected");
				System.out.println("Client #"+ number + " disconnected");
			}
		}
	}
}

package assignment1;
/**
 * Class represents a thread for handling task2 = moving image in game.
 * @author Jens Andreassen
 *
 */
public class Task2 extends Thread {
	private Gui gui;
	private volatile boolean running = true;
	private int level;
	
	/**
	 * Constructor
	 * @param gui for output
	 * @param level skilllevel
	 */
	public Task2(Gui gui, int level) {
		this.gui = gui;
		this.level = level+1;
	}
	/**
	 * Sets flag for shutdown
	 */
	public void shutdown() {
		running = false;
	}
	/**
	 * Sets the game up and moves the piece according to skill level
	 */
	public void run() {
		gui.initializeGame();
		System.out.println("Task2 Startad");
		while (running) {
			try {
				gui.setGamePiece();
				Thread.sleep(level*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}

		}
		System.out.println("Task2 Stoppad");
	}

}

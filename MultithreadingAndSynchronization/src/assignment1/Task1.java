package assignment1;
/**
 * Class represents a thread for handling task1 = moving text in display.
 * @author Jens Andreassen
 *
 */
public class Task1 extends Thread{
	private Gui gui;
	private volatile boolean running = true;
	private String threadName;
	/**
	 * Contstructor
	 * @param gui for output
	 * @param threadName for dispaly
	 */
	public Task1(Gui gui, String threadName) {
		this.threadName = threadName;
		this.gui = gui;
	}
	/**
	 * sets flag for shutdown
	 */
	public void shutdown() {
		running = false;
	}
	/**
	 * Sets up the display and moves the text by calling gui
	 */
	@Override
	public void run() {
		gui.initDisplay(threadName);
		System.out.println("Task1 Startad");
		while (running) {
			try {
				gui.moveDisplay();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
		System.out.println("Task1 Stoppad");
	}
}

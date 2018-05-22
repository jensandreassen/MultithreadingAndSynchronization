package assignment1;
/**
 * Acts as Controller-class for the application. handles
 * logic and so on.
 * @author Jens Andreassen
 *
 */
public class Controller {
	private Task1 thread1;
	private Task2 thread2;
	private int gameHits = 0;
	private int gameMiss = 0;
	private Gui gui;
	/**
	 * Constructor
	 * @param gui for output ot user
	 */
	public Controller(Gui gui) {
		this.gui = gui;
	}
/**
 * Starts an instance of task1 if not already started, if started it terminates it.
 * @param startStop true or false. if it should be started or not
 */
	public void task1(boolean startStop) {
		if(startStop) {
			thread1 = new Task1(gui, "Tråd 1");
			thread1.start();
		} else if(thread1!=null)  {
			thread1.shutdown();
			thread1 = null;
		}	
	}
/**
 * Starts an instance of task2 wich is a game. Selected index represents a sill-level.
 * @param selectedIndex skill level
 */
	public void task2(int selectedIndex) {
		thread2 = new Task2(gui, selectedIndex);
		thread2.start();
	}
/**
 * handles logik for game. hits and misses
  * @param b miss or hit.
 */
	public void gameClick(boolean b) {
		if(b) {
			gui.setHits(++gameHits);
			if(gameHits==5) {
				gui.endGame("Du vann, bra jobbat!");
				gameHits=0;
				gui.setHits(0);
				thread2.shutdown();
				thread2 = null;
			}
		} else {
			gameMiss++;
			if(gameMiss==10) {
				gui.endGame("Du förlorade, prova igen!");
				gui.setHits(0);
				thread2.shutdown();
				thread2 = null;
			}
		}
	}
/**
 * Closes task threads for shutdown of application.
 */
	public void close() {
		if(thread1!=null) {
			thread1.shutdown();
		} if(thread2!=null) {
			thread2.shutdown();
		}
		
	}

}

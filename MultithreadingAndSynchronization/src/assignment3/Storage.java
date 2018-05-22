package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import javax.swing.JProgressBar;
/**
 * Represents storage for the FoodItems between recieving and shipping
 * Handles synchronization between producer and comsumer-threads with Semaphores
 * @author Jens Andreassen
 *
 */
public class Storage {
	private LinkedList<FoodItem> queue;
	private JProgressBar bufferStatus;
	private Semaphore mutex = new Semaphore(1), full = new Semaphore(0), empty;
	/**
	 * Constructor
	 * @param capacity Size of storage
	 * @param bufferStatus For gui updates
 	 */
	public Storage(int capacity, JProgressBar bufferStatus) {
		this.queue = new LinkedList<FoodItem>();
		this.bufferStatus = bufferStatus;
		empty = new Semaphore(capacity);
		bufferStatus.setMaximum(capacity);
	}
	/**
	 * For putting into storage
	 * @param item
	 */
	public void put(FoodItem item) {
		try {
			empty.acquire();
			mutex.acquire();
			queue.addLast(item);
			bufferStatus.setValue(bufferStatus.getValue() + 1);
			mutex.release();
			full.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * For getting from storage
	 * @return
	 */
	public FoodItem get() {
		FoodItem food = null;
		try {
			full.acquire();
			mutex.acquire();
			food = queue.removeFirst();
			bufferStatus.setValue(bufferStatus.getValue() - 1);
			mutex.release();
			empty.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;
	}
}

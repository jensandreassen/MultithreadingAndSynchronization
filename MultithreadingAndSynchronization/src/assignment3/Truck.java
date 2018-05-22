package assignment3;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 * Represents a truck for comsuming from storage
 * @author Jens Andreassen
 *
 */
public class Truck extends Thread {
	private double maxWeight;
	private double maxVolume;
	private int maxItems;
	private double curWeight;
	private double curVolume;
	private int curItems = 0;

	private Storage storage;
	private Random r = new Random();
	private volatile boolean running = true;

	private JLabel weight;
	private JLabel volume;
	private JLabel items;
	private JLabel status;
	private JTextArea lst;
	private boolean checked;
	/**
	 * Constructor
	 * @param storage For consuming from
	 * @param weight	For gui updates
	 * @param volume	For gui updates
	 * @param status	For gui updates
	 * @param items		For gui updates
	 * @param lst		For gui updates
	 * @param checked	For gui updates
	 */
	public Truck(Storage storage, JLabel weight, JLabel volume, JLabel status, JLabel items, JTextArea lst,
			boolean checked) {
		this.storage = storage;
		this.weight = weight;
		this.volume = volume;
		this.items = items;
		this.status = status;
		this.lst = lst;
		this.checked = checked;
		generateBoundries();
	}
	/**
	 * Generates random boundaries/capacity for the truck.
	 */
	private void generateBoundries() {
		this.maxItems = r.nextInt(5) + 5; // Fixxa gränser!
		this.maxWeight = r.nextInt(5) + 5;
		this.maxVolume = r.nextInt(5) + 5;
	}
	/**
	 * Rund-method for the thread
	 * Sleeps 1 seconds and attempts to get foodItem from storage
	 * Checks if full and if it should continue after sleep if thats the case
	 */
	public void run() {
		status.setText("Loading");
		while (running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FoodItem food = storage.get();
			System.out.println(Thread.currentThread().toString() + " Konsumerar");

			lst.append(food.getName() + "\n");
			String.format("%.2f", curVolume += food.getVolume());
			weight.setText(String.format("%.2f", curWeight += food.getWeight()));
			volume.setText(String.format("%.2f", curVolume += food.getVolume()));
			items.setText(String.valueOf(++curItems));

			if (curWeight >= maxWeight || curVolume >= maxVolume || curItems >= maxItems) { // check if full
				status.setText("Full");
				if (checked) { //if it should continue
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					status.setText("Loading");
					weight.setText("0");
					volume.setText("0");
					items.setText("0");
					lst.removeAll();
					generateBoundries();
					curWeight = 0;
					curVolume = 0;
					curItems = 0;
				} else {
					status.setText("Stopped");
					break;
				}
			}
		}
	}
	/**
	 * for stopping thread
	 */
	public void setflag() {
		running = false;
	}
}

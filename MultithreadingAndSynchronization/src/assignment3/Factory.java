package assignment3;

import java.util.Random;

import javax.swing.JLabel;
/**
 * Represents a factory wich fills an instance of the Storage-class with instances of fooditems
 * @author Jens Andreassen
 *
 */
public class Factory extends Thread {
	private FoodItem[] foods;
	private JLabel lblStatus;
	private Storage storage;
	private Random r = new Random();
	private volatile boolean running = true;
	/**
	 * Constructor
	 * @param lblStatusS
	 * @param storage
	 */
	public Factory(JLabel lblStatusS, Storage storage) {
		this.lblStatus = lblStatusS;
		this.storage = storage;
		initFoodItems();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Run method for thread, sleeps 1 second and then tries to fill storage with
	 * one item and updates gui accordingly
	 */
	public void run() {
		lblStatus.setText("Status: Producing");
		while (running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lblStatus.setText("Status: Idle");
			storage.put(foods[r.nextInt(20)]);
			lblStatus.setText("Status: Producing");
			System.out.println(Thread.currentThread().toString() + " Producerar");
		}
		lblStatus.setText("Status: Stopped");
	}
	/**
	 * For stopping thread
	 */
	public void setflag() {
		running = false;
	}
	/**
	 * For test-FoodItems
	 */
	private void initFoodItems() {
		foods = new FoodItem[20];
		foods[0] = new FoodItem("Milk", 1.1, 0.5);
		foods[1] = new FoodItem("Cream", 0.6, 0.4);
		foods[2] = new FoodItem("Yoghurt", 0.7, 0.3);
		foods[3] = new FoodItem("Butter", 1.0, 1.0);
		foods[4] = new FoodItem("Flower", 1.8, 1.5);
		foods[5] = new FoodItem("Sugar", 0.3, 1.4);
		foods[6] = new FoodItem("Salt", 0.4, 1.2);
		foods[7] = new FoodItem("Almonds", 0.5, 0.8);
		foods[8] = new FoodItem("Bread", 0.6, 0.7);
		foods[9] = new FoodItem("Donuts", 0.9, 0.9);
		foods[10] = new FoodItem("Jam", 1.3, 0.3);
		foods[11] = new FoodItem("Ham", 1.4, 0.2);
		foods[12] = new FoodItem("Chicken", 1.5, 2.0);
		foods[13] = new FoodItem("Salad", 0.4, 0.3);
		foods[14] = new FoodItem("Orange", 0.5, 0.4);
		foods[15] = new FoodItem("Apple", 0.6, 0.5);
		foods[16] = new FoodItem("Pear", 0.7, 0.6);
		foods[17] = new FoodItem("Soda", 0.8, 0.7);
		foods[18] = new FoodItem("Beer", 2.0, 0.8);
		foods[19] = new FoodItem("Hotdogs", 0.1, 0.9);
	}
}

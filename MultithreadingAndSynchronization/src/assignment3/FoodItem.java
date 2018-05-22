package assignment3;
/**
 * Represents a fooditem for the factory and truck to handle.
 * @author Jens Andreassen
 *
 */
public class FoodItem {
	private String name;
	private double weight;
	private double volume;
	/**
	 * Constructor
	 * @param name
	 * @param d weight
	 * @param e	volume
	 */
	public FoodItem(String name, double d, double e) {
		this.name = name;
		this.weight = d;
		this.volume = e;
	}
	/**
	 * Getter
	 * @return Name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter
	 * @return	Volume
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * Getter
	 * @return Weight
	 */
	public double getWeight() {
		return weight;
	}

}

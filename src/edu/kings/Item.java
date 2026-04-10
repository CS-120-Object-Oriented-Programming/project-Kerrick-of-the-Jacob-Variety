package edu.kings;

public class Item {
	/** Name of the item. */
	private String name;
	/** Description of the item. */
	private String description;
	/** Points an item is worth. */
	private int points;
	/** Weight of the item. */
	private double weight;
	
	/**
	 * Create an Item described "description".
	 * "description" is something like "a shiny axe" or "a big string telescope".
	 *
	 * @param name  The item's name.
	 * @param description  The item's description.
	 * @param points  The item's points.
	 * @param weight  The item's weight.
	 */
	public Item (String name, String description, int points, double weight) {
		this.name = name;
		this.description = description;
		this.points = points;
		this.weight = weight;
	}

	/**
	 * Returns the name of this item.
	 *
	 * @return The name of this item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the weight of this item.
	 *
	 * @return The weight of this item.
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns a string description including all the details of an item.
	 * For example,
	 * Axe: A shiny axe used to cut down trees. (1lbs.)
	 *
	 * @return A string representing all the details of an item.
	*/
	public String toString() {
		return name + ": " + description + ". (" + weight + "lbs.";
	}
}

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
	/** Whether or not the player knows about the item. */
	private boolean isKnown;
	/** Whether or not the player can lift the item. */
	private boolean isTooHeavy;
	
	/**
	 * Create an Item described "description".
	 * "description" is something like "a shiny axe" or "a big string telescope".
	 *
	 * @param name  The item's name.
	 * @param description  The item's description.
	 * @param points  The item's points.
	 * @param weight  The item's weight.
	 * @param isKnown  Whether or not the player starts knowing about the item.
	 * @param isTooHeavy  Whether or not the player can lift the item.
	 */
	public Item (String name, String description, int points, double weight, boolean isKnown, boolean isTooHeavy) {
		this.name = name;
		this.description = description;
		this.points = points;
		this.weight = weight;
		this.isKnown = isKnown;
		this.isTooHeavy = isTooHeavy;
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
	 * Returns the points of this item.
	 *
	 * @return The points of this item.
	 */
	public int getPoints() {
		return points;
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
	 * Returns whether or not the player knows of this item.
	 *
	 * @return Whether or not the player knows of this item.
	 */
	public boolean getIsKnown() {
		return isKnown;
	}
	
	/**
	 * Returns whether or not the player can lift the item.
	 *
	 * @return Whether or not the player can lift the item.
	 */
	public boolean getIsTooHeavy() {
		return isTooHeavy;
	}
	
	/**
	 * Defines whether or not the player knows about the item.
	 * 
	 * @param known
	 */
	public void setIsKnown(boolean known) {
		isKnown = known;
	}
	
	/**
	 * Returns a string description including all the details of an item.
	 * For example,
	 * Axe: A shiny axe used to cut down trees. (1lbs.)
	 *
	 * @return A string representing all the details of an item.
	*/
	public String toString() {
		return name + ": " + description + " (" + weight + "lbs.)";
	}
}

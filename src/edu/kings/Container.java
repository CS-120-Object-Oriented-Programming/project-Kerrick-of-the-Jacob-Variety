package edu.kings;

import java.util.HashMap;

public class Container extends Item {
	/** A hashmap for containing items. */
	private HashMap<String, Item> inventory = new HashMap<>();
	
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
	public Container (String name, String description, int points, double weight, boolean isKnown, boolean isTooHeavy) {
		super(name, description, points, weight, isKnown, isTooHeavy);
	}
	
	/**
	 * Returns the weight of this item and its contents.
	 *
	 * @return The weight of this item and its contents.
	 */
	public double getWeight() {
		double tempVal = super.getWeight();
		for (Item item : inventory.values()) {
			tempVal += item.getWeight();
		}
		return tempVal;
	}
	
	// Accessor function for getting specific items
	public Item getItem(String name) {
		return inventory.get(name);
	}
	
	// Returns the inventory in a printable way
	public String getInv() {
		String retVal = "";
		if (inventory.isEmpty()) {
			retVal += "Sorry, " + super.getName() + " doesn't have anything in it right now.";
		} else {
			retVal += "Taking an inventory of " + super.getName() + " you find it has:";
			for (Item item : inventory.values()) {
				retVal += "\n - " + item.getName();
			}
		}
		return retVal + "\n";
	}
	
	// A function that adds Items to inventory
	public void addItem(Item newItem) {
		inventory.put(newItem.getName().toLowerCase(), newItem);
	}
	
	// A function that removes an Item from the inventory
	public Item removeItem(String name) {
		return inventory.remove(name);
	}
	
	/** 
	 * Returns whether or not an item exists in items.
	 * 
	 * @param item  The item to be checked.
	 * @return A true or false depending on if the item exists
	 */
	public boolean containsItem(String item) {
		boolean tempval = false;
		if (inventory.containsKey(item)) {
			tempval = true;
		} else {
			tempval = false;
		}
		return tempval;
	}
	
	/**
	 * Returns a string description including all the details of an item.
	 * For example,
	 * Axe: A shiny axe used to cut down trees. (1lbs.)
	 *
	 * @return A string representing all the details of an item.
	*/
	public String toString() {
		String tempVal = super.toString() + "\n";
		tempVal += getInv();
		return tempVal;
	}
}

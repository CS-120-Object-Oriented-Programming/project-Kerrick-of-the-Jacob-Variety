package edu.kings;
import java.util.HashMap;
/**
 * @author Kerrick-of-the-Jacob-Variety
 */


/**
 * Does Player-y things ???
 */
public class Player {

	/** Current room of the player object. */
	private static Room currentRoom;
	
	/** Previous room of the player object. */
	private static Room previousRoom;
	
	/** Player's maximum carry weight. */
	private static double carryWeight = 5;
	
	/** This is a HashMap of items in the players inventory. */
	private HashMap<String, Item> inventory = new HashMap<>();
	
	// Sets a starting room for the player object
	public Player(Room startingRoom) {
		currentRoom = startingRoom;
		previousRoom = startingRoom;
	}
	
	// Accessor function for player object
	public Room getRoom() {
		return currentRoom;
	}
	
	// Accessor function for the previous room
	public Room getPreviousRoom() {
		return previousRoom;
	}
	
	// Accessor function for getting specific items
	public Item getItem(String name) {
		return inventory.get(name);
	}
	
	// Returns the inventory in a printable way
	public String getInv() {
		String retVal = "";
		if (inventory.isEmpty()) {
			retVal += "Sorry, you don't have anything right now";
		} else {
			retVal += "Taking an inventory you find you have:";
			for (Item item : inventory.values()) {
				retVal += "\n - " + item.getName();
			}
		}
		return retVal + "\n";
	}
	
	// Mutator function for player object
	public void setRoom(Room newRoom) {
		previousRoom = currentRoom;
		currentRoom = newRoom;
	}
	
	// A function that adds Items to inventory
	public boolean addItem(Item newItem) {
		boolean isSpace = false;
		double currentWeight = 0;
		for (Item item : inventory.values()) {
			currentWeight += item.getWeight();
		}
		if (currentWeight + newItem.getWeight() > carryWeight) {
			isSpace = false;
		} else {
			isSpace = true;
			inventory.put(newItem.getName().toLowerCase(), newItem);
		}
		return isSpace;
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
	
}

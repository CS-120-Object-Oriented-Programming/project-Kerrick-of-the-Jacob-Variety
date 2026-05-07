package edu.kings;
import java.util.HashMap;
import java.util.ArrayList;
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
	
	/** This is a HashMap of NPC's following the player. */
	private HashMap<String, Item> followers = new HashMap<>();
	
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
	public void addItem(Item newItem) {
		if (!isOverWeight(newItem) && !(newItem instanceof NPC)) {
			inventory.put(newItem.getName().toLowerCase(), newItem);
		} else if (newItem instanceof NPC) {
			followers.put(newItem.getName().toLowerCase(), newItem);
		}
	}
	
	// A function that returns whether or not adding an item is too heavy
	public boolean isOverWeight(Item newItem) {
		boolean isSpace = true;
		double currentWeight = 0;
		for (Item item : inventory.values()) {
			currentWeight += item.getWeight();
		}
		if (currentWeight + newItem.getWeight() > carryWeight) {
			isSpace = true;
		} else {
			isSpace = false;
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
	
	/** 
	 * Returns all of the NPC's following the player.
	 * 
	 * @return all of the NPC's following the player.
	 */
	public String myFollowers() {
		String following = "Currently; ";
		if (followers.isEmpty()) {
			following += "nobody is following you.";
		} else if (followers.size() == 1) {
			following += followers.get(followers.keySet().iterator().next()).getName() + " is following you";
		} else {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			ArrayList<Item> theFollowing = new ArrayList(followers.values());
			following += theFollowing.get(0);
			for (int ii = 1; ii < theFollowing.size(); ii++) {
				following += " & " + theFollowing.get(ii);
			}
			following += " are following you.";
		}
		return following;
	}
}

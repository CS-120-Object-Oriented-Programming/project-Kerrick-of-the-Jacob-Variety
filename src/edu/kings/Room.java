package edu.kings;
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via doors. The doors are labeled north, east, south, west.
 * For each direction, the room stores a reference to an instance of door.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Room {
	/** Counter for the total number of rooms created in the world. */
	private static int counter = 0;
	/** The name of this room.  Room names should be unique. */
	private String name;
	/** The description of this room. */
	private String description;

	/** This is a HashMap of exits for the room object. */
	private HashMap<String, Door> doorways = new HashMap<>();

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 *
	 * @param name  The room's name.
	 * @param description
	 *            The room's description.
	 */
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		counter++;
	}

	/**
	 * Returns the name of this room.
	 *
	 * @return The name of this room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of this room.
	 *
	 * @return The description of this room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the number of rooms that have been created in the world.
	 * @return The number of rooms that have been created in the world.
	 */
	public static int getCounter() {
		return counter;
	}
	
	/**
	* Gets a door in a specified direction if it exists.
	*
	* @param direction  The direction of the exit.
	* @return The door in the specified direction or null if it does not exist.
	*/
	public Door getExit(String direction) {
		return doorways.get(direction);
	}

	/**
	* Defines an exit from this room.
	*
	* @param direction The direction of the exit.
	* @param neighbor The door in the given direction.
	*/
	public void setExit(String direction, Door neighbor) {
		doorways.put(direction, neighbor);
	}
	
	/** 
	 * Returns whether or not an exit exists in dooways.
	 * 
	 * @param direction  The direction to be checked.
	 * @return A true or false depending on if the exit exists
	 */
	public boolean contains(String direction) {
		boolean tempval = false;
		if (doorways.containsKey(direction)) {
			tempval = true;
		} else {
			tempval = false;
		}
		return tempval;
	}
	
	/**
	 * Returns a string description including all the details of a Room.
	 * For example,
	 * Outside:
	 * You are outside in the center of the King's College campus.
	 * Exits: north east south west
	 *
	 * @return A string representing all the details of a Room.
	*/
	public String toString() {
		String returnStr =  "\n " + name + ":" + "\nYou are " + getDescription() + "\nExits: ";
		returnStr += doorways.keySet();
		return returnStr + "\n";
	}
}

package edu.kings;
/**
 * Class Door - a door or portal between two Rooms in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Door" represents a door or portal between two locations of the game.
 * It stores a reference to the neighboring room and whether that door
 * or portal is locked.  Doors are not locked by default.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Door {

	/** The room that this door leads to. */
	private Room destination;
	/** Whether a key for this door if it is locked. */
	private Item key = null;

	/**
	 * Constructor for the Door class.
	 * @param destination The room this door leads to
	 */
	public Door(Room destination) {
		this.destination = destination;
	}

	/**
	 * A getter for the room this door leads to.
	 * @return The room this door leads to
	 */
	public Room getDestination() {
		return destination;
	}

	/**
	 * A getter for whether this door is locked.
	 * @return Whether this door is locked
	 */
	public boolean isLocked() {
		return !(key == null);
	}

	/**
	 * A method to unlock this door if it is locked.
	 * @param key is used to unlock this door if it is locked.
	 */
	public void useKey(Item key) {
		if (this.key == key) {
			this.key = null;
		} else {
			Writer.println("This is not the right key");
		}
	}
	
	/**
	 * A setter to assign a key to a door.
	 * @param key is used to be assigned to a door.
	 */
	public void setKey(Item key) {
		this.key = key;
	}
}

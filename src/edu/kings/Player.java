package edu.kings;
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
	
	// Mutator function for player object
	public void setRoom(Room newRoom) {
		previousRoom = currentRoom;
		currentRoom = newRoom;
	}
	
}

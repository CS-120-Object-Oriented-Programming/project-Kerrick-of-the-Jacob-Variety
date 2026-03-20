/**
 * Author: Kerrick-of-the-Jacob-Variety
 */
package edu.kings;

/**
 * Does Player-y things ???
 */
public class Player {

	// Current room of the player object
	private static Room currentRoom;
	
	// Sets a starting room for the player object
	public Player(Room startingRoom) {
		currentRoom = startingRoom;
	}
	
	// Accessor function for player object
	public Room getRoom() {
		return currentRoom;
	}
	
	// Mutator function for player object
	public void setRoom(Room newRoom) {
		currentRoom = newRoom;
	}
	
}

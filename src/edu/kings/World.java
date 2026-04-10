package edu.kings;
import java.util.HashMap;

/**
 * This class represents the entire world that makes up the "Campus of Kings"
 * application. "Campus of Kings" is a very simple, text based adventure game.
 * Users can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * This world class creates the world where the game takes place.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class World {
	/** The rooms in the world. */
	private HashMap<String, Room> rooms;

	/**
	 * Constructor for the world.
	 */
	public World() {
		rooms = new HashMap<String, Room>();
		createRooms();
	}

	/**
	 * This method takes care of creating all of the aspects of the world for
	 * the "Campus of Kings" application.
	 *
	 * @param name
	 *            The provided name of the room.
	 * @return The room associated with the provided name
	 */
	public Room getRoom(String name) {
		return rooms.get(name.toLowerCase());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Start of private helper methods

	/**
	 * Helper method for recreating a Room. Ensure that the room is created and
	 * installed in to the collection of Rooms
	 *
	 * @param theRoom
	 *            The room to add to the world.
	 */
	private void addRoom(Room theRoom) {
		rooms.put(theRoom.getName().toLowerCase(), theRoom);
	}
	
	/**
	 * Helper method for creating an item.
	 * 
	 * @param theRoom  The Room the Item is being added to.
	 * @param item  The Item being added to the Room.
	 */
	private void createItems(Room theRoom, Item item) {
		theRoom.addItem(item);
	}

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from
	 *            The room where the door originates.
	 * @param to
	 *            The room to the north of the originating room.
	 * @param direction
	 * 			  The name of the door.
	 */
	private void createDoor(Room from, Room to, String direction) {
		Door newDoor = new Door(to);
		from.setExit(direction, newDoor);
	}

	/**
	 * This method creates all of the individual places in this world and all
	 * the doors connecting them.
	 */
	private void createRooms() {
		// Creating all the rooms.
		Room outside = new Room("Outside", "outside in the center of the King's College campus.");
		Room holyCross = new Room("Holy Cross", "at one of two main dormitories on campus.");
		Room essef = new Room("Essef", "at the other main dormitory on campus.");
		Room campusCenter = new Room("Campus Center", "in the center of student activities on campus.");
		Room admin = new Room("Admin", "in the oldest building on campus and home to the computer science department.");
		Room slivaOffice = new Room("Sliva's Office", "in Dr Sliva's office.");
		Room janoskiOffice = new Room("Janoski's Office", "in Dr Janoski's office.");
		Room lab = new Room("Computer Lab", "in the Computer Science and Math computing lab.");
		Room classroom = new Room("Classroom", "in the classroom where the computer science classes are taught.");

		// Adding all the rooms to the world.
		this.addRoom(outside);
		this.addRoom(holyCross);
		this.addRoom(essef);
		this.addRoom(campusCenter);
		this.addRoom(admin);
		this.addRoom(slivaOffice);
		this.addRoom(janoskiOffice);
		this.addRoom(lab);
		this.addRoom(classroom);

		// Creating all the doors between the rooms.
		this.createDoor(essef, outside, "south");
		this.createDoor(outside, essef, "north");

		this.createDoor(campusCenter, outside, "east");
		this.createDoor(outside, campusCenter, "west");

		this.createDoor(outside, holyCross, "east");
		this.createDoor(holyCross, outside, "west");

		this.createDoor(outside, admin, "south");
		this.createDoor(admin, outside, "north");

		this.createDoor(admin, lab, "east");
		this.createDoor(lab, admin, "west");

		this.createDoor(admin, janoskiOffice, "south");
		this.createDoor(janoskiOffice, admin, "north");

		this.createDoor(admin, slivaOffice, "west");
		this.createDoor(slivaOffice, admin, "east");

		this.createDoor(lab, classroom, "south");
		this.createDoor(classroom, lab, "north");
		
		// Creating all the items in the rooms.
		this.createItems(outside, new Item("Spikeball", "A spikeball used for a popular game in King's Court. This one must've rolled away.", 0, 0.1, true));
		this.createItems(campusCenter, new Item("TV Remote", "The remote for the TV's in the campus center. I guess they just left it here on its own.", 0, 0.1, true));
		this.createItems(outside, new Item("ID Card", "Your ID Card is here and ready for you. (I don't know where you get new ID's).", 0, 0.1, true));
		this.createItems(lab, new Item("Laptop", "Your laptop. You apparently forgot it here. Lucky you no one else found it.", 0, 1, true));
		this.createItems(classroom, new Item("Assignment", "Your assignment! Don't let Prof. Koscho find out you didn't care enough to at least throw it away.", 0, 0.1, true));
	}
}

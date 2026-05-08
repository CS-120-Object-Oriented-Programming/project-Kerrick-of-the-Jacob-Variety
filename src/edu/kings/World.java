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
	 * Helper method for creating an NPC.
	 * 
	 * @param theRoom  The Room the NPC is being added to.
	 * @param npc  The NPC being added to the Room.
	 */
	private void createNPCs(Room theRoom, NPC npc) {
		theRoom.addItem(npc);
	}
	
	/**
	 * Helper method for creating an item.
	 * 
	 * @param theRoom  The Room the Item is being added to.
	 * @param item  The Item being added to the Room.
	 * @param direction The direction of a locked door.
	 * @param lockedRoom The room this key is in.
	 */
	private void createItems(Room theRoom, Item item, Room lockedRoom, String direction) {
		theRoom.addItem(item);
		lockedRoom.getExit(direction).setKey(item);
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
		Room start = new Room("Start", "at the place where it all began, some might even say the origin point.");
		Room shore = new Room("Shore", "at a shore, it is really quiet and peaceful and has lots of space.");
		Room lookout = new Room("Lookout", "on top of a tall hill and can see the whole island from here. This might be a good place to stargaze");
		Room lumber = new Room("Lumber", "in a lumber yard. There is plenty of cut-down trees waiting to be chopped up.");
		Room tools = new Room("Tools", "in a place that seems to once have held tons of survival tools.");
		Room tailors = new Room("Tailor's", "somewhere that looks like a well known tailor would hide.");
		Room bush = new Room("Bush", "in a dense bush but you can't see if anything is past this.");
		Room secret = new Room("Secret", "in a secret location only known by Dave, until now have fun with winning every hide and seek from now on.");
		Room knottedPlace = new Room("Knotted Place", "somewhere in the middle of the woods with knots tied everywhere. A boy scout may be nearby.");
		Room ropes = new Room("Ropes", "in an area where plenty of ropes are ... IF THEY WERE STILL HERE!!");
		Room tightrope = new Room("Tightrope", "looking at a precarious tightrope and you don't think it will hold up too much weight.");
		Room cliff = new Room("Cliff", "on a precarious cliff, it looks like you can mount a mirror here for some reason.");
		Room daves = new Room("Dave's House", "staring at a rickety old house and it looks abandoned until someone greets you.");
		Room chute = new Room("Chute", "in a suddenly darker part of the forest, which makes no sense, until you look up and see a parachute dangling in the canopy.");
		Room jackie = new Room("Jackie", "in a place with a lot of poor attempts at making an axe and a lot of failed tree cuts, there may be a lumberjack nearby.");
		Room bridge = new Room("Bridge", "about to cross an old stone bridge, the ones that have a troll in the movies.");
		Room helicopter = new Room ("Helicopter", "next to a helicopter, you might be able to leave on it.");
		
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
		this.addRoom(start);
		this.addRoom(shore);
		this.addRoom(lookout);
		this.addRoom(lumber);
		this.addRoom(tools);
		this.addRoom(tailors);
		this.addRoom(bush);
		this.addRoom(secret);
		this.addRoom(knottedPlace);
		this.addRoom(ropes);
		this.addRoom(tightrope);
		this.addRoom(cliff);
		this.addRoom(daves);
		this.addRoom(chute);
		this.addRoom(jackie);
		this.addRoom(bridge);
		this.addRoom(helicopter);
		
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
		this.createDoor(shore, start, "north");
		this.createDoor(start, shore, "south");

		this.createDoor(start, lookout, "north");
		this.createDoor(lookout, start, "south");

		this.createDoor(lookout, lumber, "east");
		this.createDoor(lumber, lookout, "west");

		this.createDoor(lookout, tools, "north");
		this.createDoor(tools, lookout, "south");

		this.createDoor(tools, knottedPlace, "west");
		this.createDoor(knottedPlace, tools, "east");

		this.createDoor(tools, tailors, "north");
		this.createDoor(tailors, tools, "south");

		this.createDoor(tools, chute, "east");
		this.createDoor(chute, tools, "west");

		this.createDoor(knottedPlace, ropes, "south");
		this.createDoor(ropes, knottedPlace, "north");

		this.createDoor(knottedPlace, tightrope, "west");
		this.createDoor(tightrope, knottedPlace, "east");

		this.createDoor(tightrope, cliff, "north");
		this.createDoor(cliff, tightrope, "south");

		this.createDoor(cliff, daves, "west");
		this.createDoor(daves, cliff, "east");

		this.createDoor(tailors, bush, "east");
		this.createDoor(bush, tailors, "west");

		this.createDoor(chute, jackie, "east");
		this.createDoor(jackie, chute, "west");

		this.createDoor(jackie, bridge, "east");
		this.createDoor(bridge, jackie, "west");

		this.createDoor(bridge, helicopter, "east");
		this.createDoor(helicopter, bridge, "west");
		
		
		
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
		Item basketball = new Item("Basketball", "A strange basketball with eyes and a mouth drawn on it and, what's that? Oh really? Huh, this basketball has plenty of stories.", 5, 1, true,  false);
		Item axe = new Item("Axe", "A plain old fireman's axe just being around.", 10, 5, true,  false);
		Item baseball = new Item("Baseball", "A cute little baseball with a face drawn on and big lushious lashes, if it starts chanting in Latin, it's never too late to find a new friend.", 1, 0.1, true,  false);
		Item rope = new Item("Rope", "Just an ordinary rope used for tying things. Please don't fray it too bad.", 3, 0.5, true,  false);
		Item football = new Item("Football", "This is a unique looking football, with strong eyes and surprisingly no mouth for how much backtalk you think you hear. Always is fun to punt things.", 3, 0.5, true,  false);
		Item scissors = new Item("Scissors", "A pair of sharp scissors that can be used for a lot of damage, or cutting fabric is fine.", 1, 0.1, true,  false);
		
		this.createItems(lookout, basketball);
		this.createItems(tools, axe);
		this.createItems(cliff, baseball);
		this.createItems(ropes, rope);
		this.createItems(chute, football);
		this.createItems(knottedPlace, scissors);

		HashMap<String, Item> davesItems = new HashMap<>();
		davesItems.put(basketball.getName().toLowerCase(), basketball);
		davesItems.put(football.getName().toLowerCase(), football);
		davesItems.put(baseball.getName().toLowerCase(), baseball);
		
		HashMap<String, Item> tylersItems = new HashMap<>();
		davesItems.put(rope.getName().toLowerCase(), rope);
		
		HashMap<String, Item> tailorsItems = new HashMap<>();
		davesItems.put(scissors.getName().toLowerCase(), scissors);
		
		HashMap<String, Item> jacksItems = new HashMap<>();
		davesItems.put(axe.getName().toLowerCase(), axe);
		
		this.createNPCs(daves, new NPC("Dave", "A crazy person who has probably been here for at least a decade and keeps saying something about missing friends.", 100, 100, true,  true, davesItems, null, null));
		this.createNPCs(knottedPlace, new NPC("Tyler", "A former Boy Scout who probably knows how to tie at least one knot.", 20, 100, true,  true, tylersItems, null, null));
		this.createNPCs(tailors, new NPC("Taylor", "A professional tailor who found himself lost on this island just like you.", 50, 100, true,  true, tailorsItems, null, null));
		this.createNPCs(jackie, new NPC("Jack", "A buff lumberjack in the stereotypical attire really looking to let out his anger on some logs.", 10, 100, true,  true, jacksItems, null, null));
		
		
		this.createItems(outside, new Item("Spikeball", "A spikeball used for a popular game in King's Court. This one must've rolled away.", 0, 4, true, false));
		this.createItems(campusCenter, new Item("TV Remote", "The remote for the TV's in the campus center. I guess they just left it here on its own.", 0, 0.1, true, false));
		this.createItems(admin, new Item("ID Card", "Your ID Card is here and ready for you. (I don't know where you get new ID's).", 0, 0.1, true, false));
		this.createItems(lab, new Item("Laptop", "Your laptop. You apparently forgot it here. Lucky you no one else found it.", 0, 1, true, false));
		this.createItems(classroom, new Item("Assignment", "Your assignment! Don't let Prof. Koscho find out you didn't care enough to at least throw it away.", 0, 0.1, true, false));
		this.createItems(outside, new Item("Essef Key", "The key you need to get into essef.", 0, 0.1, true, false),outside, "north");
		this.createItems(outside, new Container("Backpack", "A backpack you can put stuff in.", 0, 1, true, false));
	}
}

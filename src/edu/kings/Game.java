package edu.kings;
/**
 * This class is the main class of the "Campus of Kings" application.
 * "Campus of Kings" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * This game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Maria Jump
 * @author Jacob Kerrick
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

public class Game {
	/** The world where the game takes place. */
	private World world;
	/** The player character itself. */
	private Player currentPlayer;
	/** The Score incrementer. */
	private int score;
	/** The Turn incrementer. */
	private int turns;

	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		// set the starting room
		currentPlayer = new Player(world.getRoom("start"));
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main game loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean wantToQuit = false;
		while (!wantToQuit) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			// other stuff that needs to happen every turn can be added here.
		}
		printStatus();
		printGoodbye();
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for processing the commands

	/**
	 * Given a command, process (that is: execute) the command.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			Writer.println("I don't know what you mean...");
		} else {

			CommandEnum commandWord = command.getCommandWord();
			switch (commandWord) {
			case CommandEnum.HELP:
				printHelp();
				break;
			case CommandEnum.GO:
				goRoom(command);
				break;
			case CommandEnum.QUIT:
				wantToQuit = quit(command);
				break;
			case CommandEnum.LOOK:
				look();
				break;
			case CommandEnum.STATUS:
				printStatus();
				break;
			case CommandEnum.BACK:
				back();
				break;
			case CommandEnum.EXAMINE:
				examine(command);
				break;
			case CommandEnum.TAKE:
				take(command);
				break;
			case CommandEnum.DROP:
				drop(command);
				break;
			case CommandEnum.INVENTORY:
				printInv();
				break;
			case CommandEnum.UNLOCK:
				unlock(command);
				break;
			case CommandEnum.LOCK:
				lock(command);
				break;
			case CommandEnum.UNPACK:
				unpack(command);
				break;
			case CommandEnum.PACK:
				pack(command);
				break;
			case CommandEnum.FOLLOWERS:
				Writer.println(currentPlayer.myFollowers());
				break;
			case CommandEnum.GIVE:
				give(command);
				break;
			/*case CommandEnum.QUEST:
				completeQuest(command);
				break;*/
			case CommandEnum.COME:
				come(command);
				break;
			case CommandEnum.ESCAPE:
				escape(command);
			default:
				Writer.println(commandWord + " is not implemented yet!");
			}
		}
		return wantToQuit;
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for implementing all of the commands.
	// It helps if you organize these in alphabetical order.

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			Writer.println("Go where?");
		} else {
			String direction = command.getRestOfLine();

			// Try to leave current.
			Door doorway = null;
			if (currentPlayer.getRoom().containsExit(direction) && !currentPlayer.getRoom().getExit(direction).isLocked()) {
				doorway = currentPlayer.getRoom().getExit(direction);
			}
			
			if (direction.equals("back")) {
				back();
			} else if (doorway == null && currentPlayer.getRoom().getExit(direction).isLocked()) {
				Writer.println("The door is locked!");
			} else if (doorway == null) {
				Writer.println("There is no door!");
			} else {
				Room newRoom = doorway.getDestination();
				currentPlayer.setRoom(newRoom);
				turns++;
				printLocationInformation();
			}
		}
	}

	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		Writer.println("I hope you weren't too bored here on the Campus of Kings!");
		Writer.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		Writer.println("You are lost. You are alone. You wander");
		Writer.println("around at the university.");
		Writer.println();
		Writer.println("Your command words are:");
		for (CommandEnum command : CommandEnum.values()) {
			Writer.print(command.getCommand() + " ");
		}
		Writer.println();
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Writer.println();
		Writer.println("Welcome to the Surviving the Island!");
		Writer.println("Surviving the Island is a new, incredibly potential filled adventure game.");
		Writer.println("Type 'help' if you need help.");
		Writer.println();
		printLocationInformation();
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		boolean wantToQuit = true;
		if (command.hasSecondWord()) {
			Writer.println("Quit what?");
			wantToQuit = false;
		}
		return wantToQuit;
	}
	
	/**
	 * Prints out the current location and exits.
	 */
	private void printLocationInformation() {
		Writer.print(currentPlayer.getRoom().toString());
	}
	
	/**
	 * Prints out the location information.
	 */
	private void look() {
		printLocationInformation();
	}
	
	/**
	 * Prints out the status of the player object.
	 */
	private void printStatus() {
		Writer.println("You have earned " + score + " points in " + turns + " turns.");
	}
	
	/**
	 * Returns the player to the previous room.
	 */
	private void back() {
		turns++;
		currentPlayer.setRoom(currentPlayer.getPreviousRoom());
		printLocationInformation();
	}
	
	/**
	 * Try to go to examine an item. If there is an item, print the description,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void examine(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Which item?");
		} else {
			String item = command.getRestOfLine();

			if (!(currentPlayer.getRoom().containsItem(item) || currentPlayer.containsItem(item))) {
				Writer.println("No such item.");
			} else {
				if (currentPlayer.getRoom().containsItem(item)) {
					Writer.println(currentPlayer.getRoom().getItem(item).toString());
				} else {
					Writer.println(currentPlayer.getItem(item).toString());
				}
			}
		}
	}
	
	/**
	 * Try to take an item. If there is an item, take the item,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void take(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Take what?");
		} else {
			String item = command.getRestOfLine();
			
			if (!currentPlayer.getRoom().containsItem(item)) {
				Writer.println("No such item.");
			} else if (currentPlayer.getRoom().getItem(item).getIsTooHeavy()) {
				Writer.println("Too heavy to lift.");
			} else if (currentPlayer.isOverWeight(currentPlayer.getRoom().getItem(item))) {
				Writer.println("Carrying too much.");
			} else {
				currentPlayer.addItem(currentPlayer.getRoom().getItem(item));
				currentPlayer.getRoom().removeItem(item);
				Writer.println("You took the item.");
			}
		}
	}
	
	/**
	 * Try to go to drop an item. If there is an item, drop the item,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void drop(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Which item?");
		} else {
			String item = command.getRestOfLine();
			
			if (!currentPlayer.containsItem(item)) {
				Writer.println("You don't have it.");
			} else {
				currentPlayer.getRoom().addItem(currentPlayer.getItem(item));
				currentPlayer.removeItem(item);
				Writer.println("You dropped the item.");
			}
		}
	}
	
	/**
	 * Prints every item in the player's inventory.
	 */
	private void printInv() {
		Writer.print(currentPlayer.getInv());
	}
	
	/**
	 * Unlocks a specific exit.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void unlock(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to unlock...
			Writer.println("Unlock what?");
		} else {
			String direction = command.getRestOfLine();
			if (currentPlayer.getRoom().containsExit(direction)) {
				Door exit = currentPlayer.getRoom().getExit(direction);
				if (exit.isLocked()) {
					if (currentPlayer.containsItem(exit.getKey().getName().toLowerCase())) {
						Writer.print("What key would you like to use?\n> ");
						String key = Reader.getResponse();
						if (key.equals(exit.getKey().getName().toLowerCase())) {
							exit.useKey(currentPlayer.getItem(key));
							Writer.println("You unlocked it!");
						} else {
							Writer.println("That doesn't fit.");
						}
					} else {
						Writer.println("You don't have the right key.");
					}
				} else {
					Writer.println("Door is not locked.");
				}
			} else {
				Writer.println("There is no door!");
			}
		}
	}
	
	/**
	 * Locks a specific exit.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void lock(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to lock...
			Writer.println("Lock what?");
		} else {
			String direction = command.getRestOfLine();
			if (currentPlayer.getRoom().containsExit(direction)) {
				Door exit = currentPlayer.getRoom().getExit(direction);
				if (!exit.isLocked()) {
					if (exit.hasKey()) {
						if (currentPlayer.containsItem(exit.getKey().getName().toLowerCase())) {
							Writer.print("What key would you like to use?\n> ");
							String key = Reader.getResponse();
							if (key.equals(exit.getKey().getName().toLowerCase())) {
								exit.lock();
								Writer.println("You locked it!");
							} else {
								Writer.println("That doesn't fit.");
							}
						} else {
							Writer.println("You do not have the key.");
						}
					} else {
						Writer.println("Door cannot be locked.");
					}
				} else {
					Writer.println("Door is already locked.");
				}
			} else {
				Writer.println("There is no door!");
			}
		}
	}
	
	/**
	 * Unpacks an Item from a container.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void unpack(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to unpack...
			Writer.println("Unpack what?");
		} else {
			String item = command.getRestOfLine();
			if (!(currentPlayer.containsItem(item) || currentPlayer.getRoom().containsItem(item))) {
				Writer.println("You don't see it.");
			} else {
				if ((currentPlayer.getItem(item) instanceof Container && !(currentPlayer.getItem(item) instanceof NPC)) || (currentPlayer.getRoom().getItem(item) instanceof Container && !(currentPlayer.getRoom().getItem(item) instanceof NPC))) {
					Container container = null;
					if (currentPlayer.getItem(item) instanceof Container) {
						container = (Container) currentPlayer.getItem(item);
					} else if (currentPlayer.getRoom().getItem(item) instanceof Container) {
						container = (Container) currentPlayer.getRoom().getItem(item);
					}
					if (container.isEmpty()) {
						Writer.println("The container is empty.");
					} else {
						Writer.print("What item would you like to unpack?\n> ");
						String key = Reader.getResponse();
						if (container.getItem(key) == null) {
							Writer.println("You don't find it.");
						} else {
							if (currentPlayer.isOverWeight(container.getItem(key)) && !currentPlayer.containsItem(item)) {
								Writer.println("You are already carrying too much!");
							} else {
								currentPlayer.addItem(container.removeItem(key));
								Writer.println("You unpack it.");
							}
						}
					}
				} else {
					Writer.println("That's not a container!");
				}
			}
		}
	}
	
	/**
	 * Packs an Item from a container.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void pack(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to unpack...
			Writer.println("Pack what?");
		} else {
			String item = command.getRestOfLine();
			if (!(currentPlayer.containsItem(item) || currentPlayer.getRoom().containsItem(item))) {
				Writer.println("You don't have it.");
			} else {
				Item packingItem = null;
				if (currentPlayer.containsItem(item)) {
					packingItem = currentPlayer.getItem(item);
				} else if (currentPlayer.getRoom().containsItem(item)) {
					packingItem = currentPlayer.getRoom().getItem(item);
				}
				if (packingItem.getIsTooHeavy()) {
					Writer.println("Too heavy.");
				} else {
					Writer.print("What container would you like to put it in?\n> ");
					String key = Reader.getResponse();
					if (!(currentPlayer.containsItem(key) || currentPlayer.getRoom().containsItem(key))) {
						Writer.println("You don't see the container.");
					} else {
						if ((currentPlayer.getItem(key) instanceof Container && !(currentPlayer.getItem(key) instanceof NPC)) || (currentPlayer.getRoom().getItem(key) instanceof Container && !(currentPlayer.getRoom().getItem(key) instanceof NPC))) {
							Container container = null;
							if (currentPlayer.getItem(key) instanceof Container) {
								container = (Container) currentPlayer.getItem(key);
							} else if (currentPlayer.getRoom().getItem(key) instanceof Container) {
								container = (Container) currentPlayer.getRoom().getItem(key);
							}
							if (!currentPlayer.containsItem(item) && currentPlayer.isOverWeight(packingItem) && currentPlayer.containsItem(key)) {
								Writer.println("Carrying too much.");
							} else {
								if (currentPlayer.containsItem(item)) {
									container.addItem(currentPlayer.removeItem(item));
								} else if (currentPlayer.getRoom().containsItem(item)) {
									container.addItem(currentPlayer.getRoom().removeItem(item));
								}
								Writer.println("You packed it!");
							}
						}
						else {
							Writer.println("That's not a container!");
						}
					}
				}
			}
		}
	}
	
	/**
	 * Tries to give an item to an NPC.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void give(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Give what?");
		} else {
			String item = command.getRestOfLine();
			if (currentPlayer.containsItem(item)) {
				Item gift = currentPlayer.getItem(item);
				Writer.print("Which NPC would you like to give it too?\n> ");
				String npc = Reader.getResponse();
				if (currentPlayer.containsFollower(npc)) {
					if (currentPlayer.getFollower(npc).canAddItem(gift)) {
						currentPlayer.getFollower(npc).addItem(currentPlayer.removeItem(item));
					}
				} else if (currentPlayer.getRoom().containsItem(npc) && currentPlayer.getRoom().getItem(npc) instanceof NPC) {
					if (((NPC)currentPlayer.getRoom().getItem(npc)).canAddItem(gift)) {
						score += gift.getPoints();
						((NPC)currentPlayer.getRoom().getItem(npc)).addItem(currentPlayer.removeItem(item));
					}
				} else {
					Writer.println("They are no where to be seen");
				}
			} else {
				Writer.println("You don't have that item.");
			}
		}
	}
	
	/**
	 * Tries to complete an NPC quest.
	 *
	 * @param command
	 *            The command to be processed.
	 *\/
	private void completeQuest(Command command) {
		
	}*/
	
	/**
	 * Makes an NPC follow you.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void come(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Who would you like to follow you?");
		} else {
			String npc = command.getRestOfLine();
			if (!currentPlayer.getRoom().containsItem(npc)) {
				Writer.println("They aren't here.");
			} else if (currentPlayer.getRoom().getItem(npc) instanceof NPC) {
				currentPlayer.addItem(currentPlayer.getRoom().getItem(npc));
				currentPlayer.getRoom().removeItem(npc);
				Writer.println(currentPlayer.getFollower(npc) + " is now following you.");
			}
		}
	}
	
	/**
	 * Tries to escape the island.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void escape(Command command) {
		if (currentPlayer.getRoom().equals(new Room ("Helicopter", "next to a helicopter, you might be able to leave on it."))) {
			if (currentPlayer.getFollowerPoints() == 0) {
				Writer.println("You need to escape with at least one follower");
			} else {
				score += currentPlayer.getFollowerPoints();
				Writer.println("Congradulations! You have escaped with some others!");
				currentPlayer.myFollowers();
				printStatus();
				processCommand(new Command(CommandEnum.QUIT));
			}
		}
	}
}

package edu.kings;

import java.util.HashMap;

public class NPC extends Container {
	/** A HashMap for items the NPC is specifically looking for. */
	private HashMap<String, Item> questItems = new HashMap<>();
	
	/** a Room to store the special Room for the NPC. */
	private Room questRoom = null;
	
	/** An Item that will be given if NPC task is completed successfully. */
	private Item questItem = null;
	
	/** A boolean used to determine if the quest has been completed. */
	private boolean isQuestCompleted = false;
	
	/**
	 * Create an NPC described "description".
	 * "description" is something like "a shiny axe" or "a big string telescope".
	 *
	 * @param name  The NPC's name.
	 * @param description  The NPC's description.
	 * @param points  The NPC's points.
	 * @param weight  The NPC's weight.
	 * @param isKnown  Whether or not the player starts knowing about the NPC.
	 * @param isTooHeavy  Whether or not the player can lift the NPC.
	 * @param questItems  The items the NPC is looking for.
	 * @param questRoom  The Room the NPC needs for their task.
	 * @param questItem  The item the NPC rewards you with when their quest is completed.
	 */
	public NPC(String name, String description, int points, double weight, boolean isKnown, boolean isTooHeavy, HashMap<String, Item> questItems, Room questRoom, Item questItem) {
		super(name, description, points, weight, isKnown, isTooHeavy);
		this.questItems = questItems;
		this.questRoom = questRoom;
		this.questItem = questItem;
	}

	/*
	 * Overriding the Container's addItem so that the NPC only accepts the ones they're looking for.
	 * 
	 * @param newItem  The item potentially being added to the NPC's inventory.
	 */
	@Override
	public void addItem(Item newItem) {
		if (canAddItem(newItem)) {
			super.inventory.put(newItem.getName().toLowerCase(), newItem);
		}
	}
	
	/*
	 * Checks to see if the npc can take an item.
	 * 
	 * @param newItem  The item trying to be given to the npc.
	 */
	public boolean canAddItem(Item newItem) {
		boolean tempVal = false;
		if (questItems.containsKey(newItem.getName().toLowerCase())) {
			tempVal = true;
		} else {
			Writer.println(super.getName() + ": I'm sorry but I don't want that.");
		}
		return tempVal;
	}
	
	/* 
	 * Returns the items that are missing from the NPC's questItems.
	 * 
	 * @return whether the NPC has everything they need or not.
	 */
	public boolean missingItems() {
		String printVal = super.getName() + ": I am missing: ";
		boolean retVal = true;
		for (Item checker : questItems.values()) {
			if (!(super.containsItem(checker.getName().toLowerCase()))) {
				printVal += checker.getName() + " ";
				retVal = true;
			}
		}
		if (printVal.equals(super.getName() + ": I am missing: ")) {
			printVal = super.getName() + ": I don't need anything else.";
			retVal = false;
		}
		Writer.println(printVal);
		return retVal;
	}
	
	/* 
	 * Returns whether or not the NPC is currently in their questRoom.
	 * 
	 * @return whether or not the NPC is currently in their questRoom.
	 */
	public boolean checkRoom(Room room) {
		boolean retVal = false;
		if (room == questRoom || questRoom == null) {
			retVal = true;
		} else {
			Writer.println(super.getName() + ": I'm not in the right room.");
		}
		return retVal;
	}
	
	/* 
	 * Returns the questItem if the quest is completed.
	 * 
	 * @return the questItem if the quest is completed.
	 */
	public Item completeQuest(Room room) {
		Item retItem = null;
		if (questCompletable(room) && !isQuestCompleted) {
			Writer.println(super.getName() + ": I have completed my quest. Here take this.");
			retItem = questItem;
			isQuestCompleted = true;
		}
		return retItem;
	}
	
	/*
	 * Returns whether the NPC can complete their quest.
	 * 
	 * @return whether the NPC can complete their quest.
	 */
	public boolean questCompletable(Room room) {
		boolean tempVal = false;
		if (missingItems() && checkRoom(room)) {
			tempVal = true;
		}
		return tempVal;
	}
}

package edu.kings;

public enum CommandEnum {
	/** 
	 * The Go Command. 
	 */
	GO("go"),
	/** The Quit Command. */
	QUIT("quit"),
	/** The Help Command. */
	HELP("help"),
	/** The Look Command. */
	LOOK("look"),
	/** The Status Command. */
	STATUS("status"),
	/** The Back Command. */
	BACK("back"),
	/** The Examine Command. */
	EXAMINE("examine"),
	/** The Take Command. */
	TAKE("take"),
	/** The Drop Command. */
	DROP("drop"),
	/** The Inventory Command. */
	INVENTORY("inventory"),
	/** The Unlock command. */
	UNLOCK("unlock"),
	/** The Lock command. */
	LOCK("lock"),
	/** The Unpack command. */
	UNPACK("unpack"),
	/** The Pack command. */
	PACK("pack"),
	/** The Followers command. */
	FOLLOWERS("followers"),
	/** The Give command. */
	GIVE("give"),
	/** The Quest command. */
	QUEST("quest"), 
	/** The Come command. */
	COME("come"), 
	/** The Escape command. */
	ESCAPE("escape");
	
	/** A string for all Commands. */
	private final String command;
	
	private CommandEnum(String newCommand) {
		command = newCommand;
	}
	
	public String getCommand() {
		return command;
	}
}

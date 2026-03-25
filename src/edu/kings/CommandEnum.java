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
	BACK("back");
	
	/** A string for all Commands. */
	private final String command;
	
	private CommandEnum(String newCommand) {
		command = newCommand;
	}
	
	public String getCommand() {
		return command;
	}
}

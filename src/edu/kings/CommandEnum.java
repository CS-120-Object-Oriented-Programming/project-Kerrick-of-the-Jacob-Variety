package edu.kings;

public enum CommandEnum {
GO("go"),
QUIT("quit"),
HELP("help"),
LOOK("look");

	private final String command;
	
	private CommandEnum(String newCommand) {
		command = newCommand;
	}
	
	public String getCommand() {
		return command;
	}
}

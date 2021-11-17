package br.com.santanafelipe.errors;

public class CommandNotFoundException extends Exception {
	public CommandNotFoundException(String commandName) {
		super("error: command not found \"" + commandName + "\"");
	}
}

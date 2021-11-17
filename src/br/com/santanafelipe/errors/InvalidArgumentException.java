package br.com.santanafelipe.errors;

public class InvalidArgumentException extends Exception {
	public InvalidArgumentException(String argument) {
		super("error: invalid argument \"" + argument + "\" - check --help for more info");
	}
}
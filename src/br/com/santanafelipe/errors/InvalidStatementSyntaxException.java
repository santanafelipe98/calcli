package br.com.santanafelipe.errors;

public class InvalidStatementSyntaxException extends Exception {
	public InvalidStatementSyntaxException(String statement) {
		super("error: invalid syntax \"" + statement + "\" - check \"--help\" for more info");
	}
}

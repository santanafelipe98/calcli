package br.com.santanafelipe.errors;

public class ExceededParamCountException extends Exception {
	public ExceededParamCountException(String commandName, int defaultParamCount, int actualParamCount) {
		super(commandName
				+ " takes exactly "
				+ defaultParamCount
				+ " param(s) ("
				+ actualParamCount + ") given");
	}
}

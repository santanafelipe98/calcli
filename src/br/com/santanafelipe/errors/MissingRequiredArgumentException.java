package br.com.santanafelipe.errors;

public class MissingRequiredArgumentException extends Exception {
	public MissingRequiredArgumentException() {
		super("error: floating number not allowed - use the \"-f\" flag.");
	}
}

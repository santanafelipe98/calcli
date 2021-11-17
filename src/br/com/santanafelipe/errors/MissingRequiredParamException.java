package br.com.santanafelipe.errors;

public class MissingRequiredParamException extends Exception {
	public MissingRequiredParamException(String params) {
		super("error: missing params \"" + params + "\" - check --help for more info");
	}
}

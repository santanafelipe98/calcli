package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;

public interface IInvoker {
	public void executeCommand()
			throws InvalidArgumentException,
			MissingRequiredParamException,
			MissingRequiredArgumentException,
			ExceededParamCountException;
}

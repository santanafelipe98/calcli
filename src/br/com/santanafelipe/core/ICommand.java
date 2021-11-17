package br.com.santanafelipe.core;


import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;

public interface ICommand {
	public void execute()
		throws InvalidArgumentException,
			MissingRequiredArgumentException,
			MissingRequiredParamException,
			ExceededParamCountException;
	public void help();
	public ICommand getSubcommand();
	public void setSubcommand(ICommand subcommand);
	public ICommand clone();
}
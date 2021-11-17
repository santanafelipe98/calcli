package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;

public abstract class Command implements ICommand {
	protected String[] flags;
	protected String[] params;
	protected ICommand subcommand;
	
	public Command() {
		
	}
	
	public Command(String[] flags, String[] params) {
		this.setFlags(flags);
		this.setParams(params);
	}
	
	public Command(String[] flags, String[] params, ICommand subcommand) {
		this.setFlags(flags);
		this.setParams(params);
		this.setSubcommand(subcommand);;
	}
	
	public abstract Command clone();
	
	public String[] getFlags() {
		return this.flags;
	}
	
	public void setFlags(String[] flags) {
		this.flags = flags;
	}
	
	public String[] getParams() {
		return this.params;
	}
	
	public void setParams(String[] params) {
		this.params = params;
	}
	
	public ICommand getSubcommand() {
		return this.subcommand;
	}
	
	public void setSubcommand(ICommand subcommand) {
		this.subcommand = subcommand;
	}
}
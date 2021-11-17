package br.com.santanafelipe.parser;

/**
 * Classe de configuração de comando.
 * @author Felipe Santana
 * @version 0.1
 */
public class CommandDTO {
	public String[] commands;
	public String[] flags;
	public String[] params;
	
	public CommandDTO(String[] commands, String[] flags, String[] params) {
		this.commands = commands;
		this.flags    = flags;
		this.params   = params; 
	}
}

package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;

/**
 * Classe para representação do menu geral de ajuda.
 * @author Felipe Santana
 * @version 0.1
 */
public class Helper extends Command {
	
	/**
	 * Método para execução do comando de ajuda
	 * @throws InvalidArgumentException         Se o argumento passado for inválido.
	 * @throws MissingRequiredArgumentException Se um argumento obrigatório não for passado.
	 * @throws MissingRequiredParamException    Se um parâmetro obrigatório não for passado.
	 * @throws ExceededParamCountException      Se a quantidade de parâmetros for maior que a quantidade padrão de parâmetros do comando.
	 * @see    InvalidArgumentException
	 * @see    MissingRequiredArgumentException
	 * @see    MissingRequiredParamException
	 * @see    ExceededParamCountException
	 */
	@Override
	public void execute()
			throws InvalidArgumentException,
			MissingRequiredArgumentException,
			MissingRequiredParamException,
			ExceededParamCountException {
		// Se existir algum subcomando
		
		if (this.getSubcommand() != null)
			this.getSubcommand().execute(); // Delega execução para o subcomando
		else
			this.help(); // Exibe menu de ajuda
	}
	
	/**
	 * Método para clonagem do objeto
	 * @return Helper - Clone do objeto
	 */
	@Override
	public Helper clone() {
		return new Helper();
	}

	/**
	 * Exibe menu de ajuda do comando.
	 */
	@Override
	public void help() {
		System.out.println("calcli [subcommand] [options]");
		
		System.out.println("\nDescription:");
		System.out.println("  Calculator CLI is command-line tool that allows you to do some math basic operations.");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
		
		System.out.println("\nCommand");
		System.out.println("  add          Add multiple numbers");
		System.out.println("  sub          Subtract multiple numbers");
		System.out.println("  mult         Multiplies multiple numbers");
		System.out.println("  div          Divide multiple numbers recursively ");
		System.out.println("  pow          Calculates power recursively");
		System.out.println("  sqrt         Calculates the square root of a given number");
	}

	/**
	 * Método para obtenção de subcomando.
	 */
	@Override
	public ICommand getSubcommand() {
		return this.subcommand;
	}

	/**
	 * Método para definição de subcomando
	 */
	@Override
	public void setSubcommand(ICommand subcommand) {
		this.subcommand = subcommand;
	}

}

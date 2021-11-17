package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.MissingRequiredArgumentException;

import java.util.Locale;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;
import br.com.santanafelipe.utils.NumberUtils;

/**
 * Classe para representação da operação de adição.
 * @author Felipe Santana
 * @version 0.1
 */
public class Add extends Command {
	public Add() {
		super();
	}
	
	public Add(String[] flags, String[] params) {
		super(flags, params);
	}
	
	public Add(String[] flags, String[] params, Add subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto
	 * @return Add - Clone do objeto
	 */
	@Override
	public Add clone() {
		return new Add(this.flags, this.params, (Add) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de adição.
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
		
		if (this.subcommand != null) {
			this.subcommand.execute(); // Delega a operação para o subcomando
		} else {
			boolean allowFloat = false;
			
			// Se houver flags
			
			if (this.flags != null) {
				for (int i = 0; i < this.flags.length; i++) {
					// Verifica se existem alguma flag de ajuda
					
					if (this.flags[i].equals("-h") || this.flags[i].equals("--help")) {
						this.help(); // Exibe menu de ajuda
						
						return;
					} else if (this.flags[i].equals("-f") || this.flags[i].equals("--float")) { // Flag de números decimais
						allowFloat = true;
						
						break;
					} else { // Se a flag for desconhecida
						throw new InvalidArgumentException(this.flags[i]); // Lança exceção de argumento inválido
					}
				}
			}
			
			// Se não houver parâmetros
			
			if (this.params == null || this.params.length == 0)
				throw new MissingRequiredParamException("<...numbers>"); // Lança exceção
			
			double[] numbers = new double[this.params.length];
			
			// Converte array de strings em double
			
			for (int j = 0; j < this.params.length; j++) {
				// Verifica se a flag de números decimais foi passada
				
				if (!allowFloat) {
					// Verifica se o parâmetro em questão é um número inteiro
					
					if (!NumberUtils.isInt(this.params[j])) {
						throw new MissingRequiredArgumentException(); // Lança exceção
					}
				}
				
				double number = Double.parseDouble(this.params[j]);
				numbers[j]    = number;
			}
			
			double sum = this.sum(numbers);
			System.out.println((allowFloat) ? sum : ((int) sum));
		}
	}
	
	/**
	 * Método para realização de soma de um vetor de números.
	 * @param numbers - O vetor de números.
	 * @return double - Soma dos números.
	 * */
	public double sum(double numbers[]) {
		double sum = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		
		return sum;
	}
	
	/**
	 * Método para exibição do menu de ajuda do comando.
	 */
	public void help() {
		System.out.println("Usage:\nadd [options] [<...numbers>]");
		
		System.out.println("\nArguments:");
		System.out.println("  numbers      List of numbers to add");
		
		System.out.println("\nAvailable commands:");
		System.out.println("  odd          Add only odd numbers");
		System.out.println("  even         Add only even numbers");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
		System.out.println("  -f, --float  Allow floating numbers");
	}
}

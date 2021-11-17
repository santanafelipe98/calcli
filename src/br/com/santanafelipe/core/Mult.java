package br.com.santanafelipe.core;

import java.util.Locale;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;
import br.com.santanafelipe.utils.NumberUtils;

/**
 * Classe para representação da operação de multiplicação
 * @author Felipe Santana
 * @version 0.1
 */
public class Mult extends Command {

	public Mult() {
		super();
	}
	
	public Mult(String[] flags, String[] params) {
		super(flags, params);
	}
	
	public Mult(String[] flags, String[] params, Mult subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto.
	 * @return Mult - Clone do objeto
	 */
	@Override
	public Mult clone() {
		return new Mult(this.flags, this.params, (Mult) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de multiplicação.
	 * @throws InvalidArgumentException         Se o argumento passado for inválido.
	 * @throws MissingRequiredParamException    Se um parâmetro obrigatório não for passado.
	 * @throws MissingRequiredArgumentException Se um argumento obrigatório não for passado.
	 * @throws ExceededParamCountException      Se a quantidade de parâmetros for maior que a quantidade padrão de parâmetros do comando.
	 * @see    InvalidArgumentException
	 * @see    MissingRequiredParamException
	 * @see    MissingRequiredArgumentException
	 * @see    ExceededParamCountException
	 */
	@Override
	public void execute()
			throws InvalidArgumentException,
			MissingRequiredParamException,
			MissingRequiredArgumentException,
			ExceededParamCountException {
		// Se existir algum subcomando
		
		if (this.subcommand != null) {
			this.subcommand.execute(); // Delega a operação para o subcomando
		} else {
			// Se existir alguma flag
			
			if (this.flags != null) {
				// Verifica se existe alguma flag de ajuda
				
				if (this.flags[0].equals("-h") || this.flags[0].equals("--help")) {
					this.help(); // Exibe menu de ajuda
					
					return;
				}
				
				throw new InvalidArgumentException(this.flags[0]); // Lança exceção de argumento inválido
			}
			
			// Se não existir paramêtros
			
			if (this.params == null || this.params.length == 0)
				throw new MissingRequiredParamException("<...numbers>"); // Lança exceção de parâmetro obrigatório
			
			double[] numbers = NumberUtils.parseDoubleArray(this.params);
			
			double multiplication = this.multiply(numbers);
			System.out.println(multiplication);
		}
	}
	
	/**
	 * Método para multiplicação de um vetor de números.
	 * @param numbers Vetor de números.
	 * @return double Produto.
	 */
	public double multiply(double[] numbers) {
		double multiplication = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) {
			multiplication *= numbers[i];
		}
		
		return multiplication;
	}

	/**
	 * Método para exibição do menu de ajuda
	 */
	@Override
	public void help() {
		System.out.println("Usage:\nmult [<...numbers>]");
		
		System.out.println("\nArguments:");
		System.out.println("  numbers      List of numbers to multiply");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
	}

}

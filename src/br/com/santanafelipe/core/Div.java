package br.com.santanafelipe.core;

import java.util.Locale;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;
import br.com.santanafelipe.utils.NumberUtils;

/**
 * Classe para representação da operação de divisão
 * @author Felipe Santana
 * @version 0.1
 */
public class Div extends Command {

	public Div() {
		super();
	}
	
	public Div(String[] flags, String[] params) {
		super(flags, params);
	}
	
	public Div(String[] flags, String[] params, Div subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto.
	 * @return Div Clone do objeto.
	 */
	@Override
	public Div clone() {
		return new Div(this.flags, this.params, (Div) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de divisão.
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
			// Se existir alguma flag
			
			if (this.flags != null) {
				// Verifica se existe alguma flag de ajuda
				
				if (this.flags[0].equals("-h") || this.flags[0].equals("--help")) {
					this.help(); // Exibe menu de ajuda
					
					return;
				}
				
				throw new InvalidArgumentException(this.flags[0]); // Lança exceção de argumento inválido
			}
			
			// Se não existir parâmetros
			
			if (this.params == null || this.params.length == 0)
				throw new MissingRequiredParamException("<...numbers>"); // Lança exceção de parâmetro obrigatório
			
			double[] numbers = NumberUtils.parseDoubleArray(this.params);
			
			double division = this.divide(numbers);
			System.out.println(division);
		}
	}
	
	/**
	 * Método para divisão recursiva de um vetor de números 
	 * @param numbers Vetor de números
	 * @return double Quociente
	 */
	public double divide(double[] numbers) {
		double division = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) {
			division /= numbers[i];
		}
		
		return division;
	}

	/**
	 * Método para exibição do menu de ajuda do comando.
	 */
	@Override
	public void help() {
		System.out.println("Usage:\ndiv [<...numbers>]");
		
		System.out.println("\nArguments:");
		System.out.println("  numbers      List of numbers to divide");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
	}

}

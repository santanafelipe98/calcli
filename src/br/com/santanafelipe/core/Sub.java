package br.com.santanafelipe.core;

import java.util.Locale;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;
import br.com.santanafelipe.utils.NumberUtils;

/**
 * Classe para representação da operação de subtração.
 * @author Felipe Santana
 * @version 0.1
 */
public class Sub extends Command {

	public Sub() {
		super();
	}
	
	public Sub(String[] flags, String params[]) {
		super(flags, params);
	}
	
	public Sub(String[] flags, String params[], Sub subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto.
	 * @return Sub - Clone do objeto.
	 */
	@Override
	public Sub clone() {
		return new Sub(this.flags, this.params, (Sub) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de subtração.
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
			this.subcommand.execute(); // Delega operação para o subcomando
		} else {
			// Se existir alguma flag
			
			if (this.flags != null) {
				// Percorre todas as flags
				
				for (int i = 0; i < this.flags.length; i++) {
					// Verifica se existe alguma flag de ajuda
					
					if (this.flags[0].equals("-h") || this.flags[0].equals("--help")) {
						this.help(); // Exibe menu de ajuda
						
						return;
					} else {
						throw new InvalidArgumentException(this.flags[i]); // Lança exceção de argumento inválido
					}
				}
			}
			
			// Se nenhum parâmetro for passado
			
			if (this.params == null || this.params.length == 0)
				throw new MissingRequiredParamException("<...numbers>"); // Lança exceção de parâmetros obrigátorios
			
			double[] numbers = NumberUtils.parseDoubleArray(this.params);
			
			double subtraction = this.subtract(numbers);
			System.out.println(String.format(Locale.US, "%.2f", subtraction));
		}
	}
	
	/**
	 * Método para subtração recursiva de um vetor numérico
	 * @param numbers - Vetor de números
	 * @return double - Resultado
	 */
	public double subtract(double[] numbers) {
		double subtraction = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) {
			subtraction -= numbers[i];
		}
		
		return subtraction;
	}

	/**
	 * Método para exibição do menu de ajuda do comando.
	 */
	@Override
	public void help() {
		System.out.println("Usage:\nsub [<...numbers>]");
		
		System.out.println("\nArguments:");
		System.out.println("  numbers      List of numbers to subtract");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
	}

}

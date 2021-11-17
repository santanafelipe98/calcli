package br.com.santanafelipe.core;

import java.util.Locale;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;
import br.com.santanafelipe.utils.NumberUtils;

/**
 * Classe para representação da operação de adição de números ímpares.
 * @author Felipe Santana
 * @version 0.1
 */
public class AddEven extends Add {
	
	public AddEven() {
		super();
	}
	
	public AddEven(String[] flags, String[] params) {
		super(flags, params);
	}
	
	public AddEven(String[] flags, String[] params, AddEven subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto
	 * @return AddEven - Clone do objeto
	 */
	@Override
	public AddEven clone() {
		return new AddEven(this.flags, this.params, (AddEven) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de adição de números ímpares.
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
			ExceededParamCountException  {
		// Se existir algum subcomando
		
		if (this.subcommand != null) {
			this.subcommand.execute(); // Delega a operação para o subcomando
		} else {
			// Se existir alguma flag
			
			if (this.flags != null) {
				// Verifica se existe alguma flag de ajuda
				
				if (this.flags[0].equals("-h") || this.flags[0].equals("--help") ) {
					this.help(); // Exibe menu de ajuda
					
					return;
				}
				
				throw new InvalidArgumentException(this.flags[0]); // Lança exceção de argumento inválido
			}
			
			// Se não existir parâmetros
			
			if (this.params == null || this.params.length == 0)
				throw new MissingRequiredParamException("<...numbers>"); // Lança exceção de parâmetro obrigatório
			
			double[] numbers = NumberUtils.parseDoubleArray(this.params);
			
			double sum = this.sum(numbers);
			System.out.println(sum);
		}	
	}
	
	/**
	 * Método para realizar a soma de números ímpares de um vetor de números
	 * @param numbers - O vetor de números
	 * @return double - Soma dos números
	 */
	@Override
	public double sum(double[] numbers) {
		double sum = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			double number = numbers[i];
			
			if (!NumberUtils.isOdd(number)) {
				sum += number; // Realiza soma
			}
		}
		
		return sum;
	}
	
	/**
	 * Método para exibição do menu de ajuda
	 */
	@Override
	public void help() {
		System.out.println("Usage:\nadd even [<...numbers>]");
		
		System.out.println("\nArguments:");
		System.out.println("  numbers      List of numbers to add");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
	}
}

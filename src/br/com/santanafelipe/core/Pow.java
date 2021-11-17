package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;
import br.com.santanafelipe.utils.NumberUtils;

/**
 * Classe para representação da operação de potenciação
 * @author Felipe Santana
 * @version 0.1
 */
public class Pow extends Command {
	
	public Pow() {
		super();
	}
	
	public Pow(String[] flags, String[] params) {
		super(flags, params);
	}
	
	public Pow(String[] flags, String[] params, Pow subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto.
	 * @return Pow - Clone do objeto
	 */
	@Override
	public Pow clone() {
		return new Pow(this.flags, this.params, (Pow) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de potenciação.
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
			this.subcommand.execute(); // Delega operação para o subcomando
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
				throw new MissingRequiredParamException("<number>");
			
			double[] numbers = NumberUtils.parseDoubleArray(this.params);
			
			double pow = this.pow(numbers);
			System.out.println(pow);
		}
	}

	/**
	 * Método para realização de potenciação recursiva de um vetor de números.
	 * @param numbers - Vetor de números.
	 * @return double - Potência.
	 */
	protected double pow(double[] numbers) {
		double base     = (numbers.length > 0)  ? numbers[0] : 0;
		double exponent = (numbers.length >= 1) ? numbers[1] : 0;
		double pow      = Math.pow(base, exponent);
		
		for (int i = 2; i < numbers.length; i++) {
			exponent = numbers[i];
			pow = Math.pow(pow, exponent);
		}
		
		return pow;
	}

	/**
	 * Exibe o menu de ajuda do comando.
	 */
	@Override
	public void help() {
		System.out.println("Usage:\npow [<...numbers>]");
		
		System.out.println("\nArguments:");
		System.out.println("  base       The base number");
		System.out.println("  exponent   The exponent number");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
	}

}

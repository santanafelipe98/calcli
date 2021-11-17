package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;

/**
 * Classe para representação da operação de radiciação.
 * @author Felipe Santana
 * @version 0.1
 */
public class Sqrt extends Command {

	public Sqrt() {
		super();
	}
	
	public Sqrt(String[] flags, String[] params) {
		super(flags, params);
	}
	
	public Sqrt(String[] flags, String[] params, Sqrt subcommand) {
		super(flags, params, subcommand);
	}
	
	/**
	 * Método para clonagem do objeto
	 * @return Sqrt - Clone do objeto
	 */
	@Override
	public Sqrt clone() {
		return new Sqrt(this.flags, this.params, (Sqrt) this.subcommand);
	}
	
	/**
	 * Método para execução do comando de cálculo da raiz quadrada.
	 * @throws InvalidArgumentException         Se o argumento passado for inválido.
	 * @throws MissingRequiredArgumentException Se um argumento obrigatório não for passado.
	 * @throws MissingRequiredParamException    Se um parâmetro obrigatório não for passado.
	 * @throws ExceededParamCountException      Se a quantidade de parâmetros for maior que a quantidade padrão de parâmetros do comando.
	 * @see    InvalidArgumentException
	 * @see    MissingRequiredArgumentException
	 * @see    MissingRequiredParamException
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
			// Se houver flags
			
			if (this.flags != null) {
				// Verifica se existe alguma flag de ajuda
				if (this.flags[0].equals("-h") || this.flags[0].equals("--help")) {
					this.help(); // Exibe menu de ajuda
					
					return;
				}
				
				throw new InvalidArgumentException(this.flags[0]);
			}
			
			// Se não existir paramêtros
			
			if (this.params == null || this.params.length == 0)
				throw new MissingRequiredParamException("<rooting>"); // Lança exceção de parâmetro obrigatório
			
			double[] numbers = new double[this.params.length];
			numbers[0]       = Double.parseDouble(this.params[0]);  
			
			if (numbers.length > 1) {
				throw new ExceededParamCountException("sqrt", 1, numbers.length);
			}
			
			double sqrt = Math.sqrt(numbers[0]);
			System.out.println(sqrt);
		}
	}

	/**
	 * Método para exibição do menu de ajuda do comando.
	 */
	@Override
	public void help() {
		System.out.println("Usage:\nsqrt [number]");
		
		System.out.println("\nArguments:");
		System.out.println("  rooting      The rooting number");
		
		System.out.println("\nOptions:");
		System.out.println("  -h, --help   Show this help message and exit");
	}

}

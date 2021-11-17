package br.com.santanafelipe.core;

import br.com.santanafelipe.errors.ExceededParamCountException;
import br.com.santanafelipe.errors.InvalidArgumentException;
import br.com.santanafelipe.errors.MissingRequiredArgumentException;
import br.com.santanafelipe.errors.MissingRequiredParamException;

/**
 * Classe para representação da calculadora
 * @author Felipe Santana
 * @version 0.1
 */
public class Calculator implements IInvoker {
	private ICommand operation;
	
	public Calculator(ICommand operation) {
		this.setOperation(operation);
	}
	
	/**
	 * Método para a execução da operação matemática.
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
	public void executeCommand()
			throws InvalidArgumentException,
			MissingRequiredArgumentException,
			MissingRequiredParamException,
			ExceededParamCountException  {
		this.operation.execute();
	}
	
	/**
	 * Método para obtenção da operação atual
	 * @return Command Operação
	 */
	public ICommand getOperation() {
		return this.operation;
	}
	
	/**
	 * Método para definição da operação atual
	 * @param operation A operação a ser realizada
	 */
	public void setOperation(ICommand operation) {
		this.operation = operation;
	}
}

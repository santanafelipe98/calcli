package br.com.santanafelipe.parser;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;
import br.com.santanafelipe.core.ICommand;
import br.com.santanafelipe.core.Add;
import br.com.santanafelipe.core.AddOdd;
import br.com.santanafelipe.core.AddEven;
import br.com.santanafelipe.core.Sub;
import br.com.santanafelipe.errors.CommandNotFoundException;
import br.com.santanafelipe.core.Mult;
import br.com.santanafelipe.core.Pow;
import br.com.santanafelipe.core.Sqrt;
import br.com.santanafelipe.core.Div;
import br.com.santanafelipe.core.Helper;

/**
 * Classe utilitária para criação de comandos.
 * @author Felipe Santana
 * @version 0.1
 */
public abstract class CommandFactory {
	public static final String COMMAND_ADD  = "add";
	public static final String COMMAND_ODD  = "odd";
	public static final String COMMAND_EVEN = "even";
	public static final String COMMAND_SUB  = "sub";
	public static final String COMMAND_MULT = "mult";
	public static final String COMMAND_DIV  = "div";
	public static final String COMMAND_POW  = "pow";
	public static final String COMMAND_SQRT = "sqrt";
	public static final String COMMAND_HELP_LONG = "--help";
	public static final String COMMAND_HELP_SHORT = "-h";
	
	/**
	 * Método para criação de comando via objeto de configuração.
	 * @param dto      - Objeto de configuração.
	 * @return Command - Comando
	 * @throws InvalidArgumentException Se o objeto de configuração não for válido
	 */
	public static ICommand create(CommandDTO dto) throws CommandNotFoundException {
		ICommand head = find(dto.commands[0], dto.flags, dto.params);
		ICommand tail = null;
		
		for (int i = 1; i < dto.commands.length; i++) {
			ICommand aux = find(dto.commands[i], dto.flags, dto.params);
			
			if (i == 1) {
				if (aux instanceof Helper) {
					ICommand copyOfHead = head.clone();
					head               = aux;
					tail               = copyOfHead;
				} else {
					tail = aux;
					head.setSubcommand(tail);
				}
			} else {
				if (aux instanceof Helper) {
					ICommand copyOfHead = head.clone();
					head = aux;
					tail = copyOfHead;
				} else {
					tail = tail.getSubcommand();
					tail.setSubcommand(find(dto.commands[i], dto.flags, dto.params));
				}
			}
		}
		
		return head;
	}
	
	/**
	 * Método para encontrar um comando pelo nome.
	 * @param commandName - Nome do comando.
	 * @param flags       - Vetor de flags.
	 * @param params      - Vetor de parâmetros.
	 * @return Command    - Commando encontrado.
	 * @throws CommandNotFoundException Se o comando não for encontrado.
	 */
	public static ICommand find(String commandName, String[] flags, String[] params) throws CommandNotFoundException {
		switch (commandName) {
		 	case COMMAND_ADD:
		 		return new Add(flags, params);
		 	case COMMAND_ODD:
		 		return new AddOdd(flags, params);
		 	case COMMAND_EVEN:
		 		return new AddEven(flags, params);
		 	case COMMAND_SUB:
		 		return new Sub(flags, params);
		 	case COMMAND_MULT:
		 		return new Mult(flags, params);
		 	case COMMAND_DIV:
		 		return new Div(flags, params);
		 	case COMMAND_POW:
		 		return new Pow(flags, params);
		 	case COMMAND_SQRT:
		 		return new Sqrt(flags, params);
		 	case COMMAND_HELP_SHORT:
		 		return new Helper();
		 	case COMMAND_HELP_LONG:
		 		return new Helper();
		 	default:
		 		throw new CommandNotFoundException(commandName);
		}
	}
}

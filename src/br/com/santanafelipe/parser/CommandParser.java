package br.com.santanafelipe.parser;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.santanafelipe.errors.InvalidStatementSyntaxException;

import java.util.regex.Matcher;

/**
 * Classe utilitária para a criação de objetos de configuração para comandos.
 * @author Felipe Santana
 * @version 0.1
 */
public abstract class CommandParser {
	/**
	 * Método estático para transformar um instrução em um objeto de configuração.
	 * @param statement   - Instrução
	 * @return CommandDTO - Objeto de configuração
	 * @throws InvalidStatementSyntaxException Se a instrução
	 */
	public static CommandDTO parse(String statement) throws InvalidStatementSyntaxException {
		List<Pattern> patterns = new ArrayList<>();
		patterns.add(Pattern.compile("(--?[a-z]+|[a-z]+)"));
		patterns.add(Pattern.compile("(([a-z]+)((\\s[a-z]+)+)?)((\\s-[a-z]+)|((\\s--[a-z]+)((\\s--[a-z]+)+)?))"));
		patterns.add(Pattern.compile("(([a-z]+)((\\s[a-z]+)+)?)((\\s-[a-z]+)|((\\s--[a-z]+)((\\s--[a-z]+)+)?))?((\\s-?((\\d+)?\\.)?\\d+)((\\s-?((\\d+)?\\.)?\\d+)+)?)"));
		
		for (Pattern commandPattern : patterns) {
			Matcher commandMatcher = commandPattern.matcher(statement);

			if (commandMatcher.matches()) {
				String[] commands  = commandMatcher.group(1).trim().split(" ");
				String[] flags     = null;
				String[] params    = null;
				
				if (commandMatcher.groupCount() >= 5) {
					if (commandMatcher.group(5) != null)
							flags = commandMatcher.group(5).trim().split(" ");
					
					if (commandMatcher.groupCount() >= 11) {
						if (commandMatcher.group(11) != null)
							params = commandMatcher.group(11).trim().split(" ");
					}
				}
				
				return new CommandDTO(commands, flags, params);
			}
		}
		
		throw new InvalidStatementSyntaxException(statement);
	}
}
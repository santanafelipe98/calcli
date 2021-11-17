package br.com.santanafelipe.entry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.santanafelipe.core.Calculator;
import br.com.santanafelipe.core.ICommand;
import br.com.santanafelipe.parser.CommandDTO;
import br.com.santanafelipe.parser.CommandFactory;
import br.com.santanafelipe.parser.CommandParser;

public class Main {

	public static void main(String[] args) {
		String statement = String.join(" ", args).replaceAll("[\n\t\r]", "").toLowerCase();
		Calculator calc;

		try {
			CommandDTO dto    = CommandParser.parse(statement);
			ICommand operation = CommandFactory.create(dto);
			calc              = new Calculator(operation);
			calc.executeCommand();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

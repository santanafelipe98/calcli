package br.com.santanafelipe.utils;

/**
 * Classe utilitária para realização de operações numéricas.
 * @author Felipe Santana
 * @version 0.1
 */
public abstract class NumberUtils {
	/**
	 * Método para verificar se um dado número é par.
	 * @param number   - Número em questão
	 * @return boolean - Resultado 
	 */
	public static boolean isOdd(double number) {
		return number % 2 == 0;
	}
	
	/**
	 * Método para verificar se o conteúdo de uma String é numérico.
	 * @param str      - String em questão
	 * @return boolean - Resultado
	 */
	public static boolean isNumeric(String str) {
		return str.matches("-?(\\d+)?\\.?\\d+");
	}
	
	/**
	 * Método para verificar se o conteúdo de uma String é um número inteiro.
	 * @param number   - String em questão
	 * @return boolean - Resultado
	 */
	public static boolean isInt(String number) {
		return number.matches("-?\\d+");
	}
	
	/**
	 * Método para verificar se o conteúdo de uma String é um número decimal.
	 * @param number   - String em questão
	 * @return boolean - Resultado
	 */
	public static boolean isFloat(String number) {
		return number.matches("-?(\\d+)?\\.\\d+");
	}
	
	/**
	 * Método para conversão de um vetor de strings númericas para um vetor de doubles
	 * @param numbers   Vetor de strings numéricas
	 * @return double[] Vetor de números
	 */
	public static double[] parseDoubleArray(String[] numbers) {
		double[] n = new double[numbers.length];
		
		for (int i = 0; i < numbers.length; i++) {
			double number = Double.parseDouble(numbers[i]);
			n[i]          = number;
		}
		
		return n;
	}
}

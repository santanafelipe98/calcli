# calcli
Calculadora CLI que realiza operações básicas como **adição**, **subtração**, **multiplicação**, **divisão**, **radiciação** e **potenciação**. Totalmente implementada em Java.

## Tecnologias
1. Java

## Comandos
* **add**: Realiza a adição de uma lista de números. Ex.: <code>**add** 10 10</code> = 20
  * <code>flags</code>
    * -f, --float: Permite a adição de números com casas decimais. Ex.: <code>**add** -f 10 1.1 1.2</code> = 12.3
  * <code>subcomandos</code>
    * odd: Realiza a adição somente de números ímpares. Ex.: <code>add **even** 3 1 12 7</code> = 11
    * even: Realiza a adição somente de números pares. Ex.: <code>add **odd** 10 4 5 1</code> = 14
* **sub**: Realiza a subtração de uma lista de números. Ex.: <code>**sub** 10 2 30 -3</code> = -19
* **mult**: Realiza a multiplicação de uma lista de números. Ex.: <code>**mult** 3 2</code> = 6
* **div**: Realiza a divisão recursiva de uma lista de números. Ex.: <code>**div** 9 3</code> = 3
* **sqrt**: Calcula a raiz quadrada de determinado número. Ex.: <code>**sqrt** 25</code> = 5
* **pow**: Calcula a potência de uma lista de números recursivamente <code>**pow** 2 3 2</code> = 64

Para mais informações sobre o comando use a flag <code>-h</code> ou <code>--help</code>.

## Licença
Este é um projeto open-source e está disponível sobre a [licença MIT](https://github.com/santanafelipe98/calcli/blob/main/LICENSE).

# ia-viterbi
Codificador/decodificador convolucional usando o Algoritmo de Viterbi

Alunos: Elias Fank; João Gehlen; Ricardo Zanuzzo


## Como compilar e executar:
### Por Makefile: 

  $ make all
  $ make run <qtd_bits> <ruido>

  <qtd_bits> deve ser informado a quantidade de bits para gerar a sequencia aleatória.
  <ruido> deve ser informado a porcentagem de ruido, por exemplo 5 para 5% de ruido.


### Exemplo de execução:

  $ make run 100 5

  Nessa execução vai gerar uma sequencia de 100 bits na entrada e vai usar um ruído de 5%	 
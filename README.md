# ia-viterbi
Codificador/decodificador convolucional usando o Algoritmo de Viterbi

Alunos: Elias Fank; João Gehlen; Ricardo Zanuzzo


## Como compilar e executar:
### Por Makefile: 
 ```sh
  $ make all
  $ make run <qtd_bits> <ruido>
```

Onde \<qtd_bits> deve ser informado a quantidade de bits para gerar a sequencia aleatória.

E \<ruido> deve ser informado a porcentagem de ruido, por exemplo 5 para 5% de ruido.

### Exemplo de execução:
```sh
  $ make run 100 5
```
Nessa execução vai gerar uma sequencia de 100 bits na entrada e vai usar um ruído de 5%

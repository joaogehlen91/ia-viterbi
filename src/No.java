/*
 * Nomes: Elias Fank, João Gehlen, Ricardo Zanuzzo
 * Disciplina: Inteligencia Artificial
 * 
 * 2017/1
 * 
 * Esta classe eh usada para construir cada folha da arvore.
 * 
 */

public class No {
	public String estado, bitsParEmitido;
	public int pesoDir, pesoEsq, distRaiz, bitQueLevou;
	public No pai, direita, esquerda;
}
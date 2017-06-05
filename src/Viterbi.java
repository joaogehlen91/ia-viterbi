/*
 * Nomes: Elias Fank, João Gehlen, Ricardo Zanuzzo
 * Disciplina: Inteligencia Artificial
 * 
 * 2017/1
 * 
 * Esta classe eh a classe principal do algoritmo de Viterbi.
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Viterbi {

	public static void main(String[] args) {
		String emitir[][] = {{"00", "11"}, {"11", "00"}, {"10", "01"}, {"01", "10"}};
		String estado[][] = {{"00", "10"}, {"00", "10"}, {"01", "11"}, {"01", "11"}};

		String estadoAtual = "00";
		String saida = "";
		
		String entrada = stringBinariaRandomica(Integer.parseInt(args[0]));
		entrada+="00";
		
		// nivel de ruido em porcentagem(0 - 1), exemplo: 0.75 = 75% de ruido
		Double ruido = 0.01*Double.parseDouble(args[1]); 
		
		System.out.println("Entrada + par de bits extra:");
		System.out.println(entrada);
		
		// codificacao
		for (String ent : entrada.split("")) {
			String logConsole = "";
			logConsole += estadoAtual + "      |" + ent + "   |";
			int i = stringParaInt(estadoAtual);
			int j = Integer.parseInt(ent);
			estadoAtual = estado[i][j];
			saida = saida + emitir[i][j];
			logConsole += emitir[i][j] + "    |" + estadoAtual;
			//System.out.println(logConsole);
		}
		
		
		
		System.out.println("\nPares a serem enviados:\n" + saida);
		
		/* INICIO RUIDO */
		// calcula a quantidade de bits que precisa ser trocado
		int qtdBitsRuido = (int) Math.round((saida.length() * ruido));
		
		// cria lista para colocar a saida com ruido
		ArrayList<String> bitsSaidaComRuido = new ArrayList<String>();
		bitsSaidaComRuido = converteStringtoArrayList(saida);
		
		// lista usada para sortear os bits a serem trocados
		ArrayList<Integer> listIDs = new ArrayList<Integer>();
		for (int i = 0; i < bitsSaidaComRuido.size(); i++) listIDs.add(new Integer(i));
		Collections.shuffle(listIDs);
				
        // laço para realizar a troca dos bits sorteados;
		for (int i = 0; i < qtdBitsRuido; i++) {
			Integer idBit = listIDs.get(0);
			listIDs.remove(0);
			//System.out.println("Troca o bit: " + idBit);
			bitsSaidaComRuido.set(idBit, inverte(bitsSaidaComRuido.get(idBit)));
		}
		
		// formata a saida com os bits ja trocados
		String saidaComRuido = "";
		for (String bit : bitsSaidaComRuido) saidaComRuido = saidaComRuido + bit;
		
		System.out.println("\nQtd de bits trocados:\n" + qtdBitsRuido);
		System.out.println("\nSaida com Ruido:\n" + saidaComRuido);
		/* FIM RUIDO */
		

		ArrayList<String> bitsDecodificar = new ArrayList<String>();

		for(int i=0; i<bitsSaidaComRuido.size(); i+=2){
			bitsDecodificar.add(bitsSaidaComRuido.get(i)+bitsSaidaComRuido.get(i+1));
		}

		String[] stringArray = bitsDecodificar.toArray(new String[0]);
		Arvore a = new Arvore(stringArray.length, stringArray);
    	//a.imprimirArvore(a.raiz, 0, 0);
    	String resultado = a.gerarSaida();
    	System.out.println("\nResultado Viterbi:");
    	System.out.println(resultado);


    	System.out.println("\nA entrada e a saída deferem em:");
    	System.out.println(a.calculaPeso(entrada, resultado));

		
	}


	private	static String stringBinariaRandomica( int len ){
		Random rnd = new Random();
		String ab = "01";
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( ab.charAt( rnd.nextInt(ab.length()) ) );
		return sb.toString();
	}


	private static ArrayList<String> converteStringtoArrayList(String texto) {
		char[] caracteres = texto.toCharArray();
		ArrayList<String> lista = new ArrayList<String>();
		for(char c : caracteres){
		    lista.add(String.valueOf(c));
		}
		return lista;
	}


	private static String inverte(String string) {
		if (string.equals("0")) return "1";
		return "0";
	}


	private static int stringParaInt(String string) {
		int saida = 0;
		switch (string) {
		case "00":
			saida = 0;
			break;
		case "01":
			saida = 1;
			break;
		case "10":
			saida = 2;
			break;
		case "11":
			saida = 3;
			break;
		}
		return saida;
	}

	
}

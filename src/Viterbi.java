import java.util.ArrayList;
import java.util.Collections;


public class Viterbi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String emitir[][] = {{"00", "11"}, {"11", "00"}, {"10", "01"}, {"01", "10"}};
		String estado[][] = {{"00", "10"}, {"00", "10"}, {"01", "11"}, {"01", "11"}};

		String estadoAtual = "00";
		String saida = "";
		
		String entrada = "10111010001001000";
		Double ruido = 0.05; // nivel de ruido em porcentagem(0 - 1), exemplo: 0.75 = 75% de ruido
		
		System.out.println("Entrada:");
		System.out.println(entrada);
		//System.out.println("Est Atu |Ent |Emite |Prox Est");
		
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
		
		
		/* TODO 
		 * Além disto são gerados 2 pares de bits extras no final para usar toda a influencia dos bits de entrada.
		 * Nao lembro como que é esses dois bits, repete os dois ultimos? o ultimo duas vezes??
		 * */
		
		
		
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
		
		//viterbi(saidaComRuido);

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

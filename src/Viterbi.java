
public class Viterbi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String emitir[][] = {{"00", "11"}, {"11", "00"}, {"10", "01"}, {"01", "10"}};
		String estado[][] = {{"00", "10"}, {"00", "10"}, {"01", "11"}, {"01", "11"}};

		String estadoAtual = "00";
		String saida = "";
		
		String entrada = "10101";
		
		System.out.println("Est Atu |Ent |Emite |Prox Est");
		
		// codificacao
		for (String ent : entrada.split("")) {
			String logConsole = "";
			logConsole += estadoAtual + "      |" + ent + "   |";
			int i = stringParaInt(estadoAtual);
			int j = Integer.parseInt(ent);
			estadoAtual = estado[i][j];
			saida = saida + emitir[i][j];
			logConsole += emitir[i][j] + "    |" + estadoAtual;
			System.out.println(logConsole);
		}
		
		
		/* TODO 
		 * Além disto são gerados 2 pares de bits extras no final para usar toda a influencia dos bits de entrada.
		 * Nao lembro como que é esses dois bits, repete os dois ultimos? o ultimo duas vezes??
		 * */
		System.out.println("\nSaida: " + saida);
		
		/* TODO
		 * fazer ruido
		 * 
		 * */
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

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Arvore {
	public No raiz;
	public int profundidade;
	public String[] bitsRecebidos;	
	public ArrayList<No> folhas = new ArrayList<No>();
	Map<String,String[]> parEmitido = new HashMap<String,String[]>()
	{
        {
            put(new String("00"), new String[] {new String("00"), new String("11")});
            put(new String("01"), new String[] {new String("11"), new String("00")});
            put(new String("10"), new String[] {new String("10"), new String("01")});
            put(new String("11"), new String[] {new String("01"), new String("10")});
        };
    };
	Map<String,String[]> proximoEstado = new HashMap<String,String[]>()
	{
        {
            put(new String("00"), new String[] {new String("00"), new String("10")});
            put(new String("01"), new String[] {new String("00"), new String("10")});
            put(new String("10"), new String[] {new String("01"), new String("11")});
            put(new String("11"), new String[] {new String("01"), new String("11")});
        };
    };
	public Arvore(int p, String[] entrada){
		this.profundidade = p;
		this.bitsRecebidos = entrada;
		No n = new No();
		n.pai = null;
		n.bitsParEmitido = null;
		n.estado = "00";
		n.esquerda = null;
		n.direita = null;
		n.pesoEsq = 0;
		n.pesoDir = 0;
		n.distRaiz = 0;
		this.raiz = n;
		geraArvore(this.raiz, 0);
	}

	public void geraArvore(No n, int p){
		if(p >= this.profundidade){
			this.folhas.add(n);
			return;
		} 
		No noEsq = new No();
		No noDir = new No();
		noEsq.pai = noDir.pai = n;
		noEsq.bitQueLevou = 0;
		noDir.bitQueLevou = 1;
		noEsq.bitsParEmitido = this.parEmitido.get(n.estado)[noEsq.bitQueLevou];
		noDir.bitsParEmitido = this.parEmitido.get(n.estado)[noDir.bitQueLevou];
		noEsq.estado = this.proximoEstado.get(n.estado)[noEsq.bitQueLevou];
		noDir.estado = this.proximoEstado.get(n.estado)[noDir.bitQueLevou];
		n.pesoEsq = calculaPeso(this.bitsRecebidos[p], noEsq.bitsParEmitido);
		n.pesoDir = calculaPeso(this.bitsRecebidos[p], noDir.bitsParEmitido);
		noEsq.distRaiz = n.distRaiz + n.pesoEsq;
		noDir.distRaiz = n.distRaiz + n.pesoDir;
		n.esquerda = noEsq;
		n.direita = noDir;
		geraArvore(noEsq, p+1);
		geraArvore(noDir, p+1);
	}

	public int calculaPeso(String s1, String s2){
		if(s1 == null) return 0;
		int counter = 0, index = 0;
		for(char c : s1.toCharArray()){
        	if (c != s2.charAt(index))
            	counter++;
            index++;    
		}

		return counter;
	}

	public ArrayList<String> bitsDoMelhorCaminho() {
		No noMenor = this.folhas.get(0);
		for(No n : this.folhas){
			if(n.distRaiz < noMenor.distRaiz)
				noMenor = n;
		}
		ArrayList<String> paresBits = new ArrayList<String>();
		while(noMenor.pai != null){
			paresBits.add(String.valueOf(noMenor.bitQueLevou));
			noMenor = noMenor.pai;
		}
		return paresBits;

	}

	public String gerarSaida(){
		ArrayList<String> paresDeBits = bitsDoMelhorCaminho();
		String resultado = new String();
		for(int i=paresDeBits.size()-1; i>=0; i--){
			resultado+=paresDeBits.get(i);
		} 
		return resultado;
	}

	public  void imprimirArvore(No raiz, int peso, int nivel){
	    if(raiz==null)
	         return;
	    imprimirArvore(raiz.direita, raiz.pesoDir, nivel+1);
	    if(nivel!=0){
	        for(int i=0;i<nivel-1;i++)
	            System.out.print("|\t");
	            System.out.println("|---"+peso+"---"+raiz.bitsParEmitido);
	    }
	    else
	        System.out.println(raiz.bitsParEmitido);
	    imprimirArvore(raiz.esquerda, raiz.pesoEsq, nivel+1);
	}  

}
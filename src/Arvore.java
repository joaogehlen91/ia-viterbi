import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Arvore {

	public No raiz;
	public int profundidade, poda=8;
	public String[] bitsRecebidos;	
	public ArrayList<No> folhas = new ArrayList<No>();
	public ArrayList<No> pdzinha = new ArrayList<No>();
	public Map<String,String[]> parEmitido = new HashMap<String,String[]>()
	{
        {
            put(new String("00"), new String[] {new String("00"), new String("11")});
            put(new String("01"), new String[] {new String("11"), new String("00")});
            put(new String("10"), new String[] {new String("10"), new String("01")});
            put(new String("11"), new String[] {new String("01"), new String("10")});
        };
    };
	public Map<String,String[]> proximoEstado = new HashMap<String,String[]>()
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
		pdzinha.add(n);
		geraArvore(0);
	}


	public void geraArvore(int nivel){

		if(nivel >= this.profundidade){
			for(No n : pdzinha){
				this.folhas.add(n);
			}
			return;
		} 

		ArrayList<No> novapdzinha = new ArrayList<No>();
		for(No n : this.pdzinha){
			No noEsq = new No();
			No noDir = new No();
			noEsq.pai = noDir.pai = n;
			noEsq.bitQueLevou = 0;
			noDir.bitQueLevou = 1;
			noEsq.bitsParEmitido = this.parEmitido.get(n.estado)[noEsq.bitQueLevou];
			noDir.bitsParEmitido = this.parEmitido.get(n.estado)[noDir.bitQueLevou];
			noEsq.estado = this.proximoEstado.get(n.estado)[noEsq.bitQueLevou];
			noDir.estado = this.proximoEstado.get(n.estado)[noDir.bitQueLevou];
			n.pesoEsq = calculaPeso(this.bitsRecebidos[nivel], noEsq.bitsParEmitido);
			n.pesoDir = calculaPeso(this.bitsRecebidos[nivel], noDir.bitsParEmitido);
			noEsq.distRaiz = n.distRaiz + n.pesoEsq;
			noDir.distRaiz = n.distRaiz + n.pesoDir;
			n.esquerda = noEsq;
			n.direita = noDir;
			novapdzinha.add(noEsq);
			novapdzinha.add(noDir);
		}

		Collections.sort(novapdzinha, new Comparator<No>() {
	        @Override
	        public int compare(No no1, No no2)
	        {
	        	return no1.distRaiz < no2.distRaiz ? -1 : no1.distRaiz > no2.distRaiz ? +1 : 0;
	        }
    	});

		this.pdzinha = new ArrayList<No>();
		int qtPoda = 0;
		if (novapdzinha.size() < this.poda)
			qtPoda = novapdzinha.size();
		else
			qtPoda = this.poda;
		for (int i=0; i<qtPoda; i++){
			this.pdzinha.add(novapdzinha.get(i));
		}

		geraArvore(nivel+1);
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
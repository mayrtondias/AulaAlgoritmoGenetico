
package aulaalgoritmogenetico;

/**
 *
 * @author Liaa
 */
public class Cromossomo {
    private int genes[];
    private double avaliacao = 0;
    
    public Cromossomo(){
        genes = new int[10];
        inicializarCromossomo();
    }
    
    public void inicializarCromossomo(){
        int i;
        
        for(i = 0; i < 10; ++i){
            genes[i] = 0;
        }
        
        i = (int)Math.random()*10;
        genes[i] = 1;
    }
    
    public double avaliar(){
        double somaPesos = 0, somaValores = 0;
        
        for(int i = 0; i < 10; ++i){
            if(genes[i] == 1){
                somaPesos += Tela.PESOS[i];
                somaValores += Tela.VALORES[i];
            }
        }
        if(somaPesos <= Tela.CAPACIDADE){
            this.avaliacao =  somaValores;
        }else{
            this.avaliacao = 1;
        }
        return this.avaliacao;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
    
}

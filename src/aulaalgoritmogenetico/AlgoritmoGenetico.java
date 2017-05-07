package aulaalgoritmogenetico;

import java.util.LinkedList;

/**
 *
 * @author Liaa
 */
public class AlgoritmoGenetico {
    
    private int tamanhoPopulacao;
    private int geracoes;
    private double mutacao;
    private LinkedList<Cromossomo> populacao, novaPopulacao;
    
    public AlgoritmoGenetico(int tamanhoPopulacao, int geracoes, double mutacao){
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geracoes = geracoes;
        this.mutacao = mutacao;
        populacao = new LinkedList<>();
        novaPopulacao = new LinkedList<>();
    }
    
    public void iniciar(){
        double soma;
        int i, j, pai1, pai2;
        
        inicializarPopulacao();
        
        for(i = 0; i < this.geracoes; ++i){
            soma = avaliarPopulacao();
            
            for(j = 0; j < this.tamanhoPopulacao; ++j){
                pai1 = selecionar(soma);
                pai2 = selecionar(soma);
                
                crossover(pai1, pai2);
                
                mutacao(novaPopulacao.getLast());
            }
            
            modulo();
        }
        avaliarPopulacao();
        Tela.MELHOR = verificarMelhor();
    }
    
    public void inicializarPopulacao(){
        Cromossomo individuo;
        
        for(int i = 0; i< this.tamanhoPopulacao; ++i){
            individuo = new Cromossomo();
            populacao.add(individuo);
        }
    }
    
    public double avaliarPopulacao(){
        double soma = 0;
        
        for(Cromossomo c: this.populacao){
            soma = c.avaliar();
        }
        
        return soma;
    }
    
    public int selecionar(double total){
        double escolha, soma = 0;
        int contador = 0;
        
        escolha = Math.random()*total;
        
        for(Cromossomo c: this.populacao){
            if(soma + c.getAvaliacao() > escolha ){
                return contador;
            }
            soma += c.getAvaliacao();
            ++contador;
        }
        return  0;
    }
    
    public void crossover(int pai1, int pai2){
        int primeiroPai[] = populacao.get(pai1).getGenes();
        int segundoPai[] = populacao.get(pai2).getGenes();
        Cromossomo filho = new Cromossomo();
        int genes[] = new int[10];
        int corte;
        
        corte = (int)Math.random()*10;
        
        for(int i = 0; i < corte; ++i){
            genes[i] = primeiroPai[i];
        }
        
        for(int i = corte; i < 10; ++i){
            genes[i] = segundoPai[i];
        }
        
        filho.setGenes(genes);
        novaPopulacao.add(filho);
    }
    
    public void mutacao(Cromossomo filho){
        int novo[] = filho.getGenes();
        for(int i = 0; i < 10; ++i){
            if(Math.random() < this.mutacao){
                if(novo[i] == 0){
                    novo[i] = 1;
                }else{
                    novo[i] = 0;
                }
            }
        }
    }
    
    public void modulo(){
        populacao.clear();
        populacao.addAll(novaPopulacao);
    }
    
    public int[] verificarMelhor(){
        int melhorID = 0, contador = 0;
        double melhorValor = populacao.get(0).getAvaliacao();
        
        for(Cromossomo c: populacao){
            if(c.getAvaliacao() > melhorValor){
                melhorValor = c.getAvaliacao();
                melhorID = contador;
            }
            ++contador;
        }
        
        return populacao.get(melhorID).getGenes();
    }
}

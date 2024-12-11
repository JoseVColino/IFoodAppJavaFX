/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

/**
 *
 * @author José Victor
 */
public class Bebida extends Produto {
    private int tamanho;
    private boolean alcoolica;
    
    public Bebida() {
        super();
        this.tamanho = 0;
        this.alcoolica = false;
    }

    public Bebida(int tamanho, boolean alcoolica, String nome, String descricao, double preco, int tempoDePreparo) {
        super(nome, descricao, preco, tempoDePreparo);
        this.tamanho = tamanho;
        this.alcoolica = alcoolica;
    }    

    public int getTamanho() {
        return tamanho;
    }
    public void setTamanho(int tamanho) {
        if ( tamanho<=0 || tamanho > 100000){
            throw new RuntimeException("tamanho Inválido");
        }
        this.tamanho = tamanho;
    }

    public boolean isAlcoolica() {
        return alcoolica;
    }
    public void setAlcoolica(boolean alcoolica) {
        this.alcoolica = alcoolica;
    }
    
    
    
}

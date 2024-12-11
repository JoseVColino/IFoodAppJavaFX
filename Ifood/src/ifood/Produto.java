/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

/**
 *
 * @author José Victor
 */
public abstract class Produto {
    private String nome;
    private String descricao;
    private double preco;
    private int tempoDePreparo;

    public Produto() {
        this.nome = "";
        this.descricao = "";
        this.preco = 0.0;
        this.tempoDePreparo = 0;
    }    

    public Produto(String nome, String descricao, double preco, int tempoDePreparo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tempoDePreparo = tempoDePreparo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome == null || nome.length() == 0 || nome.length() > 50) {
            throw new RuntimeException("Nome inválido");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        if (preco < 0 || preco > 10000) {
            throw new RuntimeException("Preco inválido");
        }
        this.preco = preco;
    }

    public int getTempoDePreparo() {
        return tempoDePreparo;
    }
    public void setTempoDePreparo(int tempoDePreparo) {
        if (tempoDePreparo < 0 || tempoDePreparo > 1000){
            throw new RuntimeException("Tempo de preparo inválido");
        }
        this.tempoDePreparo = tempoDePreparo;
    }
    
}

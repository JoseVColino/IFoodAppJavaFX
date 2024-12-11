/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

import java.util.ArrayList;

public class Restaurante {
    private String nome;
    private String endereco;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public Restaurante() {
        this.nome = "";
        this.endereco = "";
    }
    
    public Restaurante(String nome, String endereco, ArrayList<Produto> produtos) {
        setNome(nome);
        setEndereco(endereco);
        setProdutos(produtos);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    public ArrayList<String> getProdutosComoStrings(){
        ArrayList<String> nomesProdutos = new ArrayList<>();
        if (produtos == null) {
            return null;
    }
        for (Produto produto : produtos) {
            nomesProdutos.add(produto.getNome());
        }
        return nomesProdutos;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public final void setProdutos(ArrayList<Produto> produtos) {
        if (produtos == null){
            return;
        }
        this.produtos = produtos;
    }
    
    public Produto getProduto(String nome){
        if (produtos == null) {
            return null;
        }
        
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        throw new RuntimeException("Nao foi posssivel achar o produto pelo nome");
    }

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        if ( nome.length() == 0){
            throw new RuntimeException("Nome de Restaurante Inválido");
        }
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public final void setEndereco(String endereco) {
        if ( endereco.length() == 0){
            throw new RuntimeException("endereço Inválido");
        }
        this.endereco = endereco;
    }
    
}

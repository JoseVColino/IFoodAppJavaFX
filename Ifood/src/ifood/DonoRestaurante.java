/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

import java.util.ArrayList;

public class DonoRestaurante extends Usuario{
     private Restaurante restaurante;
     
    
    public DonoRestaurante() {
        super();
        this.restaurante = new Restaurante();
    }
    
    public DonoRestaurante(String login, String senha, String endereco, String nomeRestaurante, ArrayList<Produto> produtos) {
        super(login, senha);
        this.restaurante = new Restaurante(nomeRestaurante, endereco, produtos);
        BancoDeDados.addRestaurante(restaurante);
    }
    
    public Restaurante getRestaurante(){
        return restaurante;
    }
    
}

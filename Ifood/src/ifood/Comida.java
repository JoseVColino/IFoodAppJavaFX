/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

public class Comida extends Produto {
    private String tipoCozinha;
    private boolean vegetariano;
    private boolean vegano;
    
    public Comida() {
        super();
        this.tipoCozinha = "";
        this.vegetariano = false;
        this.vegano = false;
    }
    public Comida(String tipoCozinha, boolean vegetariano, boolean vegano, String nome, String descricao, double preco, int tempoDePreparo) {
        super(nome, descricao, preco, tempoDePreparo);
        this.tipoCozinha = tipoCozinha;
        this.vegetariano = vegetariano;
        this.vegano = vegano;
    }
    
    

    public String getTipoCozinha() {
        return tipoCozinha;
    }
    public void setTipoCozinha(String tipoCozinha) {
        if ( tipoCozinha.length() == 0 || tipoCozinha.length() > 50){
            throw new RuntimeException("Tipo de cozinha Inválido");
        }
        this.tipoCozinha = tipoCozinha;
    }

    public boolean isVegetariano() {
        return vegetariano;
    }
    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }
    
    public boolean isVegano() {
        return vegano;
    }
    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }
    
    
}

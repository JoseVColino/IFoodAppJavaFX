/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

public abstract class Usuario {
    private String login;
    private String senha;
    
    public Usuario() {
    this.login = "";
    this.senha = "";
}

    public Usuario(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    public String getLogin() {
        return login;
    }
    public final void setLogin(String login) {
        if ( login.length() == 0 || login.length() > 50){
            throw new RuntimeException("login Inválido");
        }
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public final void setSenha(String senha) {
        if (senha.length() == 0 || senha.length() > 50){
            throw new RuntimeException("senha Inválida");
        }
        this.senha = senha;
    }
    
    
}

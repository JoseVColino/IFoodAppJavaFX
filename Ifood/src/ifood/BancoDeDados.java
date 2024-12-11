/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifood;

import java.util.ArrayList;

public class BancoDeDados {
    
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Restaurante> restaurantes = new ArrayList<>();

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public static ArrayList<Restaurante> getRestaurantes(){
        return restaurantes;
    }

    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public static void addRestaurante(Restaurante restaurante) {
        restaurantes.add(restaurante);
    }
    
    public static Usuario encontrarUsuario(String login, String senha) {
        try{
            for (Usuario user: usuarios) {
                if  ( login.equals(user.getLogin()) && senha.equals(user.getSenha()) ) {
                   return user;               
                }
            }
            // Se não achar o cliente no banco, sinaliza mandando um null
        return null;
                    
            // Se o arraylist de usuarios estiver vaziol vai dar um erro, com o catch o erro é impedido e o null é enviado
        } catch(Exception e) {
            return null;
        }
            
    }
    
    public static Restaurante encontrarRestaurante(String nome) {
            for (Restaurante restaurante: restaurantes) {
                if  ( nome.equals(restaurante.getNome()) ) {
                   return restaurante;               
                }
            }
            // Se não achar o restaurante no banco, sinaliza mandando um null
             return null;
            
    }
    
    public static Usuario encontrarUsuario(String login) {
        try{
            for (Usuario user: usuarios) {
                if  ( login.equals(user.getLogin()) ) {
                   return user;               
                }
            }
            // Se não achar o cliente no banco, sinaliza mandando um null
        return null;
                    
            // Se o arraylist de usuarios estiver vaziol vai dar um erro, com o catch o erro é impedido e o null é enviado
        } catch(Exception e) {
            return null;
        }
    }
    
    
}

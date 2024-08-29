package br.cefetmg.controller;

import br.cefetmg.dao.ClienteDAO;

public class ControleLogin {
    
    public void logar(String senha, String username) {
        ClienteDAO managerCliente = new ClienteDAO();
        boolean acesso = managerCliente.encontrarCliente(senha, username);
        
        
    }
}

package br.cefetmg.controller;

import br.cefetmg.dao.ClienteDAO;

public class ControleLogin {
    
    public boolean logar(String senha, String username) {
        ClienteDAO managerCliente = new ClienteDAO();
        return managerCliente.encontrarCliente(senha, username);
    }
}
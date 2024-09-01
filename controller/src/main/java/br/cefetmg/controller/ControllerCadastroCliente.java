package br.cefetmg.controller;

import br.cefetmg.dao.ClienteDAO;
import br.cefetmg.entidades.Cliente;

public class ControllerCadastroCliente {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.inserirCliente(cliente);
    }
    
}

package br.cefetmg.controller;

import br.cefetmg.dao.ClienteDAO;
import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.utils.Encriptador;

public class ControllerCadastroCliente {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public void cadastrarCliente(Cliente cliente) {
        Encriptador encriptador = new Encriptador();
        cliente.setSenha(encriptador.encriptarSenha(cliente.getSenha()));
        
        clienteDAO.inserirCliente(cliente);
    }
    
}
package br.cefetmg.controller;

import br.cefetmg.dao.ClienteDAO;
import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.Funcionario;
import java.util.List;

public class ControleLogin {
    
    public List<Cliente> logarCliente(String senha, String username) {
        ClienteDAO managerCliente = new ClienteDAO();
        
        return managerCliente.encontrarCliente(username, senha);
    }
    
    public List<Funcionario> logarFuncionario(String senha, String username) {
        FuncionarioDAO managerFuncionario = new FuncionarioDAO();

        return managerFuncionario.encontrarFuncionario(username, senha);
    }
}
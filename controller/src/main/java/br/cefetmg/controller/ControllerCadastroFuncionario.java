package br.cefetmg.controller;

import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.entidades.Funcionario;

public class ControllerCadastroFuncionario {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public void cadastrarFuncionario(Funcionario funcionario) {
    
        funcionarioDAO.inserirFuncionario(funcionario);
    }
}

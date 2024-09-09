package br.cefetmg.controller;

import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.entidades.Funcionario;
import java.util.List;

public class ControllerFuncionarios {

    public List<Funcionario> listarFuncionarios () {
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.getFuncionarios();
    }
    
}

package br.cefetmg.controller;

import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.utils.Encriptador;

public class ControllerCadastroFuncionario {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public void cadastrarFuncionario(Funcionario funcionario) {
        Encriptador encriptador = new Encriptador();
        funcionario.setSenha(encriptador.encriptarSenha(funcionario.getSenha()));
        
        funcionarioDAO.inserirFuncionario(funcionario);
    }
}
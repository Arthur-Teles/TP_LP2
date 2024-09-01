package br.cefetmg.controller;

import br.cefetmg.dao.EmpresaDAO;
import br.cefetmg.entidades.Empresa;


public class ControllerCadastroEmpresa {
    
    EmpresaDAO empresaDAO = new EmpresaDAO();
    
    
    public void cadastrarEmpresa(Empresa empresa) {
    
        empresaDAO.inserirEmpresa(empresa);
    }
    
}

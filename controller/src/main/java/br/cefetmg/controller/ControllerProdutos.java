package br.cefetmg.controller;

import br.cefetmg.dao.ProdutoDAO;
import br.cefetmg.entidades.Produto;
import java.util.List;

public class ControllerProdutos {

    public List<Produto> listarProdutos() {
        
        ProdutoDAO managerProduto = new ProdutoDAO();
        return managerProduto.getProdutos();
    }

}

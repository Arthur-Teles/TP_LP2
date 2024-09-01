package br.cefetmg.controller;

import br.cefetmg.dao.ProdutoDAO;
import br.cefetmg.entidades.Produto;

public class ControllerCadastroProduto {
    
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void cadastrarProduto(Produto produto) {
        produtoDAO.inserirProduto(produto);
    }
    
}

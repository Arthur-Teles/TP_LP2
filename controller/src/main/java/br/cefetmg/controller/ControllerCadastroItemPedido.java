package br.cefetmg.controller;

import br.cefetmg.dao.ItemPedidoDAO;
import br.cefetmg.entidades.ItemPedido;

public class ControllerCadastroItemPedido {
    
    ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    
    public void cadastrarItemPedido(ItemPedido itemPedido) {
        
        itemPedidoDAO.inserirItemPedido(itemPedido);
    }
    
}

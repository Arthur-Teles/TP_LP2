package br.cefetmg.controller;

import br.cefetmg.dao.PedidoDAO;
import br.cefetmg.entidades.Pedido;

public class ControllerCadastroPedido {
    
    PedidoDAO pedidoDAO = new PedidoDAO();
    
    public void cadastrarPedido(Pedido pedido) {
        
        pedidoDAO.inserirPedido(pedido);
    }
}

package br.cefetmg.controller;

import br.cefetmg.dao.PedidoDAO;
import br.cefetmg.entidades.Pedido;
import java.util.List;

public class ControllerPedidos {
    
    public List<Pedido> listarPedidos () {
        
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.getPedidos();
    }
}

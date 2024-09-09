package br.cefetmg.controller;

import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.dao.PedidoDAO;
import br.cefetmg.entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

public class ControleDeEntregas {
    
    public ArrayList<Pedido> getPedidos() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ControleDeSessao controleSessao = new ControleDeSessao();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        List<Pedido> pedidosRecuperados = pedidoDAO.getPedidos();
        int idDoEntregadorLogado = funcionarioDAO.encontrarFuncionarioJaEncriptada(controleSessao.getUsernameSessao(), controleSessao.getSenhaSessao()).get(0).getId();
        
        ArrayList<Pedido> pedidosARetornar = new ArrayList<>();
        
        for (Pedido pedido : pedidosRecuperados) {
            if (pedido.getIdEntregador() == idDoEntregadorLogado)
                pedidosARetornar.add(pedido);
        }
        
        return pedidosARetornar;
    } 
}

package br.cefetmg.controller;

import br.cefetmg.dao.ClienteDAO;
import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.dao.PedidoDAO;
import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroPedido {
    
    PedidoDAO pedidoDAO = new PedidoDAO();
    
    public void cadastrarPedido(Pedido pedido) {
        
        ControleDeSessao controleSessao = new ControleDeSessao();
        
        pedidoDAO.inserirPedido(pedido);
    }
    
    public long getCPFCliente() {
        
        ControleDeSessao controleSessao = new ControleDeSessao();
        ClienteDAO controleCliente = new ClienteDAO();
 
        Cliente clienteDaSessao = controleCliente.encontrarClienteJaEncriptada(controleSessao.getUsernameSessao(), controleSessao.getSenhaSessao()).get(0);

        return clienteDaSessao.getCPF();
    }
    
    public int getIdEntregador() {
        
        FuncionarioDAO controleFuncionario = new FuncionarioDAO();
        List<Funcionario> funcionariosCadastrados = controleFuncionario.getFuncionarios();
        ArrayList<Funcionario> funcionariosEntregadores = new ArrayList<>();
        
        for (Funcionario funcionario : funcionariosCadastrados) {
            if (funcionario.getTipoPerfil().ordinal() == 2) 
                funcionariosEntregadores.add(funcionario);
        }
        
        System.out.println(funcionariosEntregadores.size());
        
        int posicaoSorteada = (int) ((Math.random() * ((funcionariosEntregadores.size() -1 ) - 0)) + 0);
        
        return funcionariosEntregadores.get(posicaoSorteada).getId();
    }
}

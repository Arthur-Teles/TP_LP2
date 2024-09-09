package br.cefetmg.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import br.cefetmg.controller.*;
import br.cefetmg.dao.*;
import br.cefetmg.entidades.*;
import java.io.IOException;
import javafx.fxml.FXML;

public class MenuPrincipalAdminController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void cadastrarProduto () throws IOException {
        App.setRoot("cadastrarProduto");
    }
    
    @FXML
    public void cadastrarClientes () throws IOException {
        App.setRoot("cadastrarCliente");
    }
    
    @FXML
    public void cadastrarFuncionario () throws IOException {
        App.setRoot("cadastrarFuncionario");
    }
    
    @FXML
    public void listarClientes () throws IOException {
        App.setRoot("exibirClientes");
    }
    
    @FXML
    public void listarFuncionarios () throws IOException {
        App.setRoot("exibirFuncionarios");
    }
    
    @FXML
    public void listarPedidos () throws IOException {
        App.setRoot("exibirPedidos");
    } 
    
}

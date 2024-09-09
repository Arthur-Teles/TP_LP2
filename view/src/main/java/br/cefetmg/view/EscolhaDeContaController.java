package br.cefetmg.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EscolhaDeContaController {

    @FXML
    private Button escolhaContaCliente;

    @FXML
    private Button escolhaContaEntregador;

    @FXML
    void CriarContaDeCliente(ActionEvent event) throws IOException {
        App.setRoot("cadastrarCliente");
    }

    @FXML
    void CriarContaDeEntregador(ActionEvent event) throws IOException {
        App.setRoot("cadastrarEntregador");
    }

}
package br.cefetmg.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import br.cefetmg.controller.*; 

public class LoginController {

    @FXML
    private Button EntrarLogin;

    @FXML
    private TextField SenhaLogin;

    @FXML
    private TextField UsernameLogin;

    @FXML
    void CriarConta(ActionEvent event) {

    }

    @FXML
    void LogarConta(ActionEvent event) {
        ControleLogin controle = new ControleLogin();  
    }
}


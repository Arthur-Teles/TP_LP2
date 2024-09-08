package br.cefetmg.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import br.cefetmg.controller.*; 
import java.io.IOException;
import javafx.scene.control.Alert;

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
    void LogarConta(ActionEvent event) throws IOException{
        ControleLogin controle = new ControleLogin(); 
        
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.valueOf(controle.logar(SenhaLogin.getText(), UsernameLogin.getText())));
        alert.setTitle("Erro!");
        alert.setHeaderText("Usuário não encontrado!");

        alert.show();*/
        
        if (controle.logar(SenhaLogin.getText(), UsernameLogin.getText()))
            App.setRoot("secondary");
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Atenção ao preenchimento dos campos!");
            alert.setTitle("Erro!");
            alert.setHeaderText("Usuário não encontrado!");

            alert.show();
        }  
    }
}


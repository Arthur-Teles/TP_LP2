package br.cefetmg.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import br.cefetmg.controller.*; 
import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.Funcionario;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Alert;

public class LoginController {

    @FXML
    private Button EntrarLogin;

    @FXML
    private TextField SenhaLogin;

    @FXML
    private TextField UsernameLogin;

    @FXML
    void CriarConta(ActionEvent event) throws IOException {
        App.setRoot("escolhaDeConta");
    }

    @FXML
    void LogarConta(ActionEvent event) throws IOException{
        ControleLogin controleLogin = new ControleLogin(); 
        ControleDeSessao controleSessao = new ControleDeSessao();
        
        List<Cliente> clienteLogado = controleLogin.logarCliente(SenhaLogin.getText(), UsernameLogin.getText());
        List<Funcionario> funcionarioLogado = controleLogin.logarFuncionario(SenhaLogin.getText(), UsernameLogin.getText());
        
        if (!clienteLogado.isEmpty()) {
            Cliente clienteRecuperado = clienteLogado.get(0);
            
            controleSessao.setInfosUsuario(clienteRecuperado.getUsername(), clienteRecuperado.getSenha(), 3);
            
            App.setRoot("secondary"); //tela do cliente
        }
        else if (!funcionarioLogado.isEmpty()) {
            Funcionario funcionarioRecuperado = funcionarioLogado.get(0);
            
            controleSessao.setInfosUsuario(funcionarioRecuperado.getUsername(), funcionarioRecuperado.getSenha(), funcionarioRecuperado.getTipoPerfil().ordinal());
            
            App.setRoot("secondary"); //tela do funcionario
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Atenção ao preenchimento dos campos!");
            alert.setTitle("Erro!");
            alert.setHeaderText("Usuário não encontrado!");

            alert.show();
        }  
    }
}

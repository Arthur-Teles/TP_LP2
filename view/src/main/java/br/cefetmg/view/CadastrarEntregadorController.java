package br.cefetmg.view;

import br.cefetmg.controller.ControllerCadastroFuncionario;
import br.cefetmg.entidades.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarEntregadorController implements Initializable {
    
    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldTelefone;
    
    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldSenha;
    
    @FXML
    private Button enviar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldNome.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
        textFieldTelefone.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
    }
    
    public void calcularValorTotal() {
    
    }

    @FXML
    public void cadastrarFuncionario() {
        
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();
        String tipo = "Entregador";
        String username = textFieldUsername.getText();
        String senha = textFieldSenha.getText();
        
        if (verificarCampos() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Todos os campos precisam ser preenchidos");
            alert.setHeaderText("");
            alert.show();
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {

            Funcionario funcionario = new Funcionario();
            ControllerCadastroFuncionario controllerCF = new ControllerCadastroFuncionario();

            funcionario.setNome(nome);
            funcionario.setTelefone(telefone);
            funcionario.setTipoPerfil(tipo);
            funcionario.setUsername(username);
            funcionario.setSenha(senha);
            
            controllerCF.cadastrarFuncionario(funcionario);
            
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Funcionario cadastrado com sucesso! ");

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) textFieldNome.getScene().getWindow();
        stage.close();
    }
    
    private Boolean verificarCampos() {

        if (textFieldNome.getText().isEmpty()) {
            return false;
        } else if (textFieldTelefone.getText().isEmpty()) {
            return false;
        } else if (textFieldSenha.getText().isEmpty()) {
            return false;
        } else if (textFieldUsername.getText().isEmpty()) {
            return false;
        }

        return true;

    }
}
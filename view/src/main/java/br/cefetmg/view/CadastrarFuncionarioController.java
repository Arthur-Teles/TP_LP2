/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.view;

import br.cefetmg.controller.ControllerCadastroFuncionario;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.view.utils.CategoriaFuncionario;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CadastrarFuncionarioController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldTelefone;

    @FXML
    private ComboBox<String> comboBoxTipo;
    
    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldSenha;
    
    @FXML
    private Button enviar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxTipo.setItems(FXCollections.observableArrayList("Administrador", "Atendente", "Entregador"));
        
        textFieldNome.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
        textFieldTelefone.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
    }
    
    public void calcularValorTotal() {
    
        try {
            String primUsername = textFieldNome.getText();
            String segUsernameBruto = textFieldTelefone.getText();

            String segUsername = segUsernameBruto.length() > 5 ? segUsernameBruto.substring(0, 5): segUsernameBruto;

            textFieldUsername.setText(primUsername + segUsername);

        } catch (NumberFormatException e) {
            textFieldUsername.setText("");
        }
}

    @FXML
    public void cadastrarFuncionario() throws IOException {
        
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();
        String tipo = comboBoxTipo.getSelectionModel().getSelectedItem();
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
            alert.setContentText("Produto cadastrado com sucesso! ");

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        
        App.setRoot("login");
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
        } else if (comboBoxTipo.getSelectionModel().getSelectedItem().isEmpty()) {
            return false;
        }

        return true;

    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.view;

import br.cefetmg.controller.ControllerCadastroFuncionario;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.view.utils.CategoriaFuncionario;
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
    private Button enviar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxTipo.setItems(FXCollections.observableArrayList("Administrador", "Atendente", "Entregador"));
    }

    @FXML
    public void cadastrarFuncionario() {
        
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();
        String tipo = comboBoxTipo.getSelectionModel().getSelectedItem();
        
        if (nome == null || telefone == null || tipo == null) {
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

            controllerCF.cadastrarFuncionario(funcionario);

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) textFieldNome.getScene().getWindow();
        stage.close();
    }
}


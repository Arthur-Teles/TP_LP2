package br.cefetmg.view;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import br.cefetmg.controller.ControllerCadastroProduto;
import br.cefetmg.dao.ProdutoDAO;
import br.cefetmg.entidades.Produto;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class CadastrarProdutoController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldValor;
    
    @FXML
    private TextField textFieldMarca;

    @FXML
    private Button enviarProduto;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        textFieldValor.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        }));
    }

    @FXML
    public void cadastrarProduto() {

        String nomeProduto = textFieldNome.getText();
        String marcaProduto = textFieldMarca.getText();
        double valor = Double.parseDouble(textFieldValor.getText());

        if (verificarCampos() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Todos os campos precisam ser preenchidos");
            alert.setHeaderText("");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.NONE);
        try {

            Produto produto = new Produto();
            ControllerCadastroProduto controllerCP = new ControllerCadastroProduto();

            produto.setNome(nomeProduto);
            produto.setMarca(marcaProduto);
            produto.setValorUni(valor);

            controllerCP.cadastrarProduto(produto);
            
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Produto cadastrado com sucesso! ");

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) textFieldMarca.getScene().getWindow();
        stage.close();
    }
    
    private Boolean verificarCampos() {

        if (textFieldNome.getText().isEmpty()) {
            return false;
        } else if (textFieldMarca.getText().isEmpty()) {
            return false;
        } else if (textFieldValor.getText().isEmpty()) {
            return false;
        }
        return true;
    }
}

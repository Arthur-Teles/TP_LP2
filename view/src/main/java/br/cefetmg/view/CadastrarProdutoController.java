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
import javafx.stage.Stage;

public class CadastrarProdutoController implements Initializable {

    @FXML
    private TextField nomeProdutoFxml;

    @FXML
    private TextField localizacaoProdutoFxml;

    @FXML
    private Button enviarProduto;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarProduto() {

        String nomeProduto = nomeProdutoFxml.getText();
        String localizacaoProduto = localizacaoProdutoFxml.getText();

        if (nomeProduto == null || localizacaoProduto == null) {
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
            produto.setLocalizacao(localizacaoProduto);

            controllerCP.cadastrarProduto(produto);

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) localizacaoProdutoFxml.getScene().getWindow();
        stage.close();
    }

}

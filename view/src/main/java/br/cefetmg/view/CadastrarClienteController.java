package br.cefetmg.view;

import br.cefetmg.controller.ControllerCadastroCliente;
import br.cefetmg.entidades.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarClienteController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldLogradouro;

    @FXML
    private TextField textFieldBairro;

    @FXML
    private Button enviarButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarCliente() {

        String nome = textFieldNome.getText();
        String logradouro = textFieldLogradouro.getText();
        String bairro = textFieldBairro.getText();

        if (nome == null || logradouro == null || bairro == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Todos os campos precisam ser preenchidos");
            alert.setHeaderText("");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.NONE);
        try {

            Cliente cliente = new Cliente();
            ControllerCadastroCliente controllerCC = new ControllerCadastroCliente();

            cliente.setNome(nome);
            cliente.setLogradouro(logradouro);
            cliente.setBairro(bairro);

            controllerCC.cadastrarCliente(cliente);

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) textFieldLogradouro.getScene().getWindow();
        stage.close();
    }

}

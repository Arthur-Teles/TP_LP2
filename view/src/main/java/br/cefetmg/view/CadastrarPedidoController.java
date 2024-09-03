package br.cefetmg.view;

import br.cefetmg.controller.ControllerCadastroPedido;
import br.cefetmg.entidades.Pedido;
import br.cefetmg.entidades.ItemPedido;
import br.cefetmg.controller.ControllerCadastroItemPedido;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class CadastrarPedidoController implements Initializable {

    @FXML
    private Button enviar;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldUni;

    @FXML
    private TextField textFieldQntd;

    @FXML
    private TextField textFieldTot;

    @FXML
    private TextField textFieldMarca;

    @FXML
    private TextArea textAreaObs;

    @FXML
    private ComboBox<String> comboBoxForma;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxForma.setItems(FXCollections.observableArrayList("Dinheiro", "Pix", "Cartão de crédito", "Cartão de débito"));

        textFieldQntd.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        textFieldUni.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        }));

        textFieldUni.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
        textFieldQntd.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
    }

    private void calcularValorTotal() {
        try {
            double valorProduto = Double.parseDouble(textFieldUni.getText());
            int quantidade = Integer.parseInt(textFieldQntd.getText());

            double valorTotal = valorProduto * quantidade;

            textFieldTot.setText(String.format("%.2f", valorTotal));

        } catch (NumberFormatException e) {
            textFieldTot.setText("");
        }
    }

    @FXML
    public void cadastrarPedido() {

        if (verificarCampos() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Todos os campos precisam ser preenchidos");
            alert.setHeaderText("");
            alert.show();
            return;
        }

        String nome = textFieldNome.getText();
        double valorUni = Double.parseDouble(textFieldUni.getText());
        int quantidade = Integer.parseInt(textFieldQntd.getText());
        double valorTot = Double.parseDouble(textFieldTot.getText());
        String marca = textFieldMarca.getText();
        String obs = textAreaObs.getText();
        String formaPag = comboBoxForma.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {

            Pedido pedido = new Pedido();
            ControllerCadastroPedido controllerCP = new ControllerCadastroPedido();

            pedido.setValorTotal(valorTot);

            controllerCP.cadastrarPedido(pedido);

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) textFieldNome.getScene().getWindow();
        stage.close();
        
        try {

            ItemPedido itemPedido = new ItemPedido();
            ControllerCadastroItemPedido controllerCIP = new ControllerCadastroItemPedido();

            itemPedido.setQuantidade(quantidade);
            itemPedido.setValorUnitario(valorUni);

            controllerCIP.cadastrarItemPedido(itemPedido);

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        stage = (Stage) textFieldNome.getScene().getWindow();
        stage.close();
    }

    private Boolean verificarCampos() {

        if (textFieldNome.getText().isEmpty()) {
            return false;
        } else if (textFieldUni.getText().isEmpty()) {
            return false;
        } else if (textFieldQntd.getText().isEmpty()) {
            return false;
        } else if (textFieldTot.getText().isEmpty()) {
            return false;
        } else if (textFieldMarca.getText().isEmpty()) {
            return false;
        } else if (textAreaObs.getText().isEmpty()) {
            return false;
        } else if (comboBoxForma.getSelectionModel().getSelectedItem().isEmpty()) {
            return false;
        }

        return true;
    }

}

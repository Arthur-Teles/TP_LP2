package br.cefetmg.view;

import br.cefetmg.controller.*;
import br.cefetmg.dao.*;
import br.cefetmg.entidades.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
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
    private TextField textFieldQntd;

    @FXML
    private TextField textFieldTot;

    @FXML
    private TextArea textAreaObs;

    @FXML
    private ComboBox<String> comboBoxForma;
    
    @FXML
    private ComboBox<Produto> comboBoxProdutos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ControllerProdutos controllerProdutos = new ControllerProdutos();
        List<Produto> produtos = controllerProdutos.listarProdutos();
        
        comboBoxForma.setItems(FXCollections.observableArrayList("Dinheiro", "Pix", "Cartão de crédito", "Cartão de débito"));
        comboBoxProdutos.setItems(FXCollections.observableList(produtos));
        
        comboBoxProdutos.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Produto produto, boolean empty) {
                super.updateItem(produto, empty);
                setText(empty ? null : produto.getNome());
            }
        });

        comboBoxProdutos.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Produto produto, boolean empty) {
                super.updateItem(produto, empty);
                setText(empty ? null : produto.getNome());
            }
        });
                
        textFieldQntd.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        textFieldQntd.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());
    }

    private void calcularValorTotal() {
        try {
            Produto produto = comboBoxProdutos.getSelectionModel().getSelectedItem();
            int quantidade = Integer.parseInt(textFieldQntd.getText());

            double valorProduto = produto.getValorUni();
            double valorTotal = valorProduto * quantidade;

            textFieldTot.setText(Double.toString(valorTotal));

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
        
        Produto produto = comboBoxProdutos.getSelectionModel().getSelectedItem();
        Pedido pedido = new Pedido();
        
        String nome = produto.getNome();
        int quantidade = Integer.parseInt(textFieldQntd.getText());
        double valorTot = Double.parseDouble(textFieldTot.getText());
        String obs = textAreaObs.getText();
        String formaPag = comboBoxForma.getSelectionModel().getSelectedItem();
        
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {

            ControllerCadastroPedido controllerCP = new ControllerCadastroPedido();

            Date data = new Date();
            
            pedido.setValorTotal(valorTot);
            pedido.setData(data);
            pedido.setFormaPag(formaPag);
            pedido.setObs(obs);
            pedido.setQntd(quantidade);
            pedido.setValorTotal(valorTot);
            pedido.setStatus(StatusPedido.EM_PREPARACAO);
            
            pedido.setCPFCliente(controllerCP.getCPFCliente());
            pedido.setIdEntregador(controllerCP.getIdEntregador());

            controllerCP.cadastrarPedido(pedido);
            
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Pedido cadastrado com sucesso! ");

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        Stage stage = (Stage) textFieldQntd.getScene().getWindow();
        stage.close();
        
        try {

            ItemPedido itemPedido = new ItemPedido();
            ControllerCadastroItemPedido controllerCIP = new ControllerCadastroItemPedido();

            itemPedido.setIdPedido(pedido.getId());
            itemPedido.setIdProduto(produto.getId());
            itemPedido.setQuantidade(quantidade);

            controllerCIP.cadastrarItemPedido(itemPedido);

        } catch (Exception e) {

            System.out.println(e.toString());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());

        }

        alert.show();
        stage = (Stage) textFieldQntd.getScene().getWindow();
        stage.close();
    }

    private Boolean verificarCampos() {

        if (textFieldQntd.getText().isEmpty()) {
            return false;
        } else if (textFieldTot.getText().isEmpty()) {
            return false;
        } else if (textAreaObs.getText().isEmpty()) {
            return false;
        } else if (comboBoxForma.getSelectionModel().getSelectedItem().isEmpty()) {
            return false;
        }

        return true;
    }
}
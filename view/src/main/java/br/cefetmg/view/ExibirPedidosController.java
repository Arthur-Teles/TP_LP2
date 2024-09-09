package br.cefetmg.view;

import br.cefetmg.dao.PedidoDAO;
import br.cefetmg.entidades.Pedido;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExibirPedidosController implements Initializable {

    @FXML
    private TableView tabela;
    @FXML
    private TableColumn<PedidosCadastrados, Double> valorTotalCol;
    @FXML
    private TableColumn<PedidosCadastrados, String> statusCol;
    @FXML
    private TableColumn<PedidosCadastrados, String> obsCol;
    @FXML
    private TableColumn<PedidosCadastrados, String> formaPagCol;
    @FXML
    private TableColumn<PedidosCadastrados, Integer> qntdCol;
    @FXML
    private TableColumn<PedidosCadastrados, Date> dataCol;
    
    private PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        valorTotalCol.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        obsCol.setCellValueFactory(new PropertyValueFactory<>("obs"));
        formaPagCol.setCellValueFactory(new PropertyValueFactory<>("formaPag"));
        qntdCol.setCellValueFactory(new PropertyValueFactory<>("qntd"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

        List<Pedido> listaPedidos = pedidoDAO.getPedidos();

        ObservableList<PedidosCadastrados> pedidos = FXCollections.observableArrayList(
                listaPedidos.stream()
                        .map(pedido -> new PedidosCadastrados(
                        pedido.getValorTotal(),
                        String.valueOf(pedido.getStatus()),
                        pedido.getObs(),
                        pedido.getFormaPag(),
                        pedido.getQntd(),
                        pedido.getData()))
                        .collect(Collectors.toList())
        );

        tabela.setItems(pedidos);

    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("menuPrincipalAdmin");
    }

    public static class PedidosCadastrados {

        private final SimpleDoubleProperty valorTotal;
        private final SimpleStringProperty status;
        private final SimpleStringProperty obs;
        private final SimpleStringProperty formaPag;
        private final SimpleIntegerProperty qntd;
        private final ObjectProperty<Date> data;

        public PedidosCadastrados(Double valorTotal, String status, String obs, String formaPag, Integer qntd, Date data) {
            this.valorTotal = new SimpleDoubleProperty(valorTotal);
            this.status = new SimpleStringProperty(status);
            this.obs = new SimpleStringProperty(obs);
            this.formaPag = new SimpleStringProperty(formaPag);
            this.qntd = new SimpleIntegerProperty(qntd);
            this.data = new SimpleObjectProperty<>(data);
        }

        public Double getValorTotal() {
            return valorTotal.get();
        }

        public SimpleDoubleProperty valorTotalProperty() {
            return valorTotal;
        }

        public void setValorTotal(Double valorTotal) {
            this.valorTotal.set(valorTotal);
        }

        public String getStatus() {
            return status.get();
        }

        public SimpleStringProperty statusProperty() {
            return status;
        }

        public void setStatus(String status) {
            this.status.set(status);
        }

        public String getObs() {
            return obs.get();
        }

        public SimpleStringProperty obsProperty() {
            return obs;
        }

        public void setObs(String obs) {
            this.obs.set(obs);
        }

        public String getFormaPag() {
            return formaPag.get();
        }

        public SimpleStringProperty formaPagProperty() {
            return formaPag;
        }

        public void setFormaPag(String formaPag) {
            this.formaPag.set(formaPag);
        }

        public Integer getQntd() {
            return qntd.get();
        }

        public SimpleIntegerProperty qntdProperty() {
            return qntd;
        }

        public void setQntd(Integer qntd) {
            this.qntd.set(qntd);
        }

        public Date getDataCadastro() {
            return data.get();
        }

        public ObjectProperty<Date> dataProperty() {
            return data;
        }

        public void setData(Date data) {
            this.data.set(data);
        }

    }

}

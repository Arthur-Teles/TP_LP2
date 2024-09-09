package br.cefetmg.view;

import br.cefetmg.controller.ControleDeEntregas;
import br.cefetmg.entidades.Pedido;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VisualizarEntregasController implements Initializable {

    
    public static class PedidoCadastrado {
        private final SimpleLongProperty cpf;
        private final SimpleStringProperty data;
        private final SimpleStringProperty pag;
        private final SimpleDoubleProperty total;
        private final SimpleStringProperty obs;

        public PedidoCadastrado(Long cpf, String data, String pag, float total, String obs) {
            this.cpf = new SimpleLongProperty(cpf);
            this.data = new SimpleStringProperty(data);
            this.pag = new SimpleStringProperty(pag);
            this.total = new SimpleDoubleProperty(total);
            this.obs = new SimpleStringProperty(obs);
        }

        public long getCpf() {
            return cpf.get();
        }

        public SimpleLongProperty cpfProperty() {
            return cpf;
        }

        public void setCpf(long cpf) {
            this.cpf.set(cpf);
        }

        public String getData() {
            return data.get();
        }

        public SimpleStringProperty dataProperty() {
            return data;
        }

        public void setData(String data) {
            this.data.set(data);
        }

        public String getPag() {
            return pag.get();
        }

        public SimpleStringProperty pagProperty() {
            return pag;
        }

        public void setPag(String pag) {
            this.pag.set(pag);
        }
        
        public double getTotal() {
            return total.get();
        }

        public SimpleDoubleProperty totalProperty() {
            return total;
        }

        public void setTotal(double total) {
            this.total.set(total);
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
    }
    
    @FXML
    private TableView<PedidoCadastrado> tabela;
    
    @FXML
    private TableColumn<PedidoCadastrado, Long> cpfCol;
    
    @FXML
    private TableColumn<PedidoCadastrado, String> dataCol;
    
    @FXML
    private TableColumn<PedidoCadastrado, String> pagCol;
    
    @FXML
    private TableColumn<PedidoCadastrado, Double> totalCol;
    
    @FXML
    private TableColumn<PedidoCadastrado, String> obsCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        pagCol.setCellValueFactory(new PropertyValueFactory<>("pag"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        obsCol.setCellValueFactory(new PropertyValueFactory<>("obs"));
        
        tabela.setItems(listaDePedidos());
    }

    private ObservableList<PedidoCadastrado> listaDePedidos() {
        ControleDeEntregas controleEntregas = new ControleDeEntregas();
        
        ArrayList<Pedido> pedidosCadastradosOriginal = controleEntregas.getPedidos();
        ArrayList<PedidoCadastrado> pedidosCadastrados = new ArrayList<>();
        
        for (Pedido pedido : pedidosCadastradosOriginal){
            PedidoCadastrado pedidoCadastrado = new PedidoCadastrado(pedido.getCPFCliente(), String.valueOf(pedido.getData()), pedido.getFormaPag(), (float) pedido.getValorTotal(), pedido.getObs());
            pedidosCadastrados.add(pedidoCadastrado);
        }
        
        return FXCollections.observableArrayList(pedidosCadastrados);
    }
}

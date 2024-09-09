package br.cefetmg.view;

import br.cefetmg.dao.ClienteDAO;
import br.cefetmg.entidades.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExibirClientesController implements Initializable {

    @FXML
    private TableView tabela;
    @FXML
    private TableColumn<ClientesCadastrados, String> nomeCol;
    @FXML
    private TableColumn<ClientesCadastrados, String> logradouroCol;
    @FXML
    private TableColumn<ClientesCadastrados, String> bairroCol;
    @FXML
    private TableColumn<ClientesCadastrados, Long> telefoneCol;
    @FXML
    private TableColumn<ClientesCadastrados, String> usernameCol;
    
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        logradouroCol.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        bairroCol.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        List<Cliente> listaClientes = clienteDAO.getClientes();

        ObservableList<ClientesCadastrados> clientes = FXCollections.observableArrayList(
            listaClientes.stream()
                         .map(cliente -> new ClientesCadastrados(
                             cliente.getNome(),
                             cliente.getLogradouro(),
                             cliente.getBairro(),
                             cliente.getTelefone(),
                             cliente.getUsername()))
                         .collect(Collectors.toList())
        );

        tabela.setItems(clientes);
        
    }


    @FXML
    private void voltar() throws IOException {
        App.setRoot("menuPrincipalAdmin");
    }

    public static class ClientesCadastrados {

        private final SimpleStringProperty nome;
        private final SimpleStringProperty logradouro;
        private final SimpleStringProperty bairro;
        private final SimpleStringProperty telefone;
        private final SimpleStringProperty username;

        public ClientesCadastrados(String nome, String logradouro, String bairro, String telefone, String username) {
            this.nome = new SimpleStringProperty(nome);
            this.logradouro = new SimpleStringProperty(logradouro);
            this.bairro = new SimpleStringProperty(bairro);
            this.telefone = new SimpleStringProperty(telefone);
            this.username = new SimpleStringProperty(username);
        }

        public String getNome() {
            return nome.get();
        }

        public SimpleStringProperty nomeProperty() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome.set(nome);
        }
        
        public String getLogradouro() {
            return logradouro.get();
        }

        public SimpleStringProperty logradouroProperty() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro.set(logradouro);
        }
        
        public String getBairro() {
            return bairro.get();
        }

        public SimpleStringProperty bairroProperty() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro.set(bairro);
        }
        
        public String getTelefone() {
            return telefone.get();
        }

        public SimpleStringProperty telefoneProperty() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone.set(telefone);
        }
        
        public String getUsername() {
            return username.get();
        }

        public SimpleStringProperty usernameProperty() {
            return username;
        }

        public void setUsername(String username) {
            this.username.set(username);
        }

       
    }   
    
}

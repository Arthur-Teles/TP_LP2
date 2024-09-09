package br.cefetmg.view;

import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.entidades.Funcionario;
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

public class ExibirFuncionariosController implements Initializable {

    @FXML
    private TableView tabela;
    @FXML
    private TableColumn<FuncionariosCadastrados, String> nomeCol;
    @FXML
    private TableColumn<FuncionariosCadastrados, String> telefoneCol;
    @FXML
    private TableColumn<FuncionariosCadastrados, String> usernameCol;
    @FXML
    private TableColumn<FuncionariosCadastrados, String> funcaoCol;
    
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        funcaoCol.setCellValueFactory(new PropertyValueFactory<>("funcao"));

        List<Funcionario> listaFuncionarios = funcionarioDAO.getFuncionarios();

        ObservableList<FuncionariosCadastrados> funcionarios = FXCollections.observableArrayList(
                listaFuncionarios.stream()
                        .map(funcionario -> new FuncionariosCadastrados(
                        funcionario.getNome(),
                        funcionario.getTelefone(),
                        funcionario.getUsername(),
                        String.valueOf(funcionario.getTipoPerfil())))
                        .collect(Collectors.toList())
        );

        tabela.setItems(funcionarios);

    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("menuPrincipalAdmin");
    }

    public static class FuncionariosCadastrados {

        private final SimpleStringProperty nome;
        private final SimpleStringProperty telefone;
        private final SimpleStringProperty username;
        private final SimpleStringProperty funcao;

        public FuncionariosCadastrados(String nome, String telefone, String username, String funcao) {
            this.nome = new SimpleStringProperty(nome);
            this.telefone = new SimpleStringProperty(telefone);
            this.username = new SimpleStringProperty(username);
            this.funcao = new SimpleStringProperty(funcao);
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

        public String getFuncao() {
            return funcao.get();
        }

        public SimpleStringProperty funcaoProperty() {
            return funcao;
        }

        public void setFuncao(String funcao) {
            this.funcao.set(funcao);
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

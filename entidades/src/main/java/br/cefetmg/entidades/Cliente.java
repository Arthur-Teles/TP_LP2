package br.cefetmg.entidades;

import br.cefetmg.entidades.utils.Encriptador;
import javax.persistence.*;

@Entity
public class Cliente {
    private String nome;
    private String logradouro;
    private String bairro;
    private String telefone;
    private String senha;
    private String username;
    @Id
    private long CPF;

    public Cliente() {
        
    }
    
    public Cliente(String nome, String logradouro, String bairro, String telefone, String senha, String username, long CPF) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.telefone = telefone;
        this.username = username;
        this.CPF = CPF;
        
        Encriptador encriptador = new Encriptador();
        this.senha = encriptador.encriptarSenha(senha);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

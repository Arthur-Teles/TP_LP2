package br.cefetmg.entidades;

import javax.persistence.*;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nome;  
    private String senha;
    private String telefone;
    int idEmpresa;
    private TipoPerfil tipoPerfil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setTipoPerfil(String tipo) {
        
        this.tipoPerfil = TipoPerfil.tipoFuncionario(tipo);
    }
    
    public TipoPerfil getTipoPerfil() {
        return this.tipoPerfil;
    }
}

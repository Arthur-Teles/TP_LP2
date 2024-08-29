package br.cefetmg.entidades;

import javax.persistence.*;
import java.util.*;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int idPedido;
    private double valorUnitario;
    private int quantidade;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

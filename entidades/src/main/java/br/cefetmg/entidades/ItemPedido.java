package br.cefetmg.entidades;

import javax.persistence.*;
import java.util.*;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int idPedido;
    private int idProduto;
    private int quantidade;

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

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

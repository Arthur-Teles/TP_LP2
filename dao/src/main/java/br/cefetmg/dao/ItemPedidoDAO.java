package br.cefetmg.dao;

import br.cefetmg.entidades.ItemPedido;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class ItemPedidoDAO {
    
    public void inserirItemPedido(ItemPedido itemPedido) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(itemPedido);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void atualizarItemPedido(ItemPedido itemPedido) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            ItemPedido itemPedidoRecuperado = entityManager.find(ItemPedido.class, itemPedido.getId());

            if (itemPedidoRecuperado != null) {
                itemPedidoRecuperado.setIdPedido(itemPedido.getIdPedido());
                itemPedidoRecuperado.setQuantidade(itemPedido.getQuantidade());
                itemPedidoRecuperado.setValorUnitario(itemPedido.getValorUnitario());
            } else {
                System.out.println("Não foi possível encontrar um itemPedido com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void excluirItemPedido(ItemPedido itemPedido) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            ItemPedido itemPedidoRecuperado = entityManager.find(ItemPedido.class, itemPedido.getId());

            if (itemPedidoRecuperado != null) {
                entityManager.remove(itemPedidoRecuperado);
            } else {
                System.out.println("Não foi possível encontrar um itemPedido com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void getItemPedidos() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaQuery<ItemPedido> criteria = entityManager.getCriteriaBuilder().createQuery(ItemPedido.class);
        criteria.select(criteria.from(ItemPedido.class));
        List<ItemPedido> itemPedidos = entityManager.createQuery(criteria).getResultList();

        for (ItemPedido itemPedido : itemPedidos) {
            System.out.println("ID do item Pedido: " + itemPedido.getId());
            System.out.println("Quantidade do item Pedido: " + itemPedido.getQuantidade());
            System.out.println("Valor do item Pedido: " + itemPedido.getValorUnitario());
            System.out.println("ID do pedido referente ao item Pedido: " + itemPedido.getIdPedido());
        }
        
        entityManager.close();

    }
}

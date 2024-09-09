package br.cefetmg.dao;

import br.cefetmg.entidades.Pedido;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class PedidoDAO {
    
    public void inserirPedido(Pedido pedido) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pedido);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void atualizarPedido(Pedido pedido) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Pedido pedidoRecuperado = entityManager.find(Pedido.class, pedido.getId());

            if (pedidoRecuperado != null) {
                pedidoRecuperado.setData(pedido.getData());
                pedidoRecuperado.setValorTotal(pedido.getValorTotal());
                pedidoRecuperado.setStatus(pedido.getStatus());
                pedidoRecuperado.setFormaPag(pedido.getFormaPag());
                pedidoRecuperado.setQntd(pedido.getQntd());
                pedidoRecuperado.setObs(pedido.getObs());
            } else {
                System.out.println("Não foi possível encontrar um pedido com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void excluirPedido(Pedido pedido) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Pedido pedidoRecuperado = entityManager.find(Pedido.class, pedido.getId());

            if (pedidoRecuperado != null) {
                entityManager.remove(pedidoRecuperado);
            } else {
                System.out.println("Não foi possível encontrar um pedido com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void getPedidos() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaQuery<Pedido> criteria = entityManager.getCriteriaBuilder().createQuery(Pedido.class);
        criteria.select(criteria.from(Pedido.class));
        List<Pedido> pedidos = entityManager.createQuery(criteria).getResultList();

        for (Pedido pedido : pedidos) {
            System.out.println("ID do pedido: " + pedido.getId());
            System.out.println("Data do pedido: " + pedido.getData());
            System.out.println("Valor total do pedido: " + pedido.getValorTotal());
            System.out.println("Status do pedido: " + String.valueOf(pedido.getStatus()));
        }
        
        entityManager.close();

    }
}
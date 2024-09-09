package br.cefetmg.dao;

import br.cefetmg.entidades.Produto;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class ProdutoDAO {

    public void inserirProduto(Produto produto) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void atualizarProduto(Produto produto) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Produto produtoRecuperado = entityManager.find(Produto.class, produto.getId());

            if (produtoRecuperado != null) {
                produtoRecuperado.setNome(produto.getNome());
                produtoRecuperado.setValorUni(produto.getValorUni());
                produtoRecuperado.setMarca(produto.getMarca());
            } else {
                System.out.println("Não foi possível encontrar um produto com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void excluirProduto(Produto produto) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Produto produtoRecuperado = entityManager.find(Produto.class, produto.getId());

            if (produtoRecuperado != null) {
                entityManager.remove(produtoRecuperado);
            } else {
                System.out.println("Não foi possível encontrar um produto com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public List<Produto> getProdutos() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaQuery<Produto> criteria = entityManager.getCriteriaBuilder().createQuery(Produto.class);
        criteria.select(criteria.from(Produto.class));
        List<Produto> produtos = entityManager.createQuery(criteria).getResultList();

        entityManager.close();

        return produtos;
    }
}
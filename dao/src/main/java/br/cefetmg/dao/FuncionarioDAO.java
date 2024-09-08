package br.cefetmg.dao;

import br.cefetmg.entidades.Funcionario;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class FuncionarioDAO {

    public void inserirFuncionario(Funcionario funcionario) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void atualizarFuncionario(Funcionario funcionario) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Funcionario funcionarioRecuperado = entityManager.find(Funcionario.class, funcionario.getId());

            if (funcionarioRecuperado != null) {
                funcionarioRecuperado.setNome(funcionario.getNome());
                funcionarioRecuperado.setSenha(funcionario.getSenha());
                funcionarioRecuperado.setTelefone(funcionario.getTelefone());
                funcionarioRecuperado.setUsername(funcionario.getUsername());
            } else {
                System.out.println("Não foi possível encontrar um funcionario com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void excluirFuncionario(Funcionario funcionario) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Funcionario funcionarioRecuperado = entityManager.find(Funcionario.class, funcionario.getId());

            if (funcionarioRecuperado != null) {
                entityManager.remove(funcionarioRecuperado);
            } else {
                System.out.println("Não foi possível encontrar um funcionario com o ID fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void getFuncionarios() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaQuery<Funcionario> criteria = entityManager.getCriteriaBuilder().createQuery(Funcionario.class);
        criteria.select(criteria.from(Funcionario.class));
        List<Funcionario> funcionarios = entityManager.createQuery(criteria).getResultList();

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome do funcionario: " + funcionario.getNome());
            System.out.println("Id do funcionario: " + funcionario.getId());
            System.out.println("Senha do funcionario: " + funcionario.getSenha());
            System.out.println("Telefone do funcionario: " + funcionario.getTelefone());
            System.out.println("Username do funcionario: " + funcionario.getUsername());
        }

        entityManager.close();

    }
}

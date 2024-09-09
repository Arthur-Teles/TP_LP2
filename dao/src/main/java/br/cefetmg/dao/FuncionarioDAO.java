package br.cefetmg.dao;

import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.utils.Encriptador;
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

    public List<Funcionario> getFuncionarios() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaQuery<Funcionario> criteria = entityManager.getCriteriaBuilder().createQuery(Funcionario.class);
        criteria.select(criteria.from(Funcionario.class));
        List<Funcionario> funcionarios = entityManager.createQuery(criteria).getResultList();

        return funcionarios;
    }
    
    public List<Funcionario> encontrarFuncionario(String username, String senha) {
        Encriptador encriptador = new Encriptador();
        String senhaEncriptada = encriptador.encriptarSenha(senha);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.username = '"+username+"' AND f.senha = '"+senhaEncriptada+"'", Funcionario.class);

        List<Funcionario> resultado = query.getResultList();
        
        return resultado;
    }
    
    public List<Funcionario> encontrarFuncionarioJaEncriptada(String username, String senha) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.username = '"+username+"' AND f.senha = '"+senha+"'", Funcionario.class);

        List<Funcionario> resultado = query.getResultList();
        
        return resultado;
    }
}

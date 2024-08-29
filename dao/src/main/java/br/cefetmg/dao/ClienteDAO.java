package br.cefetmg.dao;

import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.utils.Encriptador;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClienteDAO {

    public void inserirCliente(Cliente cliente) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void atualizarCliente(Cliente cliente) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Cliente clienteRecuperado = entityManager.find(Cliente.class, cliente.getCPF());

            if (clienteRecuperado != null) {
                clienteRecuperado.setNome(cliente.getNome());
                clienteRecuperado.setCPF(cliente.getCPF());
                clienteRecuperado.setLogradouro(cliente.getLogradouro());
                clienteRecuperado.setBairro(cliente.getBairro());
                clienteRecuperado.setTelefone(cliente.getTelefone());
                clienteRecuperado.setSenha(cliente.getSenha());
                clienteRecuperado.setUsername(cliente.getUsername());
            } else {
                System.out.println("Não foi possível encontrar um cliente com o CPF fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void excluirCliente(Cliente cliente) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Cliente clienteRecuperado = entityManager.find(Cliente.class, cliente.getCPF());

            if (clienteRecuperado != null) {
                entityManager.remove(clienteRecuperado);
            } else {
                System.out.println("Não foi possível encontrar um cliente com o CPF fornecido!");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {

            entityManager.close();
        }

    }

    public void getClientes() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaQuery<Cliente> criteria = entityManager.getCriteriaBuilder().createQuery(Cliente.class);
        criteria.select(criteria.from(Cliente.class));
        List<Cliente> clientes = entityManager.createQuery(criteria).getResultList();

        for (Cliente cliente : clientes) {
            System.out.println("Nome do cliente: " + cliente.getNome());
            System.out.println("CPF do cliente: " + String.valueOf(cliente.getCPF()));
            System.out.println("Logradouro do cliente: " + cliente.getLogradouro());
            System.out.println("Bairro do cliente: " + cliente.getBairro());
            System.out.println("Telefone do cliente: " + cliente.getTelefone());
        }
        
        entityManager.close();

    }
    
    public boolean encontrarCliente(String username, String senha) {
        Encriptador encriptador = new Encriptador();
        String senhaEncriptada = encriptador.encriptarSenha(senha);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.username = "+username+" AND c.senha = "+senhaEncriptada, Cliente.class);
        List<Cliente> resultado = query.getResultList();
        
        return resultado != null;
    }
}

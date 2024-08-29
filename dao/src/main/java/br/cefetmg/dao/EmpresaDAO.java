package br.cefetmg.dao;

import br.cefetmg.entidades.Empresa;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class EmpresaDAO {
    
    public void inserirEmpresa(Empresa empresa) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(empresa);
            entityManager.getTransaction().commit();
        }
        catch(Exception e) {
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }
    
    public void atualizarEmpresa(Empresa empresa) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            
            Empresa empresaRecuperada = entityManager.find(Empresa.class, empresa.getCNPJ());
            
            if (empresaRecuperada != null) {
                empresaRecuperada.setNome(empresa.getNome());
                empresaRecuperada.setCNPJ(empresa.getCNPJ());
                empresaRecuperada.setPorcentagemComissaoEntregador(empresa.getPorcentagemComissaoEntregador());
            }
            else
                System.out.println("Não foi possível encontrar um empresa com o CNPJ fornecido!");
            
            entityManager.getTransaction().commit();
        }
        catch(Exception e) {
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }
    
    public void deletarEmpresa(int CNPJ) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            
            Empresa empresaRecuperada = entityManager.find(Empresa.class, CNPJ);
            
            if (empresaRecuperada != null)
                entityManager.remove(empresaRecuperada);
            else
                System.out.println("Não foi possível encontrar um empresa com o CNPJ fornecido!");
            
            entityManager.getTransaction().commit();
        }
        catch(Exception e) {
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }
    
    public void getEmpresas() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();

        CriteriaQuery<Empresa> criteria = entityManager.getCriteriaBuilder().createQuery(Empresa.class);
        criteria.select(criteria.from(Empresa.class));
        
        List<Empresa> empresas = entityManager.createQuery(criteria).getResultList();
        
        for (Empresa empresa : empresas) {
            System.out.println("Nome da Empresa: " + empresa.getNome());
            System.out.println("CNPJ da Empresa: " + String.valueOf(empresa.getCNPJ()));
            System.out.println("Porcentagem de comissão de entregadores da Empresa: " + String.valueOf(empresa.getPorcentagemComissaoEntregador()));
        }
        
        entityManager.close();
    }
}

package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Fornecedor;
import org.bancafx.utils.jpa.JPAUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class FornecedorRepositoryImp implements FornecedorRepository, Serializable {

    private EntityManager em;

    public FornecedorRepositoryImp(){
        em = JPAUtil.getEntityManager();
    }

    @Override
    public void salvar(Fornecedor f) {
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void excluir(Fornecedor f) {
        em.getTransaction().begin();
        em.remove(em.getReference(Fornecedor.class, f.getCnpj()));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(Fornecedor f) {
        em.getTransaction().begin();
        em.merge(f);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Fornecedor> buscarTodos() {
        return em.createNamedQuery(Fornecedor.TODOS_FORNECEDORES, Fornecedor.class).getResultList();
    }

    @Override
    public Fornecedor buscarPorCnpj(String cnpj) {
        //TODO implementar
        return null;
    }
}
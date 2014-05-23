package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */

public class GeneroProdutoRepositoryImp implements GeneroProdutoRepository, Serializable {

    private EntityManager em;

    public GeneroProdutoRepositoryImp(){
        em = JPAUtil.getEntityManager();
    }

    @Override
    public void salvar(GeneroProduto gp) {
        em.getTransaction().begin();
        em.persist(gp);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void excluir(GeneroProduto gp) {
        em.getTransaction().begin();
        em.remove(em.getReference(GeneroProduto.class, gp.getId()));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(GeneroProduto gp) {
        em.getTransaction().begin();
        em.merge(gp);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public GeneroProduto buscarPorId(Integer id) {
        return em.find(GeneroProduto.class, id);
    }

    @Override
    public List<GeneroProduto> buscarTodos() {
        return em.createNamedQuery(GeneroProduto.TODOS_GENEROS, GeneroProduto.class).getResultList();
    }
}

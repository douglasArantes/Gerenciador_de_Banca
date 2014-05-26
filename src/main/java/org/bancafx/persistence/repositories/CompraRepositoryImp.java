package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Compra;
import org.bancafx.utils.jpa.JPAUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class CompraRepositoryImp implements CompraRepository, Serializable {

    private EntityManager em;

    public CompraRepositoryImp(){
        em = JPAUtil.getEntityManager();
    }

    @Override
    public void salvar(Compra c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void excluir(Compra c) {
        em.getTransaction().begin();
        em.remove(em.getReference(Compra.class, c.getId()));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Compra> buscarTodas() {
        return em.createNamedQuery(Compra.TODAS_COMPRAS, Compra.class).getResultList();
    }

    @Override
    public List<Compra> buscarEntrePeriodo(LocalDate inicio, LocalDate fim) {
        //TODO implementar
        return null;
    }
}
